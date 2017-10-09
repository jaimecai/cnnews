package com.cai.news.servlet;


import com.cai.news.beans.News;
import com.cai.news.beans.NewsInfo;
import com.cai.news.beans.NewsType;
import com.cai.news.services.NewsInfoService;
import com.cai.news.services.NewsService;
import com.cai.news.services.NewsTypeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "InfoPageServlet", urlPatterns = {"/InfoPageServlet.action"})
public class InfoPageServlet extends HttpServlet {
    NewsInfoService newsInfoService=new NewsInfoService();
    NewsService newsService=new NewsService();
    NewsTypeService newsTypeService=new NewsTypeService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean tag = true;
        String msg;
        String newsIdRec = request.getParameter("newsId");
        if (newsIdRec == null || newsIdRec.trim().equals("")) {
            tag = false;
            msg = "输入的不能为空！";
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("/msg.jsp").forward(request, response);
        }
        int newsId = -1;
        if (tag) {
            try {
                newsId = Integer.parseInt(newsIdRec);
            } catch (Exception e) {
                tag = false;
                msg = "输入的id有问题！";
                request.setAttribute("msg", msg);
                request.getRequestDispatcher("/msg.jsp").forward(request, response);
            }
        }
        if (tag) {
            try {
                NewsInfo newsInfo=newsInfoService.findById(newsId);
                News news=newsInfo.getNews();
                NewsType newsType = news.getNewsType();
                ArrayList<News> newsTypeBrowserTop10 = newsService.findByNewsTypeIdAndBrowserTop10(newsType.getId());
                ArrayList<News> newsTypeLatestTop10 = newsService.findByNewsTop10(newsType.getId());
                ArrayList<NewsType> newsTypes=newsTypeService.findAll();
                request.setAttribute("news",news);
                request.setAttribute("newsType",newsType);
                request.setAttribute("newsTypeBrowserTop10",newsTypeBrowserTop10);
                request.setAttribute("newsTypeLatestTop10",newsTypeLatestTop10);
                request.setAttribute("newsTypes",newsTypes);
                request.setAttribute("newsInfo",newsInfo);
                request.getRequestDispatcher("/info.jsp").forward(request,response);
            } catch (Exception e) {
                msg = e.getMessage();
                request.setAttribute("msg", msg);
                request.getRequestDispatcher("/msg.jsp").forward(request, response);
            }
        }
    }
}
