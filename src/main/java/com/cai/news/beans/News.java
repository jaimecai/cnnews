package com.cai.news.beans;

public class News {
    private Integer id;

    private String st;

    private String author;

    private NewsType newsType;

    private Integer browserCount;

    private String title;

    private String picture;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSt() {
        return st;
    }

    public void setSt(String st) {
        this.st = st == null ? null : st.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public Integer getBrowserCount() {
        return browserCount;
    }

    public void setBrowserCount(Integer browserCount) {
        this.browserCount = browserCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public NewsType getNewsType() {
        return newsType;
    }

    public void setNewsType(NewsType newsType) {
        this.newsType = newsType;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}