package org.cn_grass_block.hello_new_generation_core.mixin;

import com.simibubi.create.AllItems;
import com.simibubi.create.content.kinetics.deployer.DeployerBlockEntity;
import com.simibubi.create.content.kinetics.deployer.DeployerFakePlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DeployerBlockEntity.class)
public abstract class MixinDeployerBlockEntity {
    @Shadow(remap = false) protected DeployerFakePlayer player;
    @Shadow(remap = false) protected abstract void activate();

    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lcom/simibubi/create/content/kinetics/deployer/DeployerBlockEntity;activate()V"), remap = false)
    private void ItemInspection(DeployerBlockEntity instance) {
        if (player == null) return;
        if (player.getItemInHand(InteractionHand.MAIN_HAND).is(AllItems.WORLDSHAPER.get()))
            player.getInventory().clearContent();
        else
            activate();
    }
}
