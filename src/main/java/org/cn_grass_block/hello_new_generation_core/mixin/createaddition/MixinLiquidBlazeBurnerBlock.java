package org.cn_grass_block.hello_new_generation_core.mixin.createaddition;

import com.mrh0.createaddition.blocks.liquid_blaze_burner.LiquidBlazeBurnerBlock;
import com.mrh0.createaddition.blocks.liquid_blaze_burner.LiquidBlazeBurnerBlockEntity;
import com.simibubi.create.content.processing.burner.BlazeBurnerBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LiquidBlazeBurnerBlock.class)
public abstract class MixinLiquidBlazeBurnerBlock {
    @Inject(method = "useItemOn", at = @At(value = "INVOKE", target = "Lcom/mrh0/createaddition/blocks/liquid_blaze_burner/LiquidBlazeBurnerBlock;tryInsert(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/item/ItemStack;ZZZ)Lnet/minecraft/world/InteractionResultHolder;", shift = At.Shift.AFTER))
    private static void useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult, CallbackInfoReturnable<ItemInteractionResult> cir) {
        LiquidBlazeBurnerBlockEntity be = (LiquidBlazeBurnerBlockEntity) level.getBlockEntity(pos);
        if (be != null && player != null && !player.isCreative() && be.isCreativeFuel(stack))
            stack.shrink(1);
    }
}
