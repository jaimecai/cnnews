<%@ page import="com.cai.news.beans.NewsType" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<nav class="navbar navbar-inverse navbar-fixed-top my-nav ">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="IndexServlet.action">我的资讯站</a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <%
                    NewsType newsType = (NewsType) request.getAttribute("newsType");
                    if (newsType == null){
                        newsType=new NewsType();
                        newsType.setId(-1);
                    }
                %>
                <li class="<%=newsType.getId()==-1?"active":"" %>"><a href="/IndexServlet.action">首页</a>
                </li>
                <c:forEach items="${newsTypes}" var="item">
                    <c:set var="newsTypeIdSelected" value="${item.id}" scope="request"></c:set>
                    <li class="<%=newsType.getId().equals(request.getAttribute("newsTypeIdSelected"))?"active":"" %>">
                        <a href="/NewsPageServlet.action?newsTypeId=${item.id}">${item.name}</a></li>
                </c:forEach>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>