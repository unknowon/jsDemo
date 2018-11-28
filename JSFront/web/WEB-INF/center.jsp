<%--
  User: Lulu
  Date: 2018/6/4
  Time: 4:29
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE HTML>
<html>
<head>
    <title>网站标题，控制在25个字、50个字节以内</title>
    <%@include file="/WEB-INF/header.jsp"%>
</head>
<style>
    .thetable td {
        border: 1px solid #1F1F1F;
    }

    table td {
        white-space: nowrap;
    }

    .artlistimg li {
        height: auto;
        margin: 5px auto;
        background-color: white;
        border: 1px solid #c0c0c0;
        padding: 10px;
        border-radius: 10px;
    }

    .artlistimg li a{
        padding: 5px;
    }
</style>
<style>
    .thetable td
    {
        border: 1px solid #1F1F1F;
    }
    table td
    {
        white-space: nowrap;
    }
</style>
<body>

<header class="navbar-wrapper theader" style="height: 114px !important;">
    <div class="navbar" style="height: 70px">
        <div class="headleft">
            <a class="" href="<%=ctxPath%>/Index?action=index"><img src="<%=ctxPath %>/assets/logo.png"></a>
        </div>
        <div class="headright">
            <a <c:choose><c:when test="${empty frontCompany}"><c:out value="href=Index?action=login"></c:out></c:when><c:otherwise><c:out value="href=Index?action=center"></c:out></c:otherwise></c:choose>>
	                <span class="label label-success radius" style="font-weight: normal;font-size: 14px">
                        <c:choose><c:when test="${empty frontCompany}"><c:out value="登陆"></c:out></c:when><c:otherwise>${frontCompany.name}</c:otherwise></c:choose>
                    </span>
            </a>
            <c:choose><c:when test="${empty frontCompany}"></c:when><c:otherwise><a href="Index?action=logout"><span class='label label-success radius' style='font-weight: normal;font-size: 14px'>退出登录</span></a></c:otherwise></c:choose>
            <a href="/JSAdmin/Index?action=login">
                <span class="label label-warning radius" style="font-weight: normal;font-size: 14px">后台管理</span>
            </a>
        </div>

    </div>
    <div class="navbar">
        <div class="container cl">
            <!--<a class="logo navbar-logo-m" href="/aboutHui.shtml">H-ui</a>-->
            <nav class="nav navbar-nav nav-collapse" role="navigation" id="Hui-navbar">
                <ul class="cl">
                    <li ><a href="<%=ctxPath%>/Index?action=index">首页</a></li>
                    <li ><a href="<%=ctxPath %>/Article?action=articlePageData&typeId=1&pageIndex=1">机构简介</a></li>
                    <li ><a href="<%=ctxPath %>/Article?action=articlePageData&typeId=2&pageIndex=1">政务公开</a></li>
                    <li ><a href="<%=ctxPath %>/Article?action=articlePageData&typeId=3&pageIndex=1">政策法规</a></li>
                    <li ><a href="<%=ctxPath %>/Article?action=articlePageData&typeId=4&pageIndex=1">业内新闻</a></li>
                    <li ><a href="<%=ctxPath %>/Article?action=articlePageData&typeId=5&pageIndex=1">人水和谐</a></li>
                    <li ><a href="<%=ctxPath %>/Article?action=articlePageData&typeId=6&pageIndex=1">节水型城市</a></li>
                    <li ><a href="<%=ctxPath %>/Article?action=articlePageData&typeId=7&pageIndex=1">联系我们</a></li>
                </ul>
            </nav>
            <nav class="navbar-userbar hidden-xs">

            </nav>
        </div>
    </div>
</header>

<div>
    <div style="background: url('<%=ctxPath%>/assets/static/jieshui/img/flbg.png');background-size: 100%;height: 300px;margin-top: 50px"
         align="center">

        <div class="container">
            <h3 style="color: white">功能直达</h3>
            <hr/>
            <br/>
            <div class="row">
                <div class="col-xs-3 col-sm-3">
                    <a href="<%=ctxPath %>/Report?action=add">
                        <img src="<%=ctxPath %>/assets/static/jieshui/img/table.png">
                        <h3 style="color: white">报表在线</h3>
                    </a>
                </div>
                <div class="col-xs-3 col-sm-3">
                    <a href="<%=ctxPath %>/Report?action=view">
                        <img src="<%=ctxPath %>/assets/static/jieshui/img/find.png">
                        <h3 style="color: white">水量查询</h3>
                    </a>
                </div>
                <div class="col-xs-3 col-sm-3">
                    <a href="<%=ctxPath %>/Article?action=articlePageData&typeId=10&pageIndex=1">
                        <img src="<%=ctxPath %>/assets/static/jieshui/img/zhinan.png">
                        <h3 style="color: white">办事指南</h3>
                    </a>
                </div>
                <div class="col-xs-3 col-sm-3">
                    <a href="<%=ctxPath %>/Article?action=download&pageIndex=1">
                        <img src="<%=ctxPath %>/assets/static/jieshui/img/down.png">
                        <h3 style="color: white">文件下载</h3>
                    </a>
                </div>
            </div>
        </div>
    </div>
    <div style="clear: both;"></div>
    <div class="container" style="min-width: 1090px !important;">

        <div class="row">
            <div class="col-xs-9 col-sm-9 artlistimg">

                <div class="tabCon bk-gray pd-10" role="tabpanel" data-filtered="filtered" style="display: block;">
                    <div class="artlistimg">
                        <ul>
                            <li>
                                <a href="<%=ctxPath%>/Index?action=center" class="layui-btn btn-danger">单位管理</a>
                                <a href="<%=ctxPath%>/Application?action=add" class="layui-btn btn-danger">在线申请</a>
                                <a href="<%=ctxPath%>/Article?action=add" class="layui-btn btn-danger">信息提交</a>
                            </li>
                            <li>
                                <div class="row">
                                    <div class="col-xs-12 col-sm-12" style="float:inherit !important">
                                        <h3 style="text-align: center">
                                            ${company.name}
                                        </h3>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="row" style="padding: 10px">
                                    <br/>
                                    <div class="text-c"> 日期范围：
                                        <input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}' })" id="datemin" class="input-text Wdate" style="width:120px;">
                                        -
                                        <input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d' })" id="datemax" class="input-text Wdate" style="width:120px;">
                                        <input type="text" class="input-text" style="width:250px" placeholder="输入关键字" id="" name="">
                                        <button type="submit" class="btn btn-success" id="" name=""><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
                                    </div>
                                    <div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="r">共有数据：<strong>54</strong> 条</span> </div>
                                    <table class="table table-border table-bordered table-bg">
                                        <thead>
                                        <tr>
                                            <th scope="col" colspan="9">员工列表</th>
                                        </tr>
                                        <tr class="text-c">
                                            <th width="40">ID</th>
                                            <th width="150">类型</th>
                                            <th width="230">添加时间</th>

                                            <th width="150">添加公司</th>
                                            <th width="100">操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>

                                        <c:forEach items="${reports}" var="report">
                                            <tr class="text-c">
                                                <td><c:out value="${report.id}"/></td>
                                                <td><c:out value="${report.reportTypeId}"/></td>
                                                <td><c:out value="${report.addTime}"/></td>

                                                <td><c:out value="${report.companyName}"/></td>
                                                <td class="td-manage">
                                                    <a title="编辑" href="javascript:;" onclick="admin_edit('报表编辑','admin-add.html','1','800','500')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a>
                                                    <a title="删除" href="javascript:;" onclick="admin_del(this,'1')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a>
                                                </td>
                                            </tr>
                                        </c:forEach>

                                        </tbody>
                                    </table>
                                </div>
                            </li>

                        </ul>
                        <div style="clear: both"></div>
                    </div>
                </div>

            </div>
            <div class="col-xs-3 col-sm-3">
                <b>通知公告</b>
                <ul class="rightul">
                    <c:forEach items="${notices}" var="notice">
                        <li><a href="<%=ctxPath%>/Notice?action=noticeView&noticeId=${notice.id}">${notice.title}</a> </li>
                    </c:forEach>


                </ul>
            </div>
        </div>

    </div>
</div>
<div style="clear: both"></div>
<footer class="footer mt-20">
    <div class="container-fluid">
        <nav>
            <span class="pipe">|</span>
            <c:forEach items="${links}" var="link">
                <a href="${link.url}" target="_blank">${link.name}</a> <span class="pipe">|</span>
            </c:forEach>
        </nav>

    </div>
</footer>
<script type="text/javascript" src="<%=ctxPath%>/assets/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="<%=ctxPath%>/assets/lib/jquery.SuperSlide/2.1.1/jquery.SuperSlide.min.js"></script>
<script type="text/javascript" src="<%=ctxPath%>/assets/static/h-ui/js/H-ui.js"></script>

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="<%=ctxPath%>/assets/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="<%=ctxPath%>/assets/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="<%=ctxPath%>/assets/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="<%=ctxPath%>/assets/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript" src="<%=ctxPath%>/assets/lib/webuploader/0.1.5/webuploader.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vue@2.5.16/dist/vue.js"></script>

<script>
    $(function () {
        jQuery("#slider-3 .slider").slide({
            mainCell: ".bd ul",
            titCell: ".hd li",
            trigger: "click",
            effect: "leftLoop",
            autoPlay: true,
            delayTime: 700,
            interTime: 7000,
            pnLoop: false,
            titOnClassName: "active"
        })
        jQuery("#slider-2 .slider").slide({
            mainCell: ".bd ul",
            titCell: ".hd li",
            trigger: "click",
            effect: "leftLoop",
            autoPlay: true,
            delayTime: 700,
            interTime: 7000,
            pnLoop: false,
            titOnClassName: "active"
        })
    });
    $.Huimarquee(200, 1, 3000);
    /*移动高度,移动速度,间隔时间*/
    $(function () {
        $.Huitab("#tab_demo .tabBar span", "#tab_demo .tabCon", "current", "click", "1")
    });

    $("#menu_4 dt").addClass("selected");
    $("#menu_4 dd").show();
    $(".Hui-aside").scrollTop(210);
    $(function () {
        $("#tab_demo").Huitab({
            tabEvent: "mousemove",
            index: 0
        });
    });


    jQuery.Huitab = function (tabBar, tabCon, class_name, tabEvent, i) {
        var $tab_menu = $(tabBar);
        // 初始化操作
        $tab_menu.removeClass(class_name);
        $(tabBar).eq(i).addClass(class_name);
        $(tabCon).hide();
        $(tabCon).eq(i).show();

        $tab_menu.bind(tabEvent, function () {
            $tab_menu.removeClass(class_name);
            $(this).addClass(class_name);
            var index = $tab_menu.index(this);
            $(tabCon).hide();
            $(tabCon).eq(index).show()
        })
    }
</script>
</body>
</html>
