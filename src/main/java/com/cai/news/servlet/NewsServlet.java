package com.cai.news.servlet;

import com.cai.news.beans.News;
import com.cai.news.beans.NewsInfo;
import com.cai.news.beans.NewsType;
import com.cai.news.services.NewsInfoService;
import com.cai.news.services.NewsService;
import com.cai.news.services.NewsTypeService;
import com.cai.news.utils.ValidateUtil;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

//servlet3.0如果from带上传属性，那么想要取得正常的参数，必须加上@MultipartConfig注解，才能正常取参
@WebServlet(name = "NewsServlet", urlPatterns = "/admin/NewsServlet.action")
@MultipartConfig
public class NewsServlet extends HttpServlet {
    public static final String ADD_BEFORE = "addBefore";
    public static final String ADD = "add";
    public static final String DELETE = "delete";
    public static final String EDIT_BEFORE = "editBefore";
    public static final String EDIT = "edit";
    public static final String FINDALL = "findAll";

    public static final String MSG = "msg";
    public static final String STAT = "stat";
    HttpServletRequest request;
    HttpServletResponse response;
    String method;
    String msg;
    boolean tag;
    NewsService newsService = new NewsService();
    NewsTypeService newsTypeService = new NewsTypeService();
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
            if (method.equals(ADD)) {
                add();
            } else if (method.equals(ADD_BEFORE)) {
                addBefore();
            } else if (method.equals(DELETE)) {
                delete();

            } else if (method.equals(EDIT_BEFORE)) {
                editBefore();
            } else if (method.equals(EDIT)) {
                edit();

            } else if (method.equals(FINDALL)) {
                findAll();
            } else {
                msg = "你选择服务不存在！";
                request.setAttribute("msg", msg);
                request.getRequestDispatcher("/msg.jsp").forward(request, response);
            }
        }
    }

    private void edit() throws ServletException, IOException {
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String date = request.getParameter("datepicker");
        String browser = request.getParameter("browsercount");
        String newsTypeId = request.getParameter("newsTypeId");
        String info = request.getParameter("info");
        String oldPicture = request.getParameter("oldPicture");
        String newsId = request.getParameter("newsId");
        HashMap<String, String> parameters = new HashMap<String, String>();
        parameters.put("title", title);
        parameters.put("date", date);
        parameters.put("author", author);
        parameters.put("browser", browser);
        parameters.put("newsTypeId", newsTypeId);
        parameters.put("info", info);
        parameters.put("oldPicture", oldPicture);
        parameters.put("newsId", newsId);
        boolean isValid = ValidateUtil.validateNullOrEmpty(parameters);
        if (!isValid) {
            msg = "输入的参数不能为空！";
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("/msg.jsp").forward(request, response);
            return;
        }
        int idTemp = -1;
        int browserTemp = -1;
        int newsTypeIdTemp = -1;
        try {
            idTemp = Integer.parseInt(newsId);
            browserTemp = Integer.parseInt(browser);
            newsTypeIdTemp = Integer.parseInt(newsTypeId);
        } catch (Exception e) {
            e.printStackTrace();
            msg = "你输入参数有问题！";
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("/msg.jsp").forward(request, response);
            return;
        }
        String isPicture = request.getParameter("isPicture");
        boolean isPictureTemp = false;
        if (isPicture != null) {
            if (isPicture.equals("on")) {
                isPictureTemp = true;
                //判断有没有上传文件
                Part part = request.getPart("picture");
                if (part.getSize() == 0) {
                    msg = "请选择上传的文件！";
                    request.setAttribute("msg", msg);
                    request.getRequestDispatcher("/msg.jsp").forward(request, response);
                    return;
                }
            }
        }
        News news = new News();
        news.setId(idTemp);
        news.setTitle(title);
        news.setAuthor(author);
        news.setSt(date);
        news.setBrowserCount(browserTemp);
        news.setPicture(oldPicture);

        NewsType newsType = new NewsType();
        newsType.setId(newsTypeIdTemp);
        news.setNewsType(newsType);

        NewsInfo newsInfo = new NewsInfo();
        newsInfo.setNews(news);
        newsInfo.setInfo(info);
        try {

            if (newsService.edit(newsInfo,isPictureTemp,request)) {
                msg = "修改成功！";
                request.setAttribute("msg", msg);
                request.getRequestDispatcher("/msg.jsp").forward(request, response);
                return;
            } else {
                msg = "修改失败！";
                request.setAttribute("msg",msg);
                request.getRequestDispatcher("/msg.jsp").forward(request, response);
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
            request.setAttribute("msg",msg);
            request.getRequestDispatcher("/msg.jsp").forward(request, response);
            return;
        }
    }

    private void editBefore() throws ServletException, IOException {
        boolean tag = true;
        String newsIdRec = request.getParameter("newsId");
        if (newsIdRec.trim().equals("") || newsIdRec == null) {
            tag = false;
            msg = "输入的不能为空！";
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("/msg.jsp").forward(request, response);
        }
        int newsId = -1;
        if (tag) {
            try {
                newsId = Integer.parseInt(newsIdRec);
                NewsInfo newsInfo = newsInfoService.findById(newsId);
                ArrayList<NewsType> newsTypes = newsTypeService.findAll();
                request.setAttribute("newsInfo", newsInfo);
                request.setAttribute("newsTypes", newsTypes);
                request.getRequestDispatcher("/admin/newsEdit.jsp").forward(request, response);
            } catch (Exception e) {
                tag = false;
                msg = "参数转换错误！";
                request.setAttribute("msg", msg);
                request.getRequestDispatcher("/msg.jsp").forward(request, response);

            }
        }


    }

    private void delete() throws ServletException, IOException {
        String status = "1";
        boolean tag = true;
        String strId = request.getParameter("id");
        String picture = request.getParameter("append");
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("strId", strId);
        hashMap.put("picture", picture);
        boolean isValidate = ValidateUtil.validateNullOrEmpty(hashMap);
        if (!isValidate) {
            msg = "参数为空！";
            status = "1";
            tag = false;
        }
        int id = -1;
        if (tag) {
            try {
                id = Integer.parseInt(strId);
            } catch (Exception e) {
                e.printStackTrace();
                msg = "参数有问题！";
                status = "2";
                tag = false;
            }
        }
        if (tag) {
            try {
                newsService.deleteById(id, picture);
                msg = "删除成功！";
                status = "0";

            } catch (Exception e) {
                e.printStackTrace();
                msg = e.getMessage();
                status = "3";
            }
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.accumulate(MSG, msg);
        jsonObject.accumulate(STAT, status);
        PrintWriter out = response.getWriter();
        out.write(jsonObject.toString());
        out.flush();
        out.close();


    }

    private void add() throws ServletException, IOException {
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String date = request.getParameter("datepicker");
        String browser = request.getParameter("browsercount");
        String newsTypeId = request.getParameter("newsTypeId");
        String info = request.getParameter("info");
        HashMap<String, String> parameters = new HashMap<String, String>();
        parameters.put("title", title);
        parameters.put("date", date);
        parameters.put("author", author);
        parameters.put("browser", browser);
        parameters.put("newsTypeId", newsTypeId);
        parameters.put("info", info);
        boolean isValid = ValidateUtil.validateNullOrEmpty(parameters);
        if (!isValid) {
            msg = "输入的参数不能为空！";
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("/msg.jsp").forward(request, response);
            return;
        }
        int browserTemp = -1;
        int newsTypeIdTemp = -1;
        try {
            browserTemp = Integer.parseInt(browser);
            newsTypeIdTemp = Integer.parseInt(newsTypeId);
        } catch (Exception e) {
            e.printStackTrace();
            msg = "你输入的浏览量或新闻类型ID有问题！";
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("/msg.jsp").forward(request, response);
            return;
        }
        String fileName = "";
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
        } catch (Exception e) {
            e.printStackTrace();
            msg = "文件上传出错！";
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("/msg.jsp").forward(request, response);
            return;
        }
        News news = new News();
        news.setTitle(title);
        news.setAuthor(author);
        news.setSt(date);
        news.setBrowserCount(browserTemp);
        news.setPicture(fileName);

        NewsType newsType = new NewsType();
        newsType.setId(newsTypeIdTemp);
        news.setNewsType(newsType);

        NewsInfo newsInfo = new NewsInfo();
        newsInfo.setInfo(info);
        try {

            if (newsService.add(news, newsInfo)) {
                msg = "添加成功";
                request.setAttribute("msg", msg);
                request.getRequestDispatcher("/msg.jsp").forward(request, response);
                return;
            } else {
                msg = "添加失败！！！！！！！！11";
                request.setAttribute("msg", msg);
                request.getRequestDispatcher("/msg.jsp").forward(request, response);
                return;
            }

        } catch (Exception e) {
            // 删除文件
            e.printStackTrace();
            msg = e.getMessage();
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("/msg.jsp").forward(request, response);
            return;
        }
    }

    private void addBefore() throws ServletException, IOException {
        try {
            ArrayList<NewsType> newsTypes = newsTypeService.findAll();
            request.setAttribute("newsTypes", newsTypes);
            request.getRequestDispatcher("/admin/newsAdd.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("/msg.jsp").forward(request, response);
        }
    }

    private void findAll() throws ServletException, IOException {
        ArrayList<News> arr = null;
        try {
            arr = newsService.findAll();
            request.setAttribute("arr", arr);
            request.getRequestDispatcher("/admin/news.jsp").forward(request, response);
        } catch (Exception e) {
            String msg = e.getMessage();
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
