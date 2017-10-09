package com.cai.news.services;

import com.cai.news.beans.NewsType;
import com.cai.news.dao.NewsTypeMapper;
import com.cai.news.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;

public class NewsTypeService {
    public boolean add(NewsType newsType) throws Exception {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        boolean result = false;
        try {
            NewsTypeMapper newsTypeMapper = sqlSession.getMapper(NewsTypeMapper.class);
            result = newsTypeMapper.insert(newsType) > 0 ? true : false;
            sqlSession.commit();
        } catch (Exception e) {
            throw new Exception() {
                @Override
                public String getMessage() {
                    return "该新闻类型已存在！";
                }
            };
        } finally {
            sqlSession.close();
        }

        return result;
    }

    public ArrayList<NewsType> findAll() throws Exception {
        ArrayList<NewsType> arrs = new ArrayList<NewsType>();
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        try {
            NewsTypeMapper newsTypeMapper = sqlSession.getMapper(NewsTypeMapper.class);
            arrs = newsTypeMapper.selectAll();
        } catch (Exception e) {
            throw new Exception() {
                @Override
                public String getMessage() {
                    return "查询数据库出现问题";
                }
            };
        } finally {
            sqlSession.close();
        }
        return arrs;
    }

    public NewsType findByName(String name) throws Exception {
        NewsType newsType = null;
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        try {
            NewsTypeMapper newsTypeMapper = sqlSession.getMapper(NewsTypeMapper.class);
            newsType = newsTypeMapper.selectByName(name);
        } catch (Exception e) {
            throw new Exception() {
                @Override
                public String getMessage() {
                    return "查询数据库出现问题";
                }
            };
        } finally {
            sqlSession.close();
        }
        return newsType;
    }

    public NewsType findById(int id) throws Exception {
        NewsType newsType = null;
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        try {
            NewsTypeMapper newsTypeMapper = sqlSession.getMapper(NewsTypeMapper.class);
            newsType = newsTypeMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new Exception() {
                @Override
                public String getMessage() {
                    return "查询数据库出现问题";
                }
            };
        } finally {
            sqlSession.close();
        }
        return newsType;
    }
    public boolean deleteNewsTypeById(int id) throws Exception {
        boolean result = false;
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        try {
            NewsTypeMapper newsTypeMapper = sqlSession.getMapper(NewsTypeMapper.class);
            result = newsTypeMapper.deleteByPrimaryKey(id) > 0 ? true : false;
            sqlSession.commit();
        } catch (Exception e) {
            throw new Exception() {
                @Override
                public String getMessage() {
                    return "有新闻内容无法删除";
                }
            };
        } finally {
            sqlSession.close();
        }
        return result;
    }

    public boolean edit(NewsType newsType) throws Exception {
        boolean result = false;
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        try {
            NewsTypeMapper newsTypeMapper = sqlSession.getMapper(NewsTypeMapper.class);
            result = newsTypeMapper.updateByPrimaryKey(newsType) > 0 ? true : false;
            sqlSession.commit();
        } catch (Exception e) {
            throw new Exception() {
                @Override
                public String getMessage() {
                    return "该名称已存在！";
                }
            };
        } finally {
            sqlSession.close();
        }
        return result;
    }
}
