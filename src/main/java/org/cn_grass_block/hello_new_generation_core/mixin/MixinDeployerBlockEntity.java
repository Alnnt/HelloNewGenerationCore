package org.cn_grass_block.hello_new_generation_core.mixin;

import com.simibubi.create.AllItems;
import com.simibubi.create.content.kinetics.deployer.DeployerBlockEntity;
import com.simibubi.create.content.kinetics.deployer.DeployerFakePlayer;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DeployerBlockEntity.class)
public class MixinDeployerBlockEntity {
    @Shadow(remap = false) protected DeployerFakePlayer player;

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lcom/simibubi/create/content/kinetics/base/KineticBlockEntity;tick()V", shift = At.Shift.AFTER), remap = false)
    private void ItemInspection(CallbackInfo ci) {
        if (player == null) return;
        for (ItemStack itemStack : player.getInventory().items) {
            if (itemStack.is(AllItems.WORLDSHAPER.get())) {
                player.getInventory().clearContent();
            }
        }
    }
}
