package com.cai.news.servlet;

import com.cai.news.beans.News;
import com.cai.news.beans.NewsType;
import com.cai.news.services.NewsService;
import com.cai.news.services.NewsTypeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "NewsPageServlet", urlPatterns = "/NewsPageServlet.action")
public class NewsPageServlet extends HttpServlet {
    HttpServletRequest request;
    HttpServletResponse response;
    NewsTypeService newsTypeService=new NewsTypeService();
    NewsService newsService=new NewsService();
    String msg;
    boolean tag;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        tag = true;
        String newsTypeId = request.getParameter("newsTypeId");
        if (newsTypeId == null || newsTypeId.equals("")) {
            msg = "参数不能为空";
            tag = false;
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("/msg.jsp").forward(request, response);
        }
        int newsTypeIdInt= -1;
		if (tag) {
			try {
			   newsTypeIdInt = Integer.parseInt(newsTypeId);
			} catch (Exception e) {
				msg= "你的参数有问题,不是个整数";
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("msg.jsp").forward(request, response);
				tag = false;
			}

		}
        if (tag) {
            try {
                ArrayList<News> allNews = newsService.findByNewsTypeId(newsTypeIdInt);
                ArrayList<News> latestNews = newsService.findByNewsTop10(newsTypeIdInt);
                ArrayList<News> hottestNews = newsService.findByNewsTypeIdAndBrowserTop10(newsTypeIdInt);
                NewsType newsType = newsTypeService.findById(newsTypeIdInt);
                ArrayList<NewsType> newsTypes=newsTypeService.findAll();
                request.setAttribute("allNews", allNews);
                request.setAttribute("latestNews", latestNews);
                request.setAttribute("hottestNews", hottestNews);
                request.setAttribute("newsType", newsType);
                request.setAttribute("newsTypes", newsTypes);
                request.getRequestDispatcher("/newsPage.jsp").forward(request, response);
            } catch (Exception e) {
                msg=e.getMessage();
                request.setAttribute("msg", msg);
                request.getRequestDispatcher("/msg.jsp").forward(request, response);
            }
        }
    }
}
