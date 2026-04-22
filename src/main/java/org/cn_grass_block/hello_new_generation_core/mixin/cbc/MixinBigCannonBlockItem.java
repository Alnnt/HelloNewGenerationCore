package org.cn_grass_block.hello_new_generation_core.mixin.cbc;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import rbasamoyai.createbigcannons.cannons.big_cannons.BigCannonBlockItem;

@Mixin(BigCannonBlockItem.class)
public abstract class MixinBigCannonBlockItem {
    @Redirect(method = "place", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;isCreative()Z"))
    public boolean place(Player instance) {
        return true;
    }
}
