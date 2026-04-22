package org.cn_grass_block.hello_new_generation_core.mixin.cbcmw;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.common.ForgeHooks;
import org.cn_good_grass.hello_new_generation_core.code.IBreakBaseBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import riftyboi.cbcmodernwarfare.cannons.medium_cannon.MediumcannonBaseBlock;
import riftyboi.cbcmodernwarfare.cannons.munitions_contraption_launcher.MunitionsLauncherBaseBlock;

@Mixin(MediumcannonBaseBlock.class)
public abstract class MixinMediumcannonBaseBlock {
    public void playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        IBreakBaseBlock.playerWillDestroy(level, pos, state, player);
    }

    public float getDestroyProgress(BlockState state, Player player, BlockGetter level, BlockPos pos) {
        return IBreakBaseBlock.getDestroyProgress(state, player, level, pos);
    }
}
