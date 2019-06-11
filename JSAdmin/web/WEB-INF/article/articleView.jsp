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
    <%String ctxPath = request.getContextPath(); %>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>

    <meta name="keywords" content="毕节节水数字">

    <meta name="description" content="毕业论文管理系统后台">
    <link rel="Bookmark" href="favicon.ico">
    <link rel="Shortcut Icon" href="favicon.ico"/>
    <!--[if lt IE 9]>
    <script type="text/javascript" src="<%=ctxPath%>/libFront/html5shiv.js"></script>
    <script type="text/javascript" src="<%=ctxPath%>/libFront/respond.min.js"></script>
    <![endif]-->
    <link href="<%=ctxPath %>/static/h-ui/css/H-ui.css" rel="stylesheet" type="text/css"/>
    <link href="<%=ctxPath %>/libFront/Hui-iconfont/1.0.8/iconfont.css" rel="stylesheet" type="text/css"/>
    <link href="<%=ctxPath%>/static/jieshui/css/global.css" rel="stylesheet" type="text/css"/><!--自己的样式-->
    <link href="<%=ctxPath %>/static/jieshui/css/style.css" rel="stylesheet" type="text/css"/><!--自己的样式-->
    <!--[if IE 6]>
    <script type="text/javascript" src="<%=ctxPath%>/libFront/DD_belatedPNG_0.0.8a-min.js"></script>
    <script>DD_belatedPNG.fix('.pngfix,.icon');</script>
    <![endif]-->

    <script type="text/javascript" src="<%=ctxPath %>/libFront/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="<%=ctxPath %>/libFront/jquery.SuperSlide/2.1.1/jquery.SuperSlide.min.js"></script>

    <%--<script type="text/javascript" src="<%=ctxPath %>/static/h-ui/js/H-ui.js"></script>--%>
    <script type="text/javascript" src="http://static.h-ui.net/h-ui/3.1/js/H-ui.min.js"></script>

    <%--<script type="text/javascript" src="http://validform.rjboy.cn/wp-content/themes/validform/js/jquery-1.6.2.min.js"></script>
    <script type="text/javascript" src="http://validform.rjboy.cn/Validform/v5.1/Validform_v5.1_min.js"></script>--%>

    <title>${article.articleTitle}</title>
</head>
<body>

<div>

    <div style="clear: both;"></div>
    <div class="container">
        <div class="fly-panel detail-box" style="background-color: white">
<span class="layui-breadcrumb" style="visibility: visible;">
  <a href="<%=ctxPath%>/Index?action=index">首页</a><span lay-separator="">/</span>
  <a><cite>文章详情</cite></a>
</span>
            <hr>
            <h1 class="ue_t" label="Title center" name="tc"
                style="padding:0px 4px 0px 0px;text-align:center;margin:0px 0px 20px;"><span style="color:#c0504d;">${article.articleTitle}</span>
            </h1>
            <hr>
            <div class="detail-body photos" deep="4">
                ${article.articleContent}
            </div>
        </div>

    </div>
</div>
<div style="clear: both"></div>

<script type="text/javascript" src="<%=ctxPath%>/libFront/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="<%=ctxPath%>/libFront/jquery.SuperSlide/2.1.1/jquery.SuperSlide.min.js"></script>
<script type="text/javascript" src="<%=ctxPath%>/static/h-ui/js/H-ui.js"></script>

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
