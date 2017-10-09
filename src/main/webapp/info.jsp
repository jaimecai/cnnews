<%--
  Created by IntelliJ IDEA.
  User: jaimecai
  Date: 17-10-8
  Time: 下午5:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

</body>
</html>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <jsp:include page="linkIncluded.jsp"></jsp:include>
</head>
<body>
<!--导航-->
<jsp:include page="nav.jsp"></jsp:include>
<!--banner-->
<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
        <li data-target="#carousel-example-generic" data-slide-to="1"></li>
        <li data-target="#carousel-example-generic" data-slide-to="2"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">
        <div class="item active">
            <img style="height: 500px;width: 100%" src="img/b1.jpg">
        </div>

        <div class="item">
            <img style="height: 500px;width: 100%" src="img/b2.jpg">
        </div>
        <div class="item">
            <img style="height: 500px;width: 100%" src="img/b3.jpg">
        </div>
    </div>

    <!-- Controls -->
    <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div>
<!--内容-->


<div class="container">

    <div class="row">

        <div class="col-xs-9">
            <div class="page-header">
                <h2>${news.title}</h2>
                <p><span>日期:${news.st}</span>&nbsp;&nbsp;<span>作者:${news.author}</span></p>
                <p><span>浏览量:${news.browserCount}</span></p>
            </div>
            ${newsInfo.id}
            <div class="my-div-p">
                ${newsInfo.info}
            </div>
        </div>

        <!--边栏-->
        <div class="col-xs-3">

            <div class="list-group" style="margin-top: 40px">
                <a class="list-group-item active"> 最新的10条新闻 </a>
                <c:forEach items="${newsTypeLatestTop10}" var="latest">
                    <a class="list-group-item " href="/InfoPageServlet.action?newsId=${latest.id}"> ${latest.title}</a>
                </c:forEach>
            </div>
            <div class="list-group" style="margin-top: 40px">
                <a class="list-group-item active"> ${newsType.name}浏览量最多的10条新闻 </a>
                <c:forEach items="${newsTypeBrowserTop10}" var="hottest">
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