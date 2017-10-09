package com.cai.news.servlet;

import com.cai.news.beans.NewsType;
import com.cai.news.services.NewsTypeService;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "NewsTypeServlet", urlPatterns = "/admin/NewsTypeServlet.action")
public class NewsTypeServlet extends HttpServlet {
    public static final String ADD = "addNewsType";
    public static final String DELETE = "deleteNewsType";
    public static final String EDITE = "editNewsType";
    public static final String FINDALL = "findAll";
    HttpServletRequest req;
    HttpServletResponse resp;
    NewsTypeService newsTypeService;
    String method;
    String newsType;
    boolean tag;

    String msg;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        tag = true;
        this.req = req;
        this.resp = resp;
        newsType = req.getParameter("newsType");
        method = req.getParameter("method");
        newsTypeService = new NewsTypeService();
        methodParameter();
        if (tag) {
            if (method.equals(ADD))
                add();
            else if (method.equals(FINDALL))
                findAll();
            else if (method.equals(DELETE))
                deleteNewsType();
            else if (method.equals(EDITE))
                editNewsType();
            else {
                msg = "你选择服务不存在！";
                req.setAttribute("msg", msg);
                req.getRequestDispatcher("/Error404.jsp").forward(req, resp);
            }
        }
    }

    private void editNewsType() throws IOException {
        String stat = "1";
        boolean tempTag = true;
        String id = req.getParameter("id").trim();
        String name = req.getParameter("name").trim();
        if (id == null || id == ""||name==null||name=="") {
            msg = "输入的名称不能为空!";
            stat = "1";
            tag = false;
        }
        NewsType newsType = new NewsType();
        if (tempTag) {
            try {
                int tempId = Integer.parseInt(id);
                newsType.setId(tempId);
            }catch (Exception e){
                msg="参数转换出问题！";
                stat="2";
                tag=false;
            }
            newsType.setName(name);
        }
        if(tempTag){
             try {
                newsTypeService.edit(newsType);
                msg="修改成功！";
                stat="0";
            } catch (Exception e) {
                 msg=e.getMessage();
                 stat="3";
            }
        }
        JSONObject jsonObject=new JSONObject();
        jsonObject.accumulate("msg",msg);
        jsonObject.accumulate("stat",stat);
        PrintWriter out=resp.getWriter();
        out.write(jsonObject.toString());
        out.flush();
        out.close();
    }

    private void deleteNewsType() throws IOException {
        int stat = 1;
        String id = req.getParameter("id");
        boolean tempTag = true;
        if (id == null || id == "") {
            msg = "你的操作参数错误";
            stat = 1;
            tempTag = false;
        }
        int tempId = -1;
        if (tempTag) {
            try {
                tempId = Integer.parseInt(id);
            } catch (Exception e) {
                msg = "你的操作参数转型错误";
                stat = 2;
                tempTag = false;
            }
        }
        if (tempTag) {
            try {
                if (newsTypeService.deleteNewsTypeById(tempId)) {
                    msg = "删除成功！";
                    stat = 0;
                } else {
                    msg = "删除失败！";
                    stat = 3;
                }
            } catch (Exception e) {
                msg = e.getMessage();
                stat = 4;
            }
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.accumulate("msg", msg);
        jsonObject.accumulate("stat", stat);
        PrintWriter out = resp.getWriter();
        out.write(jsonObject.toString());
        out.flush();
        out.close();
    }

    private void findAll() throws ServletException, IOException {
        ArrayList<NewsType> arr = null;
        try {
            arr = newsTypeService.findAll();
            req.setAttribute("arr", arr);
            req.getRequestDispatcher("/admin/index.jsp").forward(req, resp);
        } catch (Exception e) {
            String msg = e.getMessage();
            req.setAttribute("msg", msg);
            req.getRequestDispatcher("msg.jsp").forward(req, resp);

        }

    }

    private void add() {
        boolean tag=true;
        int stat=1;
        JSONObject jsonObject = new JSONObject();

        if (newsType == null || newsType.trim().equals("")) {
            msg = "添加的新闻类型不能为空！";
            stat = 1;
            tag=false;
        }
        NewsType addNewsType = new NewsType();
        addNewsType.setName(newsType);
        if(tag){
           try {
            if (newsTypeService.add(addNewsType)) {
                msg = "成功添加新闻类型！";
                stat = 0;
                addNewsType = newsTypeService.findByName(addNewsType.getName());
                jsonObject.accumulate("newsTypeId", addNewsType.getId());
                jsonObject.accumulate("newsTypeName", addNewsType.getName());
            } else {
                msg = "添加失败！";
                stat = 2;
            }
        } catch (Exception e) {
            msg = e.getMessage();
            stat = 3;
        }
        }

        jsonObject.accumulate("msg", msg);
        jsonObject.accumulate("stat", stat);
        PrintWriter out = null;
        try {
            out = resp.getWriter();
            out.write(jsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (out != null) {
            out.flush();
            out.close();
        }
    }

    private void methodParameter() {
        if (method == null || method.equals("")) {
            try {
                resp.sendRedirect("/Error404.jsp");

            } catch (IOException e) {
                e.printStackTrace();

            }
            tag = false;
            return;
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
