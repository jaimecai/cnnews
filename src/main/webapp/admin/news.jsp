<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>新闻管理系统后台</title>
    <!-- Tell the browser to be responsive to screen width -->
    <jsp:include page="/admin/link.jsp"></jsp:include>
    <style>
        .alert {
            position: fixed;
            top: 60px;
            right: 0px;
            z-index: 100;
            width: 500px;
        }
    </style>
</head>

<body class="hold-transition skin-red sidebar-mini fixed">
<div class="wrapper">
    <jsp:include page="header.jsp"></jsp:include>
    <jsp:include page="aside.jsp"></jsp:include>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <section class="content-header" id="content">
            <h1>
                新闻管理
                <small>News Management</small>
            </h1>
        </section>

        <!-- Main content -->
        <section class="content container-fluid">

            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header ">
                            <button class="btn btn-default" onclick="window.location.href='/admin/NewsServlet.action?method=addBefore'">添加新闻</button>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body table-responsive no-padding">
                            <table class="table table-hover">
                                <tbody>
                                <tr id="newsTypeTable">
                                    <th>ID</th>
                                    <th>图片</th>
                                    <th>标题</th>
                                    <th>时间</th>
                                    <th>作者</th>
                                    <th>类型</th>
                                    <th>浏览量</th>
                                    <th>操作</th>
                                </tr>
                                <c:forEach items="${requestScope.arr}" var="item">
                                    <tr id="${item.id}">
                                        <td>${item.id}</td>
                                        <td><img src="/upload/cnnews/${item.picture}" width="80px" height="80px"/></td>
                                        <td>
                                            <a href="/admin/NewsInfoServlet.action?method=findById&newsId=${item.id}">${item.title}</a>
                                        </td>
                                        <td>${item.st}</td>
                                        <td>${item.author}</td>
                                        <td>${item.newsType.name}</td>
                                        <td>${item.browserCount}</td>
                                        <td>
                                            <a href="/admin/NewsServlet.action?method=editBefore&newsId=${item.id}">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
                                                href="javascript:delDialog('${item.id}','${item.title}','${item.picture}','/admin/NewsServlet.action?method=delete')">删除</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                                <tfoot>
                                <tr>
                                    <th colspan="3">分页</th>
                                </tr>
                                </tfoot>
                            </table>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>
            </div>

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <jsp:include page="footer.jsp"></jsp:include>

    <!-- Control Sidebar -->
    <aside class="control-sidebar control-sidebar-dark">
        <!-- Create the tabs -->
        <ul class="nav nav-tabs nav-justified control-sidebar-tabs">
            <li class="active"><a href="#control-sidebar-home-tab" data-toggle="tab"><i class="fa fa-home"></i></a></li>
            <li><a href="#control-sidebar-settings-tab" data-toggle="tab"><i class="fa fa-gears"></i></a></li>
        </ul>
        <!-- Tab panes -->
        <div class="tab-content">
            <!-- Home tab content -->
            <div class="tab-pane active" id="control-sidebar-home-tab">
                <h3 class="control-sidebar-heading">Recent Activity</h3>
                <ul class="control-sidebar-menu">
                    <li>
                        <a href="javascript:;">
                            <i class="menu-icon fa fa-birthday-cake bg-red"></i>

                            <div class="menu-info">
                                <h4 class="control-sidebar-subheading">Langdon's Birthday</h4>

                                <p>Will be 23 on April 24th</p>
                            </div>
                        </a>
                    </li>
                </ul>
                <!-- /.control-sidebar-menu -->

                <h3 class="control-sidebar-heading">Tasks Progress</h3>
                <ul class="control-sidebar-menu">
                    <li>
                        <a href="javascript:;">
                            <h4 class="control-sidebar-subheading">
                                Custom Template Design
                                <span class="pull-right-container">
                    <span class="label label-danger pull-right">70%</span>
                  </span>
                            </h4>

                            <div class="progress progress-xxs">
                                <div class="progress-bar progress-bar-danger" style="width: 70%"></div>
                            </div>
                        </a>
                    </li>
                </ul>
                <!-- /.control-sidebar-menu -->

            </div>
            <!-- /.tab-pane -->
            <!-- Stats tab content -->
            <div class="tab-pane" id="control-sidebar-stats-tab">Stats Tab Content</div>
            <!-- /.tab-pane -->
            <!-- Settings tab content -->
            <div class="tab-pane" id="control-sidebar-settings-tab">
                <form method="post">
                    <h3 class="control-sidebar-heading">General Settings</h3>

                    <div class="form-group">
                        <label class="control-sidebar-subheading">
                            Report panel usage
                            <input type="checkbox" class="pull-right" checked>
                        </label>

                        <p>
                            Some information about this general settings option
                        </p>
                    </div>
                    <!-- /.form-group -->
                </form>
            </div>
            <!-- /.tab-pane -->
        </div>
    </aside>
    <!-- /.control-sidebar -->
    <!-- Add the sidebar's background. This div must be placed
    immediately after the control sidebar -->
    <div class="control-sidebar-bg"></div>
</div>
<jsp:include page="delDialog.jsp"></jsp:include>
<jsp:include page="/admin/js.jsp"></jsp:include>

<script src="/dist/js/myjs/showAlertMsg.js"></script>
<script src="/dist/js/myjs/deleteDialog.js"></script>
</body>
</html>