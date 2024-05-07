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
    public float dw;
    public float dh;

    public EmojiEntity(String emojiName, int index,float dw,float dh) {
        this.emojiName = emojiName;
        this.index = index;
        this.dw = dw;
        this.dh = dh;
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

    public float getDw() {
        return dw;
    }

    public void setDw(float dw) {
        this.dw = dw;
    }

    public float getDh() {
        return dh;
    }

    public void setDh(float dh) {
        this.dh = dh;
    }
}
