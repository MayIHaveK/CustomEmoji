package com.mayihavek.customemoji;

import com.mayihavek.customemoji.proxy.CommonProxy;
import cpw.mods.fml.common.event.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@Mod(modid = Main.MODID, version = Main.VERSION, name = Main.NAME,  acceptedMinecraftVersions = "[1.7.10]")
public class Main {

    public static final String MODID = "customemoji";
    public static final String NAME = "Custom Emoji";

    public static final String VERSION = "1.0.0";
    public static final Logger LOG = LogManager.getLogger(MODID);

    @SidedProxy(clientSide = "com.mayihavek.customemoji.proxy.ClientProxy", serverSide = "com.mayihavek.customemoji.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    // preInit "Run before anything else. Read your config, create blocks, items, etc, and register them with the
    // GameRegistry." (Remove if not needed)
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    // load "Do your mod setup. Build whatever data structures you care about. Register recipes." (Remove if not needed)
    public void init(FMLInitializationEvent event) throws IOException, NoSuchAlgorithmException {
        proxy.init(event);
    }

    @Mod.EventHandler
    // postInit "Handle interaction with other mods, complete your setup based on this." (Remove if not needed)
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    @Mod.EventHandler
    // register server commands in this event handler (Remove if not needed)
    public void serverStarting(FMLServerStartingEvent event) {
        proxy.serverStarting(event);
    }

    @Mod.EventHandler
    public void serverStarted(FMLServerStartedEvent event) {proxy.serverStarted(event);}
}
