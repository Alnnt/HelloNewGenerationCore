package org.cn_good_grass.hello_new_generation_core.code;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.common.ForgeHooks;

public class IBreakBaseBlock {
    public static void playerWillDestroy(Level level, BlockPos blockPos, BlockState blockState, Player player) {
        if (player != null) {
            level.levelEvent(player, 2001, blockPos, Block.getId(blockState));
            if (blockState.is(BlockTags.GUARDED_BY_PIGLINS)) PiglinAi.angerNearbyPiglins(player, false);

            boolean break_pass = false;
            if (player.getMainHandItem().getItem() instanceof TieredItem tiered)
                break_pass = tiered.getTier().getLevel() >= Tiers.DIAMOND.getLevel();
            if (!player.isCreative() && break_pass)
                Containers.dropItemStack(level, blockPos.getX() + 0.5, blockPos.getY() + 0.5, blockPos.getZ() + 0.5, new ItemStack(blockState.getBlock()));

            level.gameEvent(GameEvent.BLOCK_DESTROY, blockPos, GameEvent.Context.of(player, blockState));
        }
    }

    public static float getDestroyProgress(BlockState state, Player player, BlockGetter level, BlockPos pos) {
        boolean break_pass = false;
        if (player.getMainHandItem().getItem() instanceof TieredItem tiered) break_pass =  tiered.getTier().getLevel() >= Tiers.DIAMOND.getLevel();

        float break_speed;
        float f = state.getDestroySpeed(level, pos);
        if (f == -1.0F) {
            break_speed = 0.0F;
        } else {
            break_speed =  player.getDigSpeed(state, pos) / f / (ForgeHooks.isCorrectToolForDrops(state, player) ? 30 : 100);
        }

        return break_pass ? 0.1f : break_speed;
    }
}
