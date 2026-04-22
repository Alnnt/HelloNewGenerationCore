package org.cn_grass_block.hello_new_generation_core.mixin.cbcmw;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.cn_good_grass.hello_new_generation_core.code.IBreakBaseBlock;
import org.spongepowered.asm.mixin.Mixin;
import riftyboi.cbcmodernwarfare.cannons.medium_cannon.MediumcannonBaseBlock;
import riftyboi.cbcmodernwarfare.cannons.rotarycannon.RotarycannonBaseBlock;

@Mixin(RotarycannonBaseBlock.class)
public abstract class MixinRotarycannonBaseBlock {
    public void playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        IBreakBaseBlock.playerWillDestroy(level, pos, state, player);
    }

    public float getDestroyProgress(BlockState state, Player player, BlockGetter level, BlockPos pos) {
        return IBreakBaseBlock.getDestroyProgress(state, player, level, pos);
    }
}
