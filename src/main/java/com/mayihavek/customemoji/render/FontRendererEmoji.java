package com.mayihavek.customemoji.render;

import com.mayihavek.customemoji.config.ConfigLoader;
import com.mayihavek.customemoji.entity.EmojiEntity;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author MayIHaveK
 * @Date 2024/5/5 19:46
 */
public class FontRendererEmoji extends FontRenderer {

    public FontRendererEmoji(GameSettings gameSettings, ResourceLocation locationFontTexture, TextureManager renderEngine, boolean unicodeFlag)
    {
        super(gameSettings, locationFontTexture, renderEngine, unicodeFlag);
    }

    @Override
    protected void renderStringAtPos(String text, boolean hasShadow){
        for (int i = 0; i < text.length(); ++i)
        {
            char c0 = text.charAt(i);
            int j;
            int k;

            if (c0 == 167 && i + 1 < text.length())
            {
                j = "0123456789abcdefklmnor".indexOf(text.toLowerCase().charAt(i + 1));

                if (j < 16)
                {
                    this.randomStyle = false;
                    this.boldStyle = false;
                    this.strikethroughStyle = false;
                    this.underlineStyle = false;
                    this.italicStyle = false;

                    if (j < 0 || j > 15)
                    {
                        j = 15;
                    }

                    if (hasShadow)
                    {
                        j += 16;
                    }

                    k = this.colorCode[j];
                    this.textColor = k;
                    setColor((float)(k >> 16) / 255.0F, (float)(k >> 8 & 255) / 255.0F, (float)(k & 255) / 255.0F, this.alpha);
                }
                else if (j == 16)
                {
                    this.randomStyle = true;
                }
                else if (j == 17)
                {
                    this.boldStyle = true;
                }
                else if (j == 18)
                {
                    this.strikethroughStyle = true;
                }
                else if (j == 19)
                {
                    this.underlineStyle = true;
                }
                else if (j == 20)
                {
                    this.italicStyle = true;
                }
                else if (j == 21)
                {
                    this.randomStyle = false;
                    this.boldStyle = false;
                    this.strikethroughStyle = false;
                    this.underlineStyle = false;
                    this.italicStyle = false;
                    setColor(this.red, this.blue, this.green, this.alpha);
                }

                ++i;
            }
            else
            {
                j = "\u00c0\u00c1\u00c2\u00c8\u00ca\u00cb\u00cd\u00d3\u00d4\u00d5\u00da\u00df\u00e3\u00f5\u011f\u0130\u0131\u0152\u0153\u015e\u015f\u0174\u0175\u017e\u0207\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000\u00c7\u00fc\u00e9\u00e2\u00e4\u00e0\u00e5\u00e7\u00ea\u00eb\u00e8\u00ef\u00ee\u00ec\u00c4\u00c5\u00c9\u00e6\u00c6\u00f4\u00f6\u00f2\u00fb\u00f9\u00ff\u00d6\u00dc\u00f8\u00a3\u00d8\u00d7\u0192\u00e1\u00ed\u00f3\u00fa\u00f1\u00d1\u00aa\u00ba\u00bf\u00ae\u00ac\u00bd\u00bc\u00a1\u00ab\u00bb\u2591\u2592\u2593\u2502\u2524\u2561\u2562\u2556\u2555\u2563\u2551\u2557\u255d\u255c\u255b\u2510\u2514\u2534\u252c\u251c\u2500\u253c\u255e\u255f\u255a\u2554\u2569\u2566\u2560\u2550\u256c\u2567\u2568\u2564\u2565\u2559\u2558\u2552\u2553\u256b\u256a\u2518\u250c\u2588\u2584\u258c\u2590\u2580\u03b1\u03b2\u0393\u03c0\u03a3\u03c3\u03bc\u03c4\u03a6\u0398\u03a9\u03b4\u221e\u2205\u2208\u2229\u2261\u00b1\u2265\u2264\u2320\u2321\u00f7\u2248\u00b0\u2219\u00b7\u221a\u207f\u00b2\u25a0\u0000".indexOf(c0);

                if (this.randomStyle && j != -1)
                {
                    do
                    {
                        k = this.fontRandom.nextInt(this.charWidth.length);
                    }
                    while (this.charWidth[j] != this.charWidth[k]);

                    j = k;
                }

                float f1 = this.unicodeFlag ? 0.5F : 1.0F;
                boolean flag1 = (c0 == 0 || j == -1 || this.unicodeFlag) && hasShadow;

                if (flag1)
                {
                    this.posX -= f1;
                    this.posY -= f1;
                }
                float f;

                //如果包含 :text: 这种表情符号，则渲染表情符号，并跳过后面的字符的循环
                if(c0 == ConfigLoader.judgeChar.charAt(0)){
                    //从当前这位开始查找
                    String string = parseText(text.substring(i));
                    ConcurrentHashMap<String, EmojiEntity> map = EmojiEntity.emojiMap;
                    if(!"".equals(string) && map.containsKey(string)){
                        EmojiEntity entity = map.get(string);
                        f = this.renderCharAtPos(entity.getIndex(), c0, this.italicStyle,entity.getDw(),entity.getDh());
                        //跳过后面的字符
                        i+=string.length()+1;
                    }else{
                        f = this.renderCharAtPos(j, c0, this.italicStyle,0,0);
                    }
                }else{
                    f = this.renderCharAtPos(j, c0, this.italicStyle,0,0);
                }

                if (flag1)
                {
                    this.posX += f1;
                    this.posY += f1;
                }

                if (this.boldStyle)
                {
                    this.posX += f1;

                    if (flag1)
                    {
                        this.posX -= f1;
                        this.posY -= f1;
                    }

                    this.renderCharAtPos(j, c0, this.italicStyle,0,0);
                    this.posX -= f1;

                    if (flag1)
                    {
                        this.posX += f1;
                        this.posY += f1;
                    }

                    ++f;
                }

                doDraw(f);
            }
        }
    }

    private float renderCharAtPos(int index, char c, boolean hasShadow,float dw, float dh)
    {
        ConcurrentHashMap<Integer, EmojiEntity> indexMap = EmojiEntity.emojiIndexMap;
        if(indexMap.get(index) != null){
            //绑定贴图
            this.bindTexture(new ResourceLocation("customemoji","textures/" + indexMap.get(index).getEmojiName() + ".png"));
            return renderEmoji(this.posX + ConfigLoader.OffsetX, this.posY - ConfigLoader.OffsetY,dw,dh);
        }
        return c == 32 ? 4.0F : ("\u00c0\u00c1\u00c2\u00c8\u00ca\u00cb\u00cd\u00d3\u00d4\u00d5\u00da\u00df\u00e3\u00f5\u011f\u0130\u0131\u0152\u0153\u015e\u015f\u0174\u0175\u017e\u0207\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000\u00c7\u00fc\u00e9\u00e2\u00e4\u00e0\u00e5\u00e7\u00ea\u00eb\u00e8\u00ef\u00ee\u00ec\u00c4\u00c5\u00c9\u00e6\u00c6\u00f4\u00f6\u00f2\u00fb\u00f9\u00ff\u00d6\u00dc\u00f8\u00a3\u00d8\u00d7\u0192\u00e1\u00ed\u00f3\u00fa\u00f1\u00d1\u00aa\u00ba\u00bf\u00ae\u00ac\u00bd\u00bc\u00a1\u00ab\u00bb\u2591\u2592\u2593\u2502\u2524\u2561\u2562\u2556\u2555\u2563\u2551\u2557\u255d\u255c\u255b\u2510\u2514\u2534\u252c\u251c\u2500\u253c\u255e\u255f\u255a\u2554\u2569\u2566\u2560\u2550\u256c\u2567\u2568\u2564\u2565\u2559\u2558\u2552\u2553\u256b\u256a\u2518\u250c\u2588\u2584\u258c\u2590\u2580\u03b1\u03b2\u0393\u03c0\u03a3\u03c3\u03bc\u03c4\u03a6\u0398\u03a9\u03b4\u221e\u2205\u2208\u2229\u2261\u00b1\u2265\u2264\u2320\u2321\u00f7\u2248\u00b0\u2219\u00b7\u221a\u207f\u00b2\u25a0\u0000".indexOf(c) != -1 && !this.unicodeFlag ? this.renderDefaultChar(index, hasShadow) : this.renderUnicodeChar(c, hasShadow));
    }

    private float renderEmoji(float x, float y,float dw, float dh) {
        float heigth = ConfigLoader.height + dh;
        float width = ConfigLoader.width + dw;
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(x, y + heigth, 0.0D, 0.0D, 1.0D);
        tessellator.addVertexWithUV(x + width, y + heigth, 0.0D, 1.0D, 1.0D);
        tessellator.addVertexWithUV(x + width, y, 0.0D, 1.0D, 0.0D);
        tessellator.addVertexWithUV(x, y, 0.0D, 0.0D, 0.0D);
        tessellator.draw();
        GL11.glDisable(GL11.GL_BLEND);
        return width; // Return the width of the emoji
    }

    public String parseText(String text) {
        char judgeChar = ConfigLoader.judgeChar.charAt(0);
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == judgeChar) {
                int start = i;
                i++;
                while (i < text.length() && text.charAt(i) != judgeChar) {
                    i++;
                }
                if (i < text.length() && text.charAt(i) == judgeChar) {
                    return text.substring(start + 1, i);
                }
            }
        }
        return "";
    }


}
