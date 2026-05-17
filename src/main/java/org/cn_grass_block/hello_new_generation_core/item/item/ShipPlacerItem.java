package org.cn_grass_block.hello_new_generation_core.item.item;

import com.simibubi.create.AllDataComponents;
import com.simibubi.create.AllItems;
import com.simibubi.create.content.schematics.SchematicItem;
import com.simibubi.create.content.schematics.packet.SchematicPlacePacket;
import dev.rew1nd.sableschematicapi.network.BlueprintToolActionPayload;
import dev.rew1nd.sableschematicapi.network.BlueprintToolServerActions;
import dev.rew1nd.sableschematicapi.network.SableSchematicApiPackets;
import dev.rew1nd.sableschematicapi.tool.client.storage.BlueprintToolLocalFiles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.PatchedDataComponentMap;
import net.minecraft.core.component.TypedDataComponent;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.cn_grass_block.hello_new_generation_core.HelloNewGenerationCoreMod;
import org.cn_grass_block.hello_new_generation_core.data.HelloNewGenerationCoreModDataManger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;

public class ShipPlacerItem extends Item {
    public ShipPlacerItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack this_item = player.getItemInHand(usedHand);
        if (level.isClientSide) {
            this_item.shrink(1);
            player.setItemInHand(usedHand, this_item.copy());
        }

        if (level.isClientSide || !(this_item.getItem() instanceof ShipPlacerItem shipPlacerItem)) return InteractionResultHolder.sidedSuccess(this_item, level.isClientSide());

        String schematic_name = Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(shipPlacerItem)).getPath();
        CompoundTag schematic_data = HelloNewGenerationCoreModDataManger.ship_blueprint_map.get(schematic_name);
        String schematic_type = schematic_data.getString("type");
        String schematic_path = schematic_data.getString("path");

        Vec3 pos = player.pick(20, 0, true).getLocation();

        if (schematic_type.equals("create")) {
            ItemStack schematicItem = SchematicItem.create(level, schematic_path, HelloNewGenerationCoreMod.MODID);
            schematicItem.set(AllDataComponents.SCHEMATIC_ANCHOR, new BlockPos((int) pos.x(), (int) pos.y(), (int) pos.z()));

            SchematicPlacePacket packet = new SchematicPlacePacket(schematicItem);
            packet.handle((ServerPlayer) player);
        } else if (schematic_type.equals("sable")) {
            byte[] data = new byte[0];
            try {
                data = BlueprintToolLocalFiles.read(new BlueprintToolLocalFiles.Entry(schematic_name, Path.of(".\\Sable-Schematics\\hello_new_generation_core\\" + schematic_path)));
            } catch (IOException ignored) {}

            CompoundTag tag = new CompoundTag();
            tag.putString("name", schematic_name);
            tag.put("origin", writeVec3(pos));
            tag.putByteArray("data", data);
            tag.putBoolean("ignore_permissions", true);
            SableSchematicApiPackets.sendAction(BlueprintToolServerActions.LOAD_BLUEPRINT, tag);
        }

        this_item.shrink(1);
        player.setItemInHand(usedHand, this_item.copy());

        return InteractionResultHolder.success(this_item);
    }

    static CompoundTag writeVec3(Vec3 vec) {
        CompoundTag tag = new CompoundTag();
        tag.putDouble("x", vec.x);
        tag.putDouble("y", vec.y);
        tag.putDouble("z", vec.z);
        return tag;
    }
}