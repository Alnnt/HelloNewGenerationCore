package org.cn_grass_block.hello_new_generation_core.mixin.cbcmw;

import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import riftyboi.cbcmodernwarfare.cannons.medium_cannon.MediumcannonBlockItem;
import riftyboi.cbcmodernwarfare.cannons.munitions_contraption_launcher.MunitionsLauncherBlockItem;

@Mixin(MunitionsLauncherBlockItem.class)
public abstract class MixinMunitionsLauncherBlockItem {
    @Redirect(method = "place", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;isCreative()Z"))
    public boolean place(Player instance) {
        return true;
    }
}
