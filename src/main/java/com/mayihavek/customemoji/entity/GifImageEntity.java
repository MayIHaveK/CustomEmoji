package com.mayihavek.customemoji.entity;

/**
 * @author MayIHaveK
 * @Date 2023/11/12 16:22
 */
public class GifImageEntity {
    private String name;
    private long cd;
    private int count;
    private int page;
    private long last_update_time;

    public GifImageEntity(String name, long cd, int count,int page,long last_update_time) {
        this.name = name;
        this.cd = cd;
        this.count = count;
        this.page = page;
        this.last_update_time = last_update_time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCd() {
        return cd;
    }

    public void setCd(long cd) {
        this.cd = cd;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public long getLast_update_time() {
        return last_update_time;
    }

    public void setLast_update_time(long last_update_time) {
        this.last_update_time = last_update_time;
    }
}
