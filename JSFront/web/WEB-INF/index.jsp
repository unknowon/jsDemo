<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE HTML>
<html>
<head>
    <title>毕节市七星关区城市节水数字平台数据填报系统</title>
    <%@include file="/WEB-INF/header.jsp"%>
</head>
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
                <ul id="ulTest" class="cl">
                    <li id="0" class="current active"><a href="<%=ctxPath%>/Index?action=index">首页</a></li>
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
    <div class="container">
        <div class="row">
            <div class="col-xs-12 col-sm-6">
                <div id="slider-3">
                    <div class="slider">
                        <div class="bd banner">
                            <ul>
                                <c:forEach items="${banners}" var="banner">
                                    <li><a href="#"><img src="${banner.bannerUrl}"></a></li>
                                </c:forEach>

                            </ul>
                        </div>
                        <ol class="hd cl dots">
                            <li>1</li>
                            <li>2</li>
                            <li>3</li>
                            <li>4</li>
                        </ol>
                    </div>
                </div>
            </div>
            <div class="col-xs-12 col-sm-6">
                <div>

                    <input type="text" placeholder="输入关键字进行搜索" class="input-text radius size-L"
                           style="width: 80%;float: left;border-top-right-radius: 0px;border-bottom-right-radius: 0px;background:none ">
                    <input class="btn radius btn-secondary size-L" type="button" value="搜索"
                           style="width: 20%;border-top-left-radius: 0px;border-bottom-left-radius: 0px;">
                </div>
                <div class="Hui-tags" STYLE="border: none">

                    <input type="hidden" class="Hui-tags-val" name="" value="">
                </div>
            </div>
                    <div class="col-xs-12 col-sm-6">
                        <ul>
                            <c:forEach items="${notices}" var="notice">

                            <li>
                                <a href="<%=ctxPath%>/Notice?action=noticeView&noticeId=${notice.id}"><p>${notice.title}</p></a>
                            </li>

                            </c:forEach>

                        </ul>
                    </div>
                <br/>
        </div>
    </div>
    <div style="clear: both;"></div>
    <div style="background: url('<%=ctxPath %>/assets/static/jieshui/img/flbg.png');background-size: 100%;height: 300px;margin-top: 50px"
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
</div>


<div>
    <div class="container">
        <div class="row">
            <div class="col-xs-9 col-sm-9 artlistimg">
                <div id="tab_demo" class="HuiTab">
                
                    <div class="tabBar clearfix" align="center" style="margin: 0 auto; ">
                        <span id="" role="tab" class="current">节水技术</span>
                        <span role="tab" class="">政策法规</span>
                        <span role="tab" class="">业内新闻</span>
                        <span role="tab" class="">水平衡指南</span>
                        <span role="tab" class="">人水和谐</span>
                    </div>
                    
                    
                    <c:forEach items="${articles }" var="article">
                    <div class="tabCon bk-gray pd-10" role="tabpanel" data-filtered="filtered" style="display: block;">
                        <div class="artlistimg">
                            <ul>
                            
                            <c:forEach items="${article }" var="art">
                                <li>
                                    <div class="row">
                                        <a href="<%=ctxPath%>/Article?action=articleView&id=${art.articleId }">
                                            <div class="col-xs-3 col-sm-3 artlistimg">
                                                <img src="${art.articleImg }">
                                            </div>
                                            <div class="col-xs-9 col-sm-9">
                                                <h3>
                                                    ${art.articleTitle }
                                                </h3>
                                                <p style="height: 40px">
                                                    ${art.articleDesc }
                                                </p>
                                            </div>
                                        </a>
                                    </div>
                                </li>
                            </c:forEach>

                            </ul>
                            <div style="clear: both"></div>
                        </div>
                    </div>
                    </c:forEach>
                    
                   
                    
                </div>
            </div>
            <div class="col-xs-3 col-sm-3" style="padding-top: 76px">
                <div id="slider-2">
                    <div class="slider">
                        <div class="bd banner2">
                            <ul>
                                <c:forEach items="${banners}" var="banner">
                                    <li><a href="#"><img src="${banner.bannerUrl}"></a></li>
                                </c:forEach>
                            </ul>
                        </div>
                        <ol class="hd cl dots">
                            <li>1</li>
                            <li>2</li>
                            <li>3</li>
                            <li>4</li>
                        </ol>
                    </div>
                </div>


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

    /*$.Huimarquee(200, 1, 3000);*/
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
    };
</script>
</body>
</html>