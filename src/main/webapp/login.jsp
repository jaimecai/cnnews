<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <jsp:include page="linkIncluded.jsp"></jsp:include>
</head>
<body class="hold-transition login-page">


<div class="login-box">
    <div class="login-logo">
        <a href="#">新闻后台管理系统V1.0</a>
    </div>
    <!-- /.login-logo -->
    <div class="login-box-body">
        <p class="login-box-msg">在此输入你的登录信息</p>

        <form>
            <div class="form-group has-feedback">
                <input type="text" class="form-control" placeholder="账户名称" id="usrName">
                <span class="fa fa-user form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input type="password" class="form-control" placeholder="账户密码" id="passwd">
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>

            <div class="row">
                <div class="col-xs-6">
                    <div class="form-group has-feedback">
                        <input style="height: 50px" type="text" class="form-control" placeholder="输入验证码" id="kaptcha">
                    </div>
                </div>
                <!-- /.col -->
                <div class="col-xs-6">
                    <img id="imgKaptcha" onclick="setKaptcha()">
                </div>
                <!-- /.col -->
            </div>
            <div class="row">
                <div class="col-xs-8">
                    <div class="checkbox">
                        <label>
                            <input type="checkbox"> 记住我
                        </label>
                    </div>
                </div>
                <!-- /.col -->
                <div class="col-xs-4">
                    <button type="button" onclick="loginAjax()" class=" btn btn-primary btn-block btn-flat">登录</button>
                </div>
                <!-- /.col -->
            </div>
        </form>
        <!-- /.social-auth-links -->
        <a href="#">返回首页面</a><br>
    </div>
    <!-- /.login-box-body -->
</div>
<!-- /.login-box -->
<%--遮罩--%>
<div id="m1"class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <p>正在登录:-)......</p>
            </div>

        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!--错误提出弹出框框-->
<div id="m2" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">信息提示框</h4>
            </div>
            <div class="modal-body">
                <p id="msg"></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
</body>
</html>
<jsp:include page="jsIncluded.jsp"></jsp:include>

<script>
    function loginAjax() {
        $('#m1').modal('show');
        var a=$('#usrName').val();
        var b=$('#passwd').val();
        var c=$('#kaptcha').val();
        var url='LoginServlet.action';
        var method='post';
        //开始请求
        $.ajax({
            url:url,
            dataType:'json',
            type:'post',
            data:{
                usrName:a,
                passwd:b,
                kaptcha:c
            },
            error:function (XMLHttpRequest,textStatus,errorThrow) {
                $('#m1').modal('hide');
                setKaptcha();
                $('#msg').text('请求错误');
                $('#m2').modal('show');
            },
            success:function (data,textStatus,jqXHR) {
                if(data.responseStatus==0){
                    $('#m1').modal('hide');
                    window.location='/admin/NewsTypeServlet.action?method=findAll';
                }else{
                    $('#m1').modal('hide');
                    setKaptcha();
                    $('#msg').text(data.msg);
                    $('#m2').modal('show');
                }
            }
        });
    }

    /**
     * 生成验证码
     */
    function setKaptcha() {
        $('#imgKaptcha').attr('src','/KaptchaServlet.action?'+Math.floor(Math.random()*100));
    }
    $(function () {
        //设置遮罩层
        $('#m1').modal({
            keyboard: false,
            show: false
        });
        //信息提示框
        $('#m2').modal({
            keyboard: false,
            show: false
        });

        setKaptcha();
    });
</script>