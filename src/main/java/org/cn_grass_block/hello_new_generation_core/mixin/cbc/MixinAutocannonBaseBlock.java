package org.cn_grass_block.hello_new_generation_core.mixin.cbc;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.cn_grass_block.hello_new_generation_core.code.IBreakBaseBlock;
import org.spongepowered.asm.mixin.Mixin;
import rbasamoyai.createbigcannons.cannons.autocannon.AutocannonBaseBlock;

@Mixin(AutocannonBaseBlock.class)
public abstract class MixinAutocannonBaseBlock {
    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        IBreakBaseBlock.playerWillDestroy(level, pos, state, player);
        return state;
    }

    public float getDestroyProgress(BlockState state, Player player, BlockGetter level, BlockPos pos) {
        return IBreakBaseBlock.getDestroyProgress(state, player, level, pos);
    }
}
