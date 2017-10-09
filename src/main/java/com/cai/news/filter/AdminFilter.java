package com.cai.news.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebFilter(filterName = "A002AdminFilter",urlPatterns = {"/admin/*"})
public class AdminFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest=(HttpServletRequest)servletRequest;
        HttpServletResponse httpServletResponse=(HttpServletResponse)servletResponse;
        HttpSession httpSession=httpServletRequest.getSession();
        if(httpSession.getAttribute("admin")!=null)
            filterChain.doFilter(servletRequest,servletResponse);
        else
            httpServletResponse.sendRedirect("/IndexServlet.action");
    }

    public void destroy() {

    }
}
