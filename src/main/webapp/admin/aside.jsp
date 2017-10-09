<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<aside class="main-sidebar .sidebar-collapse">

    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">

        <!-- Sidebar user panel (optional) -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="/admin/dist/img/avatar04.png" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                <p>Alexander Pierce</p>
                <!-- Status -->
                <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
            </div>
        </div>

        <!-- search form (Optional) -->
        <form action="#" method="get" class="sidebar-form">
            <div class="input-group">
                <input type="text" name="q" class="form-control" placeholder="Search...">
                <span class="input-group-btn">
              <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
              </button>
            </span>
            </div>
        </form>
        <!-- /.search form -->

        <!-- Sidebar Menu -->
        <ul class="sidebar-menu" data-widget="tree">
            <li class="header">新闻</li>
            <!-- Optionally, you can add icons to the links -->
            <li class="<%=request.getServletPath().equals("/admin/index.jsp")?"active":"" %>"><a href="/admin/NewsTypeServlet.action?method=findAll"><i class="fa fa-newspaper-o"></i> <span>新闻类型管理</span></a></li>
            <li class="<%=request.getServletPath().equals("/admin/news.jsp")?"active":"" %>"><a href="/admin/NewsServlet.action?method=findAll"><i class="fa fa-newspaper-o"></i> <span>新闻内容管理</span></a></li>
        </ul>
        <ul class="sidebar-menu" data-widget="tree">
            <li class="header">用户</li>
            <!-- Optionally, you can add icons to the links -->
            <li class=""><a href="#"><i class="fa fa-user"></i> <span>Link</span></a></li>
            <li><a href="#"><i class="fa fa-user"></i> <span>Another Link</span></a></li>
        </ul>
        <ul class="sidebar-menu" data-widget="tree">
            <li class="header">系统</li>
            <!-- Optionally, you can add icons to the links -->
            <li class=""><a href="#"><i class="fa fa-cog"></i> <span>Link</span></a></li>
            <li><a href="#"><i class="fa fa-cog"></i> <span>Another Link</span></a></li>
        </ul>
        <!-- /.sidebar-menu -->
    </section>
    <!-- /.sidebar -->
</aside>
