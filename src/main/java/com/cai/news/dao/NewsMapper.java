package com.cai.news.dao;

import com.cai.news.beans.News;

import java.util.ArrayList;

public interface NewsMapper {
    ArrayList<News> selectAll();
    int insert(News news);
    int deleteById(int id);

    News selectById(int id);

    ArrayList<News> selectByNewsTypeAnd1to3(int newsTypeId);
    ArrayList<News> selectByNewsTypeAnd4to6(int newsTypeId);

    ArrayList<News> selectByNewsTypeId(int newsTypeId);
    ArrayList<News> selectByNewsTop10(int newsTypeId);
    ArrayList<News> selectByNewsTypeIdAndBrowserTop10(int newsTypeId);

    int updateByPrimaryKey(News news);
}