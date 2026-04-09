package org.cn_good_grass.hello_new_generation_core;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(HelloNewGenerationCoreMod.MODID)
public class HelloNewGenerationCoreMod {

    public static final String MODID = "hello_new_generation_core";
    public static final Logger LOGGER = LogUtils.getLogger();

    public HelloNewGenerationCoreMod(FMLJavaModLoadingContext modLoadingContext) {
        MinecraftForge.EVENT_BUS.register(this);


        IEventBus modEventBus = modLoadingContext.getModEventBus();

        eventRegister();

        modEventBus.addListener(this::onCommonSetup);
    }

    private void onCommonSetup(FMLCommonSetupEvent event) {
    }


    private void eventRegister() {

    }
}
