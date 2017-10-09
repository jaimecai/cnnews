package com.cai.news.beans;

public class NewsInfo {
    private Integer id;

    private News news;

    private String info;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }
}