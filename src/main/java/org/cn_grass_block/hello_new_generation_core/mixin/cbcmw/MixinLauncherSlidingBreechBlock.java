package org.cn_grass_block.hello_new_generation_core.mixin.cbcmw;

import com.simibubi.create.content.kinetics.base.DirectionalAxisKineticBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.cn_good_grass.hello_new_generation_core.code.IBreakBaseBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import riftyboi.cbcmodernwarfare.cannons.munitions_contraption_launcher.MunitionsLauncherBaseBlock;
import riftyboi.cbcmodernwarfare.cannons.munitions_contraption_launcher.breeches.sliding_breech.LauncherSlidingBreechBlock;

@Mixin(LauncherSlidingBreechBlock.class)
public abstract class MixinLauncherSlidingBreechBlock {
    @Redirect(method = "playerWillDestroy", at = @At(value = "INVOKE", target = "Lcom/simibubi/create/content/kinetics/base/DirectionalAxisKineticBlock;playerWillDestroy(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/entity/player/Player;)V"))
    public void playerWillDestroy(DirectionalAxisKineticBlock instance, Level level, BlockPos blockPos, BlockState blockState, Player player) {
        IBreakBaseBlock.playerWillDestroy(level, blockPos, blockState, player);
    }

    public float getDestroyProgress(BlockState state, Player player, BlockGetter level, BlockPos pos) {
        return IBreakBaseBlock.getDestroyProgress(state, player, level, pos);
    }
}
