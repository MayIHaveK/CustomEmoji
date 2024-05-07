package com.mayihavek.customemoji.proxy;

import com.mayihavek.customemoji.config.ConfigLoader;
import cpw.mods.fml.common.event.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class CommonProxy {


    public void preInit(FMLPreInitializationEvent event) {
        ConfigLoader.synchronizeConfiguration(event.getSuggestedConfigurationFile());
    }


    public void init(FMLInitializationEvent event) throws IOException, NoSuchAlgorithmException {}


    public void postInit(FMLPostInitializationEvent event) {}

    public void serverStarting(FMLServerStartingEvent event) {}

    public void serverStarted(FMLServerStartedEvent event) {}

}
