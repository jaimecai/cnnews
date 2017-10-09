package com.cai.news.foreground.beans;

import com.cai.news.beans.News;
import com.cai.news.beans.NewsType;

import java.util.ArrayList;

public class IndexListItemBean {
    NewsType newsType;
    ArrayList<News> leftNews;
    ArrayList<News> rightNews;

    public NewsType getNewsType() {
        return newsType;
    }

    public void setNewsType(NewsType newsType) {
        this.newsType = newsType;
    }

    public ArrayList<News> getLeftNews() {
        return leftNews;
    }

    public void setLeftNews(ArrayList<News> leftNews) {
        this.leftNews = leftNews;
    }

    public ArrayList<News> getRightNews() {
        return rightNews;
    }

    public void setRightNews(ArrayList<News> rightNews) {
        this.rightNews = rightNews;
    }
}
