package org.cn_good_grass.hello_new_generation_core.mixin.cbc;

import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import rbasamoyai.createbigcannons.cannons.autocannon.AutocannonBlockItem;

@Mixin(AutocannonBlockItem.class)
public abstract class MixinAutocannonBlockItem {
    @Redirect(method = "place", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;isCreative()Z"))
    public boolean place(Player instance) {
        return true;
    }
}
