package com.mayihavek.customemoji.config;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * @author MayIHaveK
 * @Date 2024/5/5 21:23
 */
public class ConfigLoader {

    public static String[] emojiList = new String[]{};
    public static String judgeChar = ":";
    public static float OffsetX = 0.0f;
    public static float OffsetY = 1.0f;
    public static float width = 9.0f;
    public static float height = 9.0f;

    public static void synchronizeConfiguration(File configFile) {
        Configuration configuration = new Configuration(configFile);
        emojiList = configuration.getStringList("emojiList", Configuration.CATEGORY_GENERAL, new String[]{"1:65537:32:32", "2:65538:16:16","wd/6:65539:8:8","dbc/73:65540:16:16"}, "自定义表情列表 - 表情名:表情索引:显示的宽度:显示的高度");
        judgeChar = configuration.getString("judgeChar", Configuration.CATEGORY_GENERAL, ":", "判断字符 - 用于判断是否为表情符号");
        OffsetX = configuration.getFloat("OffsetX", Configuration.CATEGORY_GENERAL, 0.0f, -1000.0f, 1000.0f, "自定义表情偏移 - X轴");
        OffsetY = configuration.getFloat("OffsetY", Configuration.CATEGORY_GENERAL, 1.0f, -1000.0f, 1000.0f, "自定义表情偏移 - Y轴");
        width = configuration.getFloat("width", Configuration.CATEGORY_GENERAL, 9.0f, 1.0f, 100.0f, "自定义表情尺寸 - 宽度");
        height = configuration.getFloat("height", Configuration.CATEGORY_GENERAL, 9.0f, 1.0f, 100.0f, "自定义表情尺寸 - 高度");
        if (configuration.hasChanged()) {
            configuration.save();
        }
    }

}
