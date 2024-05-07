package com.mayihavek.customemoji.event;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;

/**
 * @author MayIHaveK
 * @Date 2024/5/7 21:42
 */
public class TickHandler {

    public void init(){
        FMLCommonHandler.instance().bus().register(this);
    }
    public static long time = 0;
    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event){{
        time++;
    }}

}
