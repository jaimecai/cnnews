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
    <link rel="stylesheet" href="/bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css">
    <link rel="stylesheet" href="/plugins/umeditor/themes/default/css/umeditor.min.css">
    <style>
        .alert {
            position: fixed;
            top: 60px;
            right: 0px;
            z-index: 100;
            width: 500px;
        }

        #myEditor {
            width: 100%;
        }
    </style>
</head>

<body class="hold-transition skin-red sidebar-mini fixed">
<div class="wrapper">
    <jsp:include page="header.jsp"></jsp:include>
    <jsp:include page="aside.jsp"></jsp:include>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <%--<section class="content-header" id="content">--%>
        <%--<h1>--%>
        <%--添加新闻--%>
        <%--<small>Add News</small>--%>
        <%--</h1>--%>
        <%--</section>--%>

        <!-- Main content -->
        <section class="content container-fluid">

            <div class="row">
                <div class="col-xs-12">
                    <div class="box box-info">
                        <div class="box-header with-border">
                            <h3 class="box-title">添加新闻</h3>
                        </div>
                        <!-- /.box-header -->
                        <!-- form start -->
                        <form class="form-horizontal" action="/admin/NewsServlet.action?method=add" method="post" enctype="multipart/form-data">
                            <div class="box-body">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">标题：</label>

                                    <div class="col-sm-5">
                                        <input type="text" class="form-control" name="title">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">作者：</label>

                                    <div class="col-sm-5">
                                        <input type="text" class="form-control" name="author">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">日期：</label>
                                    <div class="col-sm-5">
                                        <div class="input-group date">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <input type="text" class="form-control" id="datepicker" name="datepicker">
                                        </div>
                                    </div>
                                    <!-- /.input group -->
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">浏览量：</label>

                                    <div class="col-sm-5">
                                        <input type="text" class="form-control" id="browsercount" name="browsercount" value="0" placeholder="默认为0">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">新闻类型：</label>
                                    <div class="col-sm-5">
                                        <select class="form-control select2" style="width: 100%" name="newsTypeId">
                                            <c:forEach items="${newsTypes}" var="item">
                                                <option value="${item.id}">${item.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">新闻图片：</label>

                                    <div class="col-sm-5">
                                        <input type="file" class="form-control" id="selectPicture" name="picture">
                                    </div>
                                    <div class="col-sm-5 col-sm-offset-2">
                                        <img id="selectPictureView"
                                             src="holder.js/200x200">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">新闻内容：</label>
                                    <div class="col-sm-8">
                                        <script type="text/plain" id="myEditor" name="info">
                                        </script>
                                    </div>
                                </div>
                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer">
                                <button type="submit" class="btn btn-primary">添加新闻</button>
                            </div>
                            <!-- /.box-footer -->
                        </form>
                    </div>
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
<jsp:include page="/admin/js.jsp"></jsp:include>

<script src="/plugins/umeditor/third-party/template.min.js"></script>
<script src="/plugins/umeditor/umeditor.config.js"></script>
<script src="/plugins/umeditor/umeditor.min.js"></script>
<script src="/plugins/umeditor/lang/zh-cn/zh-cn.js"></script>

<script src="https://cdn.bootcss.com/holder/2.9.4/holder.min.js"></script>
<script src="/bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
<script src="/bower_components/select2/dist/js/select2.full.min.js"></script>
<script>
    $(function () {

//Initialize Select2 Elements
        $('.select2').select2();
        //Date picker
        $('#datepicker').datepicker({
            autoclose: true
        });
        //        选择图片
        $('#selectPicture').bind('change', function () {
            //兼容性
            var $file = $(this);
            var fileObj = $file[0];
            var windowURL = window.URL || window.webkitURL;
            dataURL = windowURL.createObjectURL(fileObj.files[0]);
            if (fileObj && fileObj.files && fileObj.files[0]) {
                dataURL = windowURL.createObjectURL(fileObj.files[0]);
            } else {
                dataURL = $file.val();
            }
            //返回结果
            $('#selectPictureView').attr('src', dataURL);
        });
        //自定义参数
        var um = UM.getEditor('myEditor');

    })


</script>
</body>
</html>