package org.cn_grass_block.hello_new_generation_core.mixin.bvb;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.simulated_team.simulated.content.blocks.lasers.AbstractLaserBlockEntity;
import dev.simulated_team.simulated.content.blocks.lasers.AbstractLaserRenderer;
import net.irisshaders.iris.api.v0.IrisApi;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.cn_grass_block.hello_new_generation_core.code.IBreakBaseBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractLaserRenderer.class)
public abstract class MixinAbstractLaserRenderer<T extends AbstractLaserBlockEntity> {
    @Inject(method = "renderSafe(Ldev/simulated_team/simulated/content/blocks/lasers/AbstractLaserBlockEntity;FLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;II)V", at = @At("HEAD"), cancellable = true, remap = false)
    private void stopRender(T blockEntity, float partialTicks, PoseStack pose, MultiBufferSource buffer, int light, int overlay, CallbackInfo ci) {
        if (IrisApi.getInstance().isShaderPackInUse()) ci.cancel();
    }
}

