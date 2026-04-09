package org.cn_good_grass.hello_new_generation_core.mixin.cbc;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import rbasamoyai.createbigcannons.cannons.big_cannons.BigCannonBlock;
import rbasamoyai.createbigcannons.cannons.big_cannons.BigCannonBlockItem;

@Mixin(BigCannonBlock.class)
public abstract class MixinBigCannonBlock {
    public void destroy(LevelAccessor level, BlockPos pos, BlockState state) {
        int a = 0;
    }
}
