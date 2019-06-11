<%--
  Created by IntelliJ IDEA.
  User: Lulu
  Date: 2018/6/7
  Time: 1:34
--%>
<%--
  Created by IntelliJ IDEA.
  User: Lulu
  Date: 2018/5/26
  Time: 12:50
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@include file="/WEB-INF/header.jsp" %>
    <title>${article.articleTitle}</title>
</head>
<body>
<header class="navbar-wrapper theader" style="height: 114px !important;">
    <div class="navbar" style="height: 70px">
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
                <ul id="ulTest" class="cl">
                        <li ><a href="<%=ctxPath %>/Article?action=articlePageData&typeId=1&pageIndex=1">理科类论文</a></li>
                        <li ><a href="<%=ctxPath %>/Article?action=articlePageData&typeId=2&pageIndex=1">文科类论文</a></li>
                        <li ><a href="<%=ctxPath %>/Article?action=articlePageData&typeId=3&pageIndex=1">管理类论文</a></li>
                        <li ><a href="<%=ctxPath %>/Article?action=articlePageData&typeId=4&pageIndex=1">研究类论文</a></li>
                        <li ><a href="<%=ctxPath %>/Article?action=articlePageData&typeId=5&pageIndex=1">专题型论文</a></li>
                        <li ><a href="<%=ctxPath %>/Article?action=articlePageData&typeId=6&pageIndex=1">综合型论文</a></li>
                    <li ><a href="<%=ctxPath %>/Article?action=articlePageData&typeId=7&pageIndex=1">辩论型论文</a></li>
                </ul>
            </nav>
            <nav class="navbar-userbar hidden-xs">

            </nav>
        </div>
    </div>
</header>

<div>

    <div style="clear: both;"></div>
    <div class="container">
        <div class="fly-panel detail-box" style="background-color: white">
<span class="layui-breadcrumb" style="visibility: visible;">
  <a href="<%=ctxPath%>/Index?action=index">首页</a><span lay-separator="">/</span>
  <a><cite>通知详情</cite></a>
</span>
            <hr>
            <h1 class="ue_t" label="Title center" name="tc"
                style="padding:0px 4px 0px 0px;text-align:center;margin:0px 0px 20px;"><span style="color:#c0504d;">${notice.title}</span>
            </h1>
            <hr>
            <div class="detail-body photos" deep="4">
                ${notice.content}
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
