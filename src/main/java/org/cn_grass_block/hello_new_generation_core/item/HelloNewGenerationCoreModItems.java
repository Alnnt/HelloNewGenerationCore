package org.cn_grass_block.hello_new_generation_core.item;

import com.simibubi.create.AllTags;
import com.simibubi.create.foundation.data.BlockStateGen;
import com.simibubi.create.foundation.data.ModelGen;
import com.simibubi.create.foundation.data.SharedProperties;
import com.simibubi.create.foundation.data.TagGen;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.util.entry.BlockEntry;
import dev.qwxon.tracks.Tracks;
import dev.qwxon.tracks.content.blocks.sable_track.SableTrackBlock;
import dev.qwxon.tracks.content.blocks.sable_track.SableTrackRole;
import dev.qwxon.tracks.content.items.TrackMountBlockItem;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.cn_grass_block.hello_new_generation_core.HelloNewGenerationCoreMod;
import org.cn_grass_block.hello_new_generation_core.data.HelloNewGenerationCoreModDataManger;
import org.cn_grass_block.hello_new_generation_core.item.item.ShipPlacerItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HelloNewGenerationCoreModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(HelloNewGenerationCoreMod.MODID);

    public static final Map<String, DeferredItem<Item>> ship_blueprint = new HashMap<>();

    public static void register(IEventBus eventBus) {
        List<String> ShipBlueprintID = HelloNewGenerationCoreModDataManger.ship_blueprint_map.keySet().stream().toList();
        for (String id : ShipBlueprintID) ship_blueprint.put(id, ITEMS.register(id, () -> new ShipPlacerItem(new Item.Properties().stacksTo(64))));

        ITEMS.register(eventBus);
    }
}