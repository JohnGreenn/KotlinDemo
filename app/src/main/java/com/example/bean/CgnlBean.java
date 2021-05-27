package com.example.bean;

import java.util.List;

/**
 * 描述：
 * fileName：com.example.bean
 * author：Hujm
 * 添加版本：V4.2.12
 * time：2021/05/31 15:26
 */
public class CgnlBean {
    private int id;
    private String thumbnail;
    private String name;
    private int post_hits;
    private String post_excerpt;
    private String post_title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPost_hits() {
        return post_hits;
    }

    public void setPost_hits(int post_hits) {
        this.post_hits = post_hits;
    }

    public String getPost_excerpt() {
        return post_excerpt;
    }

    public void setPost_excerpt(String post_excerpt) {
        this.post_excerpt = post_excerpt;
    }

    public String getPost_title() {
        return post_title;
    }

    public void setPost_title(String post_title) {
        this.post_title = post_title;
    }
}
