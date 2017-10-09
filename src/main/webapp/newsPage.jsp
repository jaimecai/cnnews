<%--
  Created by IntelliJ IDEA.
  User: jaimecai
  Date: 17-9-28
  Time: 下午4:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="content-type" content="text/html; charset=UTF-8">
    <title>Title</title>


    <link href="/bower_components/bootstrap/dist/css/bootstrap.css" rel="stylesheet">
    <link href="/bower_components/font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="/dist/css/mycss/my.css" rel="stylesheet">
    <style>
        body {
            margin-top: 50px;
        }
    </style>

</head>
<body>

<jsp:include page="nav.jsp"></jsp:include>
<!--内容-->
<div class="container">

    <div class="row">

        <div class="col-xs-8">
            <div class="page-header">
                <h2>
                    ${newsType.name}
                    <small>最新前沿资讯</small>
                </h2>
                <!--具体新闻-->
                <c:forEach items="${allNews}" var="news">
                    <div class="row my-news">
                        <div class="col-xs-4">
                            <a href="/InfoPageServlet.action?newsId=${news.id}">
                            <img class="img-thumbnail" src="/upload/cnnews/${news.picture}" style="width: 160px;height: 160px">
                            </a>
                        </div>
                        <div class="col-xs-8">
                            <a href="/InfoPageServlet.action?newsId=${news.id}">
                            <h4 class="title my-news-title">${news.title}</h4>
                            </a>
                            <p class="st-author">
                                <i class="fa fa-calendar"></i>&nbsp;<span>${news.st}</span>&nbsp;<i
                                    class="fa fa-group"></i>&nbsp;<span>${news.author}</span>
                            </p>
                            <p class="brower-count">
                                <i class="fa fa-eye">浏览量</i>&nbsp;<span>${news.browserCount}</span>
                            </p>
                        </div>
                    </div>
                </c:forEach>

            </div>

            <!--分页-->
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <li><a href="#" aria-label="Previous"> <span
                            aria-hidden="true">&laquo;</span>
                    </a></li>
                    <li><a href="#">1</a></li>
                    <li><a href="#" class="active">2</a></li>
                    <li><a href="#">3</a></li>
                    <li><a href="#">4</a></li>
                    <li><a href="#">5</a></li>
                    <li><a href="#" aria-label="Next"> <span
                            aria-hidden="true">&raquo;</span>
                    </a></li>
                </ul>
            </nav>
        </div>


        <div class="col-xs-4">

            <div class="list-group" style="margin-top: 40px">
                <a class="list-group-item active"> 最新的10条新闻 </a>
                <c:forEach items="${latestNews}" var="latest">
                    <a class="list-group-item " href="/InfoPageServlet.action?newsId=${latest.id}"> ${latest.title}</a>
                </c:forEach>
            </div>
            <div class="list-group" style="margin-top: 40px">
                <a class="list-group-item active"> ${newsType.name}浏览量最多的10条新闻 </a>
                <c:forEach items="${hottestNews}" var="hottest">
                    <a class="list-group-item " href="/InfoPageServlet.action?newsId=${hottest.id}"> ${hottest.title} </a>
                </c:forEach>
            </div>

        </div>


    </div>


</div>


<!--脚注-->
<jsp:include page="footer.jsp"></jsp:include>
</body>


</html>
<script src="/bower_components/jquery/dist/jquery.js"></script>
<script src="/bower_components/bootstrap/dist/js/bootstrap.js"></script>
<script src="/plugins/STAR-ZERO-jquery-ellipsis/dist/jquery.ellipsis.js"></script>
<script>

    $(function () {

        $('.title').ellipsis({
            row: 2
        });

    });


</script>