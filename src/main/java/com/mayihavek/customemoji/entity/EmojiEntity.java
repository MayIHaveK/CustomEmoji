package com.mayihavek.customemoji.entity;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author MayIHaveK
 * @Date 2024/5/5 20:59
 */
public class EmojiEntity {
    public static ConcurrentHashMap<String, EmojiEntity> emojiMap = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<Integer, EmojiEntity> emojiIndexMap = new ConcurrentHashMap<>();
    public String emojiName;
    public int index;

    public EmojiEntity(String emojiName, int index) {
        this.emojiName = emojiName;
        this.index = index;
    }

    public String getEmojiName() {
        return emojiName;
    }

    public void setEmojiName(String emojiName) {
        this.emojiName = emojiName;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
