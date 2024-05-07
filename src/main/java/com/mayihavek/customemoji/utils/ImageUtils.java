package com.mayihavek.customemoji.utils;

import com.madgag.gif.fmsware.GifDecoder;
import com.mayihavek.customemoji.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.util.ResourceLocation;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author MayIHaveK
 * @Date 2024/5/7 19:54
 */
public class ImageUtils {


    public static InputStream getTexture(Minecraft mc,String texturePath) {
        // 创建资源位置对象
        ResourceLocation location = new ResourceLocation(Main.MODID, texturePath);
        try {
            // 获取资源
            return mc.getResourceManager().getResource(location).getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<BufferedImage> getGifBuffImage(InputStream stream) throws IOException {
        ArrayList<BufferedImage> list = new ArrayList<>();
        GifDecoder gd = new GifDecoder();
        int status = gd.read(stream);
        if (status != GifDecoder.STATUS_OK) {
            return new ArrayList<>();
        }
        for (int i = 0; i < gd.getFrameCount(); i++) {
            BufferedImage frame = gd.getFrame(i);
            list.add(frame);
        }
        return list;
    }

    public static void loadImage(BufferedImage bufferedImage,String textureName) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //存入的缓存
        ResourceLocation resourceLocation = getImageResourceLocation(textureName);
        loadImage(bufferedImage,resourceLocation);
    }

    public static ResourceLocation getImageResourceLocation(String texturePath) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return new ResourceLocation(Main.MODID,"textures/" + texturePath);
    }

    public static void loadImage(BufferedImage bufferedImage, ResourceLocation resource){
        TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
        ITextureObject object = new GifImageLoadAlt(SkinManager.field_152793_a,bufferedImage);
        texturemanager.loadTexture(resource, object);
    }


}
