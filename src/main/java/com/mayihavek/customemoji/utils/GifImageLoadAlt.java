package com.mayihavek.customemoji.utils;

import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author MayIHaveK
 * @Date 2023/5/31 18:53
 */
public class GifImageLoadAlt extends SimpleTexture {

    private static final Logger logger = LogManager.getLogger();
    private final BufferedImage bufferedImage;
    public GifImageLoadAlt(ResourceLocation resourceLocation,BufferedImage bufferedImage) {
        super(resourceLocation);
        this.bufferedImage = bufferedImage;
    }

    @Override
    public void loadTexture(IResourceManager iResourceManager) throws IOException
    {
        this.deleteGlTexture();

        try
        {
            if(bufferedImage != null) {
                    TextureUtil.uploadTextureImageAllocate(this.getGlTextureId(), bufferedImage, false, false);
            }
        }
        finally
        {
            if (bufferedImage != null)
            {
                //
            }
        }
    }

}
