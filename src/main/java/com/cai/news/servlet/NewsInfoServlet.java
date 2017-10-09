package com.cai.news.servlet;

import com.cai.news.beans.NewsInfo;
import com.cai.news.services.NewsInfoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "NewsInfoServlet", urlPatterns = "/admin/NewsInfoServlet.action")
public class NewsInfoServlet extends HttpServlet {
    public static final String FIND_BY_ID = "findById";
    HttpServletRequest request;
    HttpServletResponse response;
    String method;
    String msg;
    boolean tag;
    NewsInfoService newsInfoService = new NewsInfoService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        tag = true;
        this.request = request;
        this.response = response;
        methodParameter();
        if (tag) {
            if (method.equals(FIND_BY_ID))
                findById();
            else {
                msg = "你选择服务不存在！";
                request.setAttribute("msg", msg);
                request.getRequestDispatcher("/msg.jsp").forward(request, response);
            }
        }
    }

    private void findById() throws ServletException, IOException {
        String newsId = request.getParameter("newsId");
        if (newsId == null || newsId.equals("")) {
            msg = "你的参数有问题！";
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("/msg.jsp").forward(request, response);
            return;
        }
        Integer tempNewsId=-1;
        try {
            tempNewsId = Integer.parseInt(newsId);
        }catch (Exception e){
            msg = "你的参数有问题！";
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("/msg.jsp").forward(request, response);
            return;
        }
        try {
            NewsInfo newsInfo=newsInfoService.findById(tempNewsId);
            request.setAttribute("newsInfo",newsInfo);
            request.getRequestDispatcher("/admin/newsInfo.jsp").forward(request,response);
        } catch (Exception e) {
            msg=e.getMessage();
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("/msg.jsp").forward(request, response);
        }

    }

    private void methodParameter() throws ServletException, IOException {
        method = request.getParameter("method");
        if (method == null || method.equals("")) {
            msg = "你没有选择任何服务！";
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("/msg.jsp").forward(request, response);
            tag = false;
        }
    }
}
