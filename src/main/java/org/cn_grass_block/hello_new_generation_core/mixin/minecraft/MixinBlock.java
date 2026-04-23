package org.cn_grass_block.hello_new_generation_core.mixin.minecraft;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import org.cn_grass_block.hello_new_generation_core.code.IBreakBaseBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import rbasamoyai.createbigcannons.cannons.autocannon.AutocannonBaseBlock;
import rbasamoyai.createbigcannons.cannons.big_cannons.BigCannonBaseBlock;

import javax.annotation.Nullable;
import java.util.List;

@Mixin(Block.class)
public abstract class MixinBlock {
    @Redirect(method = "getDrops(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/entity/BlockEntity;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/item/ItemStack;)Ljava/util/List;", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;getDrops(Lnet/minecraft/world/level/storage/loot/LootParams$Builder;)Ljava/util/List;"))
    private static List<ItemStack> getDrops(BlockState instance, LootParams.Builder builder) {
        if (instance.getBlock() instanceof BigCannonBaseBlock || instance.getBlock() instanceof AutocannonBaseBlock) return List.of(new ItemStack(Items.AIR));
        return instance.getDrops(builder);
    }
}
