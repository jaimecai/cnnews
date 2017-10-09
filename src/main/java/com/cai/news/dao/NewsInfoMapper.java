package com.cai.news.dao;

import com.cai.news.beans.NewsInfo;

public interface NewsInfoMapper {
   NewsInfo selectByNewsId(Integer newsId);
   int insert(NewsInfo newsInfo);
   int updateByPrimaryKey(NewsInfo newsInfo);
}