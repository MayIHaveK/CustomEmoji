package com.mayihavek.customemoji.proxy;

import com.mayihavek.customemoji.config.ConfigLoader;
import com.mayihavek.customemoji.entity.EmojiEntity;
import com.mayihavek.customemoji.render.FontRendererEmoji;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.util.ResourceLocation;

public class ClientProxy extends CommonProxy {
    public Minecraft mc;

    @Override
    public void init(FMLInitializationEvent event) {
        mc = Minecraft.getMinecraft();
        FontRendererEmoji fontRenderer = new FontRendererEmoji(mc.gameSettings, new ResourceLocation("textures/font/ascii.png"), mc.renderEngine, false);
        fontRenderer.setUnicodeFlag(mc.func_152349_b());
        fontRenderer.setBidiFlag(mc.getLanguageManager().isCurrentLanguageBidirectional());
        ((IReloadableResourceManager)mc.getResourceManager()).registerReloadListener(fontRenderer);
        mc.fontRenderer = fontRenderer;
        loadEmoji();
    }

    public static void loadEmoji(){
        String judgeChar = ConfigLoader.judgeChar;
        String[] list = ConfigLoader.emojiList;
        for(int i=0;i<list.length;i++){
            String[] split = list[i].split(judgeChar);
            String name = split[0];
            int index = Integer.parseInt(split[1]);
            float dw = Float.parseFloat(split[2]);
            float dh = Float.parseFloat(split[3]);
            EmojiEntity entity = new EmojiEntity(name,index,dw,dh);
            EmojiEntity.emojiMap.put(name,entity);
            EmojiEntity.emojiIndexMap.put(index,entity);
        }
    }

}
