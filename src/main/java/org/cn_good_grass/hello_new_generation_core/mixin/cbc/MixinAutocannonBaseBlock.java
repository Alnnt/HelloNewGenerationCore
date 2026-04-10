package org.cn_good_grass.hello_new_generation_core.mixin.cbc;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.cn_good_grass.hello_new_generation_core.code.IBreakBaseBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import rbasamoyai.createbigcannons.cannons.autocannon.AutocannonBaseBlock;
import rbasamoyai.createbigcannons.cannons.big_cannons.BigCannonBaseBlock;
import riftyboi.cbcmodernwarfare.index.CBCModernWarfareBlocks;

@Mixin(AutocannonBaseBlock.class)
public abstract class MixinAutocannonBaseBlock {
    public void playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        IBreakBaseBlock.playerWillDestroy(level, pos, state, player);
    }

    public float getDestroyProgress(BlockState state, Player player, BlockGetter level, BlockPos pos) {
        return IBreakBaseBlock.getDestroyProgress(state, player, level, pos);
    }
}
