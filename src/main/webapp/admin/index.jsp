<%--
  Created by IntelliJ IDEA.
  User: jaimecai
  Date: 17-9-21
  Time: 下午3:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                新闻类型管理
                <small>News Type Management</small>
            </h1>
        </section>

        <!-- Main content -->
        <section class="content container-fluid">

            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header ">
                            <div class="input-group pull-left" style="width: 200px">
                                <input type="text" class="form-control " placeholder="新闻类型名称" id="newsType"/>
                                <div class="input-group-btn">
                                    <button class="btn btn-default" id="addNewsType">添加</button>
                                </div>
                            </div>
                        </div>

                        <!-- /.box-header -->
                        <div class="box-body table-responsive no-padding">
                            <table class="table table-hover">
                                <tbody>
                                <tr id="newsTypeTable">
                                    <th>ID</th>
                                    <th>名称</th>
                                    <th>操作</th>
                                </tr>
                                <c:forEach items="${requestScope.arr}" var="item">
                                    <tr id="newsTypeTableTr${item.id}">
                                        <td>${item.id}</td>
                                        <td>${item.name}</td>
                                        <td>
                                            <a href="javascript:editDialog('${item.id}','${item.name}','/admin/NewsTypeServlet.action')">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
                                                href="javascript:delDialogOpen('${item.id}','${item.name}','/admin/NewsTypeServlet.action')">删除</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
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
<jsp:include page="editDialog.jsp"></jsp:include>
<!-- ./wrapper -->
<!-- REQUIRED JS SCRIPTS -->
<%--<p id="time" style="float: right"></p>--%>
<jsp:include page="/admin/js.jsp"></jsp:include>
<%--<script src="/admin/bower_components/fastclick/lib/fastclick.js"></script>--%>
<!-- Optionally, you can add Slimscroll and FastClick plugins.
     Both of these plugins are recommended to enhance the
     user experience. -->
<script src="/dist/js/myjs/deleteNewsType.js"></script>
<script src="/dist/js/myjs/editDialog.js"></script>
<script src="/dist/js/myjs/showAlertMsg.js"></script>
<script>
    $(function () {
//        陷入死循环
//        window.location="/servlet/NewsTypeServlet.action?method=findAll";
//        window.setInterval(function () {
//            $('#time').text(new Date())
//        }, 1000);
    });
    //    $('#btn').click(function () {
    //
    //        $('#content').prepend('<div class="alert alert-danger alert-dismissible" id="requestFail">\n' +
    //            '                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>\n' +
    //            '                <h4><i class="icon fa fa-ban"></i> Alert!</h4>\n' +
    //            '                请求失败！' +
    //            '              </div>');
    //
    ////        setTimeout(function () {
    ////            if($('#requestFail').length>0){
    ////                $('#requestFail').fadeOut(5000);
    //////                淡出后元素依然存在
    //////                if($('#requestFail').length>0)
    //////                    window.alert("exist");
    //////                淡出的时间内阻塞进程后面的代码不执行
    //////                window.alert('看先是否阻塞');
    ////                $('#requestFail').remove();
    ////            }
    ////
    ////        },10000);
    //        $('#requestFail').fadeOut(5000,function () {
    //           $(this).remove();
    //        });

    //    });

    $("#addNewsType").click(function () {
        var newsType = $("#newsType").val();
        var url = '/admin/NewsTypeServlet.action';
        $.ajax({
            url: url,
            dataType: 'json',
            type: 'post',
            data: {
                newsType: newsType,
                method: 'addNewsType'
            },
            error: function (XMLHttpRequest, textStatus, errorThrow) {
                showDangerAlert("请求失败！");
            },
            success: function (data, textStatus, jqXHR) {
                if (data.stat == 0) {
                    $('#newsTypeTable').after('<tr id="newsTypeTableTr' + data.newsTypeId + '">\n' +
                        '                                    <td>' + data.newsTypeId + '</td>\n' +
                        '                                    <td>' + data.newsTypeName + '</td>\n' +
                        '                                    <td><a href="javascript:editDialog(\'' + data.newsTypeId + '\',\'' + data.newsTypeName + '\',\'' + '/admin/NewsTypeServlet.action\'' + ')">编辑</ai>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:delDialogOpen(\'' + data.newsTypeId + '\',\'' + data.newsTypeName + '\',\'' + '/admin/NewsTypeServlet.action\'' + ')">删除</a></td>\n' +
                        '                                </tr>');
                    showSuccessAlert(data.msg);
                } else {
                    showDangerAlert(data.msg);
                }
            }
        });
    });


</script>
</body>
</html>