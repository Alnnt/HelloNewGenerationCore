package org.cn_grass_block.hello_new_generation_core.mixin.sableschematicapi;

import com.llamalad7.mixinextras.sugar.Local;
import dev.rew1nd.sableschematicapi.network.BlueprintToolServerActions;
import dev.rew1nd.sableschematicapi.network.SableSchematicApiPackets;
import dev.rew1nd.sableschematicapi.tool.SableSchematicApiItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BlueprintToolServerActions.class)
public abstract class MixinBlueprintToolServerActions {
    @Redirect(method = "handleLoadBlueprint", at = @At(value = "INVOKE", target = "Ldev/rew1nd/sableschematicapi/network/SableSchematicApiPackets;canUseBlueprintTool(Lnet/minecraft/server/level/ServerPlayer;)Z"), remap = false)
    private static boolean canUseBlueprintTool(ServerPlayer player, @Local(argsOnly = true) CompoundTag data) {
        if (data.getBoolean("ignore_permissions"))
            return true;

        ItemStack main = player.getMainHandItem();
        ItemStack off = player.getOffhandItem();
        return player.hasPermissions(2) && main.is(SableSchematicApiItems.BLUEPRINT_TOOL.get()) || off.is(SableSchematicApiItems.BLUEPRINT_TOOL.get());
    }
}
