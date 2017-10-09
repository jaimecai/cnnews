<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="/bower_components/bootstrap/dist/css/bootstrap.css" rel="stylesheet">
    <link href="/bower_components/font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="/dist/css/mycss/my.css" rel="stylesheet">
</head>
<body>
<!--导航-->
<jsp:include page="nav.jsp"></jsp:include>
<!--banner-->
<jsp:include page="banner.jsp"></jsp:include>
<!--内容-->
<div class="container">
    <c:forEach items="${beans}" var="bean">
        <div class="page-header">
            <h2>${bean.newsType.name}
                <small>最新前沿资讯【更多】</small>
            </h2>
            <div class="row">
                <div class="col-xs-6">
                    <!--具体新闻-->
                    <c:forEach items="${bean.leftNews}" var="leftNew">
                        <div class="row my-news">
                            <div class="col-xs-4">
                                <a href="/InfoPageServlet.action?newsId=${leftNew.id}" >
                                <img class="img-thumbnail" src="/upload/cnnews/${leftNew.picture}" style="width: 155px;height: 155px">
                                </a>
                            </div>
                            <div class="col-xs-8">
                                <a href="/InfoPageServlet.action?newsId=${leftNew.id}">
                                <h4 class="title my-news-title">${leftNew.title}</h4>
                                </a>
                                <p class="st-author"><i class="fa fa-calendar"></i>&nbsp;<span>${leftNew.st}</span>&nbsp;<i
                                        class="fa fa-group"></i>&nbsp;<span>${leftNew.author}</span></p>
                                <p class="brower-count"><i
                                        class="fa fa-eye">浏览量</i>&nbsp;<span>${leftNew.browserCount}</span></p>
                            </div>
                        </div>
                    </c:forEach>
                </div>

                <div class="col-xs-6">
                    <!--具体新闻-->
                    <c:forEach items="${bean.rightNews}" var="rightNew">
                        <div class="row my-news">
                            <div class="col-xs-4">
                                <a href="/InfoPageServlet.action?newsId=${rightNew.id}">
                                <img class="img-thumbnail" src="/upload/cnnews/${rightNew.picture}" style="width: 155px;height: 155px">
                                </a>
                            </div>
                            <div class="col-xs-8">
                                <a href="/InfoPageServlet.action?newsId=${rightNew.id}">
                                <h4 class="title my-news-title">${rightNew.title}</h4>
                                </a>
                                <p class="st-author"><span>${rightNew.st}</span><span>来源: ${rightNew.author}</span></p>
                                <p class="brower-count"><span>浏览数：</span><span>${rightNew.browserCount}</span></p>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>

        </div>
    </c:forEach>


</div>
<!--脚注-->
<jsp:include page="footer.jsp"></jsp:include>
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
</body>
</html>
