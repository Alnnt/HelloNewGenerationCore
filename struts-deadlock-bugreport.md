# Bug Report: Server thread deadlock during chunk unload (Strut Your Stuff 1.2.5)

## Summary
On NeoForge 1.21.1 with C2ME installed, unloading a chunk that contains part of a
strut causes the server thread to deadlock. A single server tick runs for 600+
seconds until the watchdog (ModernFix) reports the server as frozen and the game
hangs.

## Environment
- Strut Your Stuff **1.2.5** (latest) — pulled in as a required dependency of Create: Bits 'n' Bobs
- NeoForge 1.21.1
- C2ME `0.4.0-alpha.0.113+1.21.1` (async chunk system)
- Java 25, integrated (single-player) server
- Watchdog: ModernFix 5.27.15 IntegratedWatchdog

## Root cause
`GirderStrutStructureShapes$ShapeRegistry.unregisterConnection(Level, ConnectionKey)`
iterates over every `BlockPos` of a strut's geometry and calls
`level.getBlockState(pos)` / `level.removeBlock(pos, false)`.

A strut spans multiple chunks. When chunk A unloads, `StrutBlockEntity.setRemoved()`
runs during `LevelChunk.clearAllBlockEntities()` and calls `unregisterConnection`,
which then calls `level.getBlockState(pos)` for positions located in a *neighboring,
not-yet-loaded chunk B*.

Under C2ME's asynchronous chunk system this forces a synchronous chunk load
(`ServerChunkCache.getChunk` -> `managedBlock` -> `waitForTasks`) **from inside the
unload callback on the server thread**. The load task can only be completed by the
server thread, which is blocked waiting for it -> the thread waits on itself forever.

Under vanilla's chunk system this is masked; C2ME exposes it as a hard deadlock.

## Server thread stack (abridged)
```
Server thread ... TIMED_WAITING
  at net.minecraft.util.thread.BlockableEventLoop.waitForTasks
  at ...ServerChunkCache$MainThreadExecutor.managedBlock
  at ...ServerChunkCache.getChunk (c2me instrumented)
  at net.minecraft.world.level.Level.getBlockState(Level.java:6327)
  at com.cake.struts.content.structure.GirderStrutStructureShapes$ShapeRegistry.unregisterConnection(GirderStrutStructureShapes.java:167)
  at com.cake.struts.content.structure.GirderStrutStructureShapes.unregisterConnection(GirderStrutStructureShapes.java:36)
  at com.cake.struts.content.block.StrutBlockEntity.setRemoved(StrutBlockEntity.java:87)
  at net.minecraft.world.level.chunk.LevelChunk.clearAllBlockEntities
  at net.minecraft.server.level.ServerLevel.unload(ServerLevel.java:954)
  ... (c2me ReadFromDiskAsync.downgradeFromThis chunk unload)
  at net.minecraft.server.MinecraftServer.tickServer
```
Watchdog: "A single server tick has taken 662942, more than 40000 milliseconds"
(rising to 869973 ms across successive dumps).

## Suggested fix
Never force-load another chunk from within `setRemoved`/unload. Guard every
cross-position world access in `unregisterConnection` (and the analogous
`placeStructureBlockIfPossible`) with a chunk-loaded check:

```java
// GirderStrutStructureShapes$ShapeRegistry.unregisterConnection
this.shapesByPosition.remove(pos);
if (!level.isLoaded(pos)) continue;              // <-- add this guard
if (level.getBlockState(pos).getBlock() != StrutBlocks.GIRDER_STRUT_STRUCTURE.get()) continue;
level.removeBlock(pos, false);
```

The skipped structure block is harmless: it will be cleaned up when its own chunk
is loaded/unloaded and its `PositionData` entry no longer exists.

## Reproduction
1. NeoForge 1.21.1 + C2ME + Strut Your Stuff 1.2.5.
2. Place a strut long enough to span two chunks.
3. Fly away so one end's chunk unloads while the other is still loaded (or trigger
   chunk save/unload while the strut straddles a chunk border).
4. Server thread hangs; watchdog reports a multi-hundred-second tick.
