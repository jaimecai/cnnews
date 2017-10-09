package com.cai.news.servlet;

import com.cai.news.beans.News;
import com.cai.news.beans.NewsType;
import com.cai.news.foreground.beans.IndexListItemBean;
import com.cai.news.services.NewsService;
import com.cai.news.services.NewsTypeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "IndexServlet", urlPatterns = "/IndexServlet.action")
public class IndexServlet extends HttpServlet {
    HttpServletRequest request;
    HttpServletResponse response;
    NewsTypeService newsTypeService = new NewsTypeService();
    NewsService newsService = new NewsService();
    String msg;
    boolean tag;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        tag = true;
        this.request = request;
        this.response = response;
        ArrayList<NewsType> newsTypes = null;
        try {
            newsTypes = newsTypeService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("/msg.jsp").forward(request, response);
            tag = false;
        }
        if (tag) {
            try {
                ArrayList<IndexListItemBean> beans=new ArrayList<IndexListItemBean>();
                for (NewsType newsType : newsTypes) {
                    IndexListItemBean indexListItemBean = new IndexListItemBean();
                    ArrayList<News> liftNews = newsService.findByNewsTypeAnd1to3(newsType.getId());
                    ArrayList<News> rightNews = newsService.findByNewsTypeAnd4to6(newsType.getId());
                    indexListItemBean.setNewsType(newsType);
                    indexListItemBean.setLeftNews(liftNews);
                    indexListItemBean.setRightNews(rightNews);
                    beans.add(indexListItemBean);
                }

                request.setAttribute("newsTypes", newsTypes);
                request.setAttribute("beans",beans);
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                msg=e.getMessage();
                request.setAttribute("msg",msg);
                request.getRequestDispatcher("/index.jsp").forward(request,response);
            }


        }

    }
}
