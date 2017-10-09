package com.cai.news.services;

import com.cai.news.beans.NewsInfo;
import com.cai.news.dao.NewsInfoMapper;
import com.cai.news.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

public class NewsInfoService {
     public NewsInfo findById(Integer id) throws Exception {
        NewsInfo newsInfo;
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        try {
            NewsInfoMapper newsInfoMapper = sqlSession.getMapper(NewsInfoMapper.class);
            newsInfo= newsInfoMapper.selectByNewsId(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception() {
                @Override
                public String getMessage() {
                    return "查询数据库出现问题";
                }
            };
        } finally {
            sqlSession.close();
        }
        return newsInfo;
    }
}
