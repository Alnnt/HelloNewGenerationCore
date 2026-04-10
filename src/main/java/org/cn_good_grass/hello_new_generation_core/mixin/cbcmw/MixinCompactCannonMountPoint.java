package org.cn_good_grass.hello_new_generation_core.mixin.cbcmw;

import com.simibubi.create.content.contraptions.Contraption;
import com.simibubi.create.content.kinetics.mechanicalArm.ArmInteractionPoint;
import com.simibubi.create.content.kinetics.mechanicalArm.ArmInteractionPointType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import rbasamoyai.createbigcannons.cannon_control.contraption.AbstractMountedCannonContraption;
import rbasamoyai.createbigcannons.cannon_control.contraption.PitchOrientedContraptionEntity;
import rbasamoyai.createbigcannons.cannon_control.fixed_cannon_mount.FixedCannonMountBlockEntity;
import rbasamoyai.createbigcannons.cannons.big_cannons.breeches.quickfiring_breech.CannonMountPoint;
import riftyboi.cbcmodernwarfare.cannon_control.compact_mount.CompactCannonMountBlockEntity;
import riftyboi.cbcmodernwarfare.cannon_control.compact_mount.ExtendsCompactCannonMount;
import riftyboi.cbcmodernwarfare.index.CBCModernWarfareBlocks;

@Mixin({CannonMountPoint.class})
public abstract class MixinCompactCannonMountPoint extends ArmInteractionPoint {
    MixinCompactCannonMountPoint(ArmInteractionPointType type, Level level, BlockPos pos, BlockState state) {
        super(type, level, pos, state);
    }

    public ItemStack insert(ItemStack stack, boolean simulate) {
        CannonMountPoint self = (CannonMountPoint) (Object) this;
        BlockEntity be = this.getLevel().getBlockEntity(this.pos);
        PitchOrientedContraptionEntity poce;
        if (be instanceof ExtendsCompactCannonMount extendsMount) {
            CompactCannonMountBlockEntity mount = extendsMount.getCannonMount();
            if (mount == null) {
                return stack;
            }

            poce = mount.getContraption();
        } else {
            if (!(be instanceof FixedCannonMountBlockEntity)) {
                return stack;
            }

            FixedCannonMountBlockEntity mount = (FixedCannonMountBlockEntity)be;
            poce = mount.getContraption();
        }

        if (poce != null) {
            Contraption var10 = poce.getContraption();
            if (var10 instanceof AbstractMountedCannonContraption) {
                AbstractMountedCannonContraption cannon = (AbstractMountedCannonContraption)var10;
                return self.getInsertedResultAndDoSomething(stack, simulate, cannon, poce);
            }
        }

        return stack;
    }
}
