package com.cai.news.servlet;

import com.cai.news.beans.Admin;
import com.cai.news.services.AdminService;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet.action"})
public class LoginServlet extends javax.servlet.http.HttpServlet {
    public static final String MSG = "msg";
    public static final String STATUS = "responseStatus";

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String usrName = request.getParameter("usrName");
        String passwd = request.getParameter("passwd");
        String kaptcha = request.getParameter("kaptcha");
        String msg = "";
        String status = "1";
        boolean tag = true;
        AdminService adminService = new AdminService();
        if (usrName == null || passwd == null || kaptcha == null) {
            msg = "输入的账号或密码有误";
            status = "1";
            tag = false;
        }
        if (tag) {
            String resKaptcha = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
            if (!kaptcha.equalsIgnoreCase(resKaptcha)) {
                msg = "你输入的验证码有错误";
                status = "2";
                tag = false;
            }
        }
        if (tag) {
            Admin admin = new Admin();
            admin.setUsername(usrName);
            admin.setPassword(passwd);
            Admin result = adminService.login(admin);
            if (result == null) {
                msg = "输入的账号或密码有误";
                status = "3";
                tag = false;
            } else {
                request.getSession().setAttribute("admin", result);
                status = "0";
                msg = "成功";
            }
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.accumulate(MSG, msg);
        jsonObject.accumulate(STATUS, status);
        PrintWriter out = response.getWriter();
        out.write(jsonObject.toString());
        out.flush();
        out.close();
    }
}
