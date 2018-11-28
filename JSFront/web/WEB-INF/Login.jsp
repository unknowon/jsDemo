<%--
  Created by IntelliJ IDEA.
  User: Lulu
  Date: 2018/5/30
  Time: 22:12
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE HTML>
<html>
<head>
    <title>登录</title>
    <%@include file="/WEB-INF/header.jsp" %>
    <script type="text/javascript">
        $(function(){
            $("#imgVerifyCode").click();
            $("#imgVerifyCode").click(function(){
                $("#imgVerifyCode").attr("src","<%=ctxPath%>/Index?action=verifyCode&ts="+Math.random());
            });



            $("#btnLogin").click(function(){
                var data = $("#form-article-add").serializeArray();
                $.ajax({
                    type:"post",
                    url:"<%=ctxPath%>/Index",
                    data:data,
                    success:function(result){
                        if(result.status == "ok"){
                            location.href="<%=ctxPath%>/Index?action=index";
                        } else{

                            alert("登录出错" + result.msg);
                            $("#imgVerifyCode").click();
                        }
                    },
                    error:function(){
                        alert("登录请求失败");
                    }
                });
            });
        });


    </script>
</head>
<body>
<style>
    .row input{
        height: 50px;
    }
    .row label{
        line-height: 50px;
    }
</style>

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
                <br/>

            </div>


            <div class="col-xs-12 col-sm-6">
                <div id="holder">
                    <ul>
                        <c:forEach items="${notices}" var="notice">

                            <li>
                                <a href="<%=ctxPath%>/Notice?action=noticeView&noticeId=${notice.id}"><p>${notice.title}</p></a>
                            </li>

                        </c:forEach>
                    </ul>
                </div>
            </div>






        </div>
    </div>
    <div style="clear: both;"></div>
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
</div>
<div>
    <div class="container">
        <div class="row">
            <div class="col-xs-9 col-sm-9 artlistimg">

                <div class="row">
                    <div class="col-xs-6 col-sm-6" style="margin: 0 auto">
                        <article class="page-container">
                            <form class="form form-horizontal" method="post" action="" id="form-article-add">
                                <input type="hidden" name="action" value="loginSubmit"/>


                                <div class="row cl">
                                    <label class="form-label col-xs-4 col-sm-2">账号：</label>
                                    <div class="formControls col-xs-8 col-sm-9">
                                        <input type="text" class="input-text" required value="" placeholder="请输入你的账号" id="articletype" name="phoneNum">
                                    </div>
                                </div>

                                <div class="row cl">
                                    <label class="form-label col-xs-4 col-sm-2">密码：</label>
                                    <div class="formControls col-xs-8 col-sm-9">
                                        <input type="password" class="input-text" required value="" placeholder="请输入你的密码" id="articletitle2" name="password">
                                    </div>
                                </div>
                               <%-- <div class="row cl">
                                    <label class="form-label col-xs-4 col-sm-2" style="line-height: 65px">验证：</label>
                                    <div class="formControls col-xs-8 col-sm-9">

                                        <div class="row cl">
                                            <div class="formControls col-xs-6 col-sm-6">
                                                <input type="text" class="input-text" required value="" placeholder="请输入验证码" id="thecode" name="verifyCode">
                                            </div>
                                            <div class="formControls col-xs-6 col-sm-6">
                                                <img style="height: 50px" id="imgVerifyCode" src="<%=ctxPath%>/Index?action=verifyCode">
                                            </div>
                                        </div>
                                    </div>
                                </div>--%>

                                <div class="row cl">
                                    <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
                                        <button style="height: 50px" class="btn btn-secondary radius btn-block" type="button" id="btnLogin">登陆</button>
                                    </div>
                                </div>
                            </form>
                        </article>

                    </div>
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




<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="<%=ctxPath%>/assets/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="<%=ctxPath%>/assets/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="<%=ctxPath%>/assets/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="<%=ctxPath%>/assets/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript" src="<%=ctxPath%>/assets/lib/webuploader/0.1.5/webuploader.min.js"></script>

<script>

    //表单验证
    $("#form-article-add").validate({
        rules:{
            articletitle2:{
                required:true,
            },
            articlecolumn:{
                required:true,
            },
            articletype:{
                required:true,
            },
            articlesort:{
                required:true,
            },
            keywords:{
                required:true,
            },
            abstract:{
                required:true,
            },
            author:{
                required:true,
            },
            sources:{
                required:true,
            },
            allowcomments:{
                required:true,
            },
            commentdatemin:{
                required:true,
            },
            commentdatemax:{
                required:true,
            },

        },
        onkeyup:false,
        focusCleanup:true,
        success:"valid",
        submitHandler:function(form){
            //$(form).ajaxSubmit();
            var index = parent.layer.getFrameIndex(window.name);
            //parent.$('.btn-refresh').click();
            parent.layer.close(index);
        }
    });

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
/*    $.Huimarquee(200, 1, 3000);*/
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