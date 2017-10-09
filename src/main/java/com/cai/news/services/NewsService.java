package com.cai.news.services;


import com.cai.news.beans.News;
import com.cai.news.beans.NewsInfo;
import com.cai.news.dao.NewsInfoMapper;
import com.cai.news.dao.NewsMapper;
import com.cai.news.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

public class NewsService {

    public ArrayList<News> findAll() throws Exception {
        ArrayList<News> arrs = new ArrayList<News>();
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        try {
            NewsMapper newsMapper = sqlSession.getMapper(NewsMapper.class);
            arrs = newsMapper.selectAll();
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
        return arrs;
    }

    public boolean add(News news, NewsInfo newsInfo) throws Exception {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        NewsMapper newsMapper = sqlSession.getMapper(NewsMapper.class);
        try {
            newsMapper.insert(news);
            NewsInfoMapper newsInfoMapper = sqlSession.getMapper(NewsInfoMapper.class);
            newsInfo.setNews(news);
            newsInfoMapper.insert(newsInfo);
            sqlSession.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception() {
                @Override
                public String getMessage() {
                    return "添加错误";
                }
            };
        } finally {
            sqlSession.close();
        }
    }

    public ArrayList<News> findByNewsTypeAnd1to3(int newsTypeId) throws Exception {
        ArrayList<News> temp = new ArrayList<News>();
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        try {
            NewsMapper newsMapper = sqlSession.getMapper(NewsMapper.class);
            temp = newsMapper.selectByNewsTypeAnd1to3(newsTypeId);
        } catch (Exception e) {

            e.printStackTrace();

            temp = new ArrayList<News>();
            throw new Exception() {

                @Override
                public String getMessage() {
                    return "查询数据库出现了问题";
                }
            };
        } finally {
            sqlSession.close();
        }

        return temp;

    }

    public ArrayList<News> findByNewsTypeAnd4to6(int newsTypeId) throws Exception {

        ArrayList<News> temp = new ArrayList<News>();
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

        try {
            NewsMapper newsMapper = sqlSession.getMapper(NewsMapper.class);
            temp = newsMapper.selectByNewsTypeAnd4to6(newsTypeId);
        } catch (Exception e) {
            e.printStackTrace();
            temp = new ArrayList<News>();
            throw new Exception() {

                @Override
                public String getMessage() {
                    // TODO Auto-generated method stub
                    return "查询数据库出现了问题";
                }
            };
        } finally {
            sqlSession.close();
        }

        return temp;

    }

    public ArrayList<News> findByNewsTypeId(int newsTypeId) throws Exception {
        ArrayList<News> result = new ArrayList<News>();
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        try {
            NewsMapper newsMapper = sqlSession.getMapper(NewsMapper.class);
            result = newsMapper.selectByNewsTypeId(newsTypeId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception() {
                @Override
                public String getMessage() {
                    return "查询数据库出现问题！";
                }
            };
        } finally {
            sqlSession.close();
        }
        return result;
    }

    public ArrayList<News> findByNewsTop10(int newsTypeId) throws Exception {
        ArrayList<News> result = new ArrayList<News>();
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        try {
            NewsMapper newsMapper = sqlSession.getMapper(NewsMapper.class);
            result = newsMapper.selectByNewsTop10(newsTypeId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception() {
                @Override
                public String getMessage() {
                    return "查询数据库出现问题！";
                }
            };
        } finally {
            sqlSession.close();
        }
        return result;
    }

    public ArrayList<News> findByNewsTypeIdAndBrowserTop10(int newsTypeId) throws Exception {
        ArrayList<News> result = new ArrayList<News>();
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        try {
            NewsMapper newsMapper = sqlSession.getMapper(NewsMapper.class);
            result = newsMapper.selectByNewsTypeIdAndBrowserTop10(newsTypeId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception() {
                @Override
                public String getMessage() {
                    return "查询数据库出现问题！";
                }
            };
        } finally {
            sqlSession.close();
        }
        return result;
    }

    public boolean deleteById(int id, String picturePath) throws Exception {
        boolean result = false;
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        try {
            NewsMapper newsMapper = sqlSession.getMapper(NewsMapper.class);
            result = newsMapper.deleteById(id) > 0 ? true : false;
            File picture = new File("/opt/tomcat/webapps/upload/cnnews/" + picturePath);
            if (picture.exists())
                picture.delete();
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception() {
                @Override
                public String getMessage() {
                    return "删除失败！";
                }
            };
        } finally {
            sqlSession.close();
        }
        return result;
    }

    public News findById(int id) throws Exception {
        News result = null;
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        try {
            NewsMapper newsMapper = sqlSession.getMapper(NewsMapper.class);
            result = newsMapper.selectById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception() {
                @Override
                public String getMessage() {
                    return "查询数据库出现问题！";
                }
            };
        } finally {
            sqlSession.close();
        }
        return result;
    }

    public boolean edit(NewsInfo newsInfo, Boolean isPicture, HttpServletRequest request) throws Exception {
        boolean result = false;
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        String oldPicture = newsInfo.getNews().getPicture();
        String fileName = "";
        //1.上传文件
        if (isPicture) {
            try {
                Part part = request.getPart("picture");
                fileName = UUID.randomUUID().toString();
                String fileExtension = part.getHeader("Content-Type");
                if (fileExtension.equalsIgnoreCase("image/tiff")) {
                    fileName += ".tif";
                }
                if (fileExtension.equalsIgnoreCase("image/fax")) {
                    fileName += ".fax";
                }
                if (fileExtension.equalsIgnoreCase("image/gif")) {
                    fileName += ".gif";
                }
                if (fileExtension.equalsIgnoreCase("image/x-icon")) {
                    fileName += ".ico";
                }
                if (fileExtension.equalsIgnoreCase("image/jpeg")) {
                    fileName += ".jpg";
                }
                if (fileExtension.equalsIgnoreCase("image/pnetvue")) {
                    fileName += ".net";
                }
                if (fileExtension.equalsIgnoreCase("image/png")) {
                    fileName += ".png";
                }
                if (fileExtension.equalsIgnoreCase("image/vnd.rn-realpix")) {
                    fileName += ".rp";
                }
                if (fileExtension.equalsIgnoreCase("image/vnd.wap.wbmp")) {
                    fileName += ".wbmp";
                }

                if (fileExtension.equalsIgnoreCase("image/bmp")) {
                    fileName += ".bmp";
                }
                String savePath = "/opt/tomcat/webapps/upload/cnnews/";
                part.write(savePath + fileName);
                //设置新文件名
                newsInfo.getNews().setPicture(fileName);
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception(){
                    @Override
                    public String getMessage() {
                        return "上传出错!";
                    }
                };
            }
        }
        //2.修改
        try {
            NewsMapper newsMapper = sqlSession.getMapper(NewsMapper.class);
            NewsInfoMapper newsInfoMapper = sqlSession.getMapper(NewsInfoMapper.class);
            newsMapper.updateByPrimaryKey(newsInfo.getNews());
            newsInfoMapper.updateByPrimaryKey(newsInfo);
            sqlSession.commit();

            //删除旧文件
            if (isPicture) {
                File file = new File("/upload/" + oldPicture);
                if (file.exists()) {
                    try {
                        file.delete();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            result=false;
            //3.修改失败删除新文件
            if(isPicture){
                 File file = new File("/upload/" + fileName);
                if (file.exists()) {
                    try {
                        file.delete();
                    } catch (Exception e2) {
                        e2.printStackTrace();

                    }
                }
                 throw new Exception(){
                    @Override
                    public String getMessage() {
                        return "修改错误!";
                    }
                };
            }
        }finally {
            sqlSession.close();
        }
        return result;
    }
}
