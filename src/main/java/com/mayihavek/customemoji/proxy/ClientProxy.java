package com.mayihavek.customemoji.proxy;

import com.madgag.gif.fmsware.GifDecoder;
import com.mayihavek.customemoji.Main;
import com.mayihavek.customemoji.config.ConfigLoader;
import com.mayihavek.customemoji.entity.EmojiEntity;
import com.mayihavek.customemoji.entity.GifImageEntity;
import com.mayihavek.customemoji.event.TickHandler;
import com.mayihavek.customemoji.render.FontRendererEmoji;
import com.mayihavek.customemoji.utils.ImageUtils;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class ClientProxy extends CommonProxy {
    public static Minecraft mc;

    @Override
    public void init(FMLInitializationEvent event) throws IOException, NoSuchAlgorithmException {
        mc = Minecraft.getMinecraft();
        FontRendererEmoji fontRenderer = new FontRendererEmoji(mc.gameSettings, new ResourceLocation("textures/font/ascii.png"), mc.renderEngine, false);
        fontRenderer.setUnicodeFlag(mc.func_152349_b());
        fontRenderer.setBidiFlag(mc.getLanguageManager().isCurrentLanguageBidirectional());
        ((IReloadableResourceManager)mc.getResourceManager()).registerReloadListener(fontRenderer);
        mc.fontRenderer = fontRenderer;
        //new TickHandler().init();
        loadEmoji();

    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {

    }

    @Override
    public void serverStarting(FMLServerStartingEvent event) {

    }

    public static void loadEmoji() throws IOException, NoSuchAlgorithmException {
        String judgeChar = ConfigLoader.judgeChar;
        String[] list = ConfigLoader.emojiList;
        for(int i=0;i<list.length;i++){
            String[] split = list[i].split(judgeChar);
            String name = split[0];
            int index = Integer.parseInt(split[1]);
            float dw = Float.parseFloat(split[2]);
            float dh = Float.parseFloat(split[3]);
            String type = split[4];
            EmojiEntity entity;
            if(type.equalsIgnoreCase("gif")){
                long cd = Long.parseLong(split[5]);
                InputStream stream = ImageUtils.getTexture(mc,"textures/" + name + ".gif");
                List<BufferedImage> bufferedImages = ImageUtils.getGifBuffImage(stream);
                int k = 0;
                for (BufferedImage bufferedImage : bufferedImages) {
                    ImageUtils.loadImage(bufferedImage, name+".gif_" + k);
                    k++;
                }
                entity = new EmojiEntity(name,index,dw,dh,type,new GifImageEntity(name, cd,k,0,0));
            }else{
                entity = new EmojiEntity(name,index,dw,dh,type);
            }

            EmojiEntity.emojiMap.put(name,entity);
            EmojiEntity.emojiIndexMap.put(index,entity);
        }
    }

    public static List<BufferedImage> getGifBuffImage(String path) throws IOException {
        ArrayList<BufferedImage> list = new ArrayList<>();
        GifDecoder gd = new GifDecoder();
        int status = gd.read(Files.newInputStream(new File(path).toPath()));
        if (status != GifDecoder.STATUS_OK) {
            return new ArrayList<>();
        }
        for (int i = 0; i < gd.getFrameCount(); i++) {
            BufferedImage frame = gd.getFrame(i);
            list.add(frame);
        }
        return list;
    }


}
