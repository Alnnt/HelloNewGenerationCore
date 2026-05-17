package org.cn_grass_block.hello_new_generation_core;

import com.mojang.logging.LogUtils;
import lombok.extern.slf4j.Slf4j;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import org.cn_grass_block.hello_new_generation_core.data.HelloNewGenerationCoreModDataManger;
import org.cn_grass_block.hello_new_generation_core.item.HelloNewGenerationCoreModItems;
import org.slf4j.Logger;

@Mod("hello_new_generation_core")
@Slf4j(topic = "HelloNewGenerationCore")
public class HelloNewGenerationCoreMod {

    public static final String MODID = "hello_new_generation_core";
    public static final Logger LOGGER = LogUtils.getLogger();

    public HelloNewGenerationCoreMod(IEventBus modEventBus, ModContainer modContainer) {
        HelloNewGenerationCoreModDataManger.readJson();

        HelloNewGenerationCoreModItems.register(modEventBus);
    }
}
