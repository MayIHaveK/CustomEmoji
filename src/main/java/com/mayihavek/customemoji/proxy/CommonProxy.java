package com.mayihavek.customemoji.proxy;

import com.mayihavek.customemoji.config.ConfigLoader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

public class CommonProxy {


    public void preInit(FMLPreInitializationEvent event) {
        ConfigLoader.synchronizeConfiguration(event.getSuggestedConfigurationFile());
    }


    public void init(FMLInitializationEvent event) {}


    public void postInit(FMLPostInitializationEvent event) {}

    public void serverStarting(FMLServerStartingEvent event) {}
}
