package com.mayihavek.customemoji.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;

/**
 * @author MayIHaveK
 * @Date 2024/5/7 21:42
 */
public class TickHandler {

    public void init(){
        //弃用，不需要了
        //MinecraftForge.EVENT_BUS.register(this);
    }
    public static long time = 0;
    @SubscribeEvent
    public void onTick(RenderGameOverlayEvent.Text event){{
        time++;
    }}

}
