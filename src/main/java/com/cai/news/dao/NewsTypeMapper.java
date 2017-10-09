package com.cai.news.dao;

import com.cai.news.beans.NewsType;

import java.util.ArrayList;

public interface NewsTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NewsType record);

    int insertSelective(NewsType record);

    NewsType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NewsType record);

    int updateByPrimaryKey(NewsType record);
    ArrayList<NewsType> selectAll();
    NewsType selectByName(String name);

}