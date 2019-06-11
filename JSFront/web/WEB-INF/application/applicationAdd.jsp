<%--
  Created by IntelliJ IDEA.
  User: Lulu
  Date: 2018/6/8
  Time: 7:02
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE HTML>
<html>
<head>
    <title>发送申请</title>
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
<body>

<header class="navbar-wrapper theader" style="height: 114px !important;">
    <div class="navbar" style="height: 70px">
        <div class="headleft">
            <a class="" href="<%=ctxPath%>//Index?action=index"><img src="<%=ctxPath %>/assets/logo.png"></a>
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
    <div class="container" style="min-width: 1090px !important;">

        <div class="row">
            <div class="col-xs-9 col-sm-9 artlistimg">

                <div class="tabCon bk-gray pd-10" role="tabpanel" data-filtered="filtered" style="display: block;">
                    <div>
                        <ul>
                            <li>
                                <a href="<%=ctxPath%>/Index?action=center" class="layui-btn btn-danger">单位管理</a>
                                <a href="<%=ctxPath%>/Application?action=add" class="layui-btn btn-danger">在线申请</a>
                                <a href="<%=ctxPath%>/Article?action=add" class="layui-btn btn-danger">信息提交</a>
                            </li>
                            <li>
                                <h3 style="text-align: center">
                                    在线申请
                                </h3>
                                <form class="form form-horizontal" id="form-article-add">
                                    <input type="hidden" name="action" value="addSubmit"/>

                                    <div class="row cl">
                                        <label class="form-label col-xs-4 col-sm-2">标题：</label>
                                        <div class="formControls col-xs-8 col-sm-9">
                                            <input type="text" class="input-text" value="" placeholder="" id="title"
                                                   name="title">
                                        </div>
                                    </div>

                                    <div class="row cl">
                                        <label class="form-label col-xs-4 col-sm-2">类型：</label>
                                        <div class="formControls col-xs-8 col-sm-9">

                                        </div>
                                    </div>

                                    <div class="row cl" id="waterdiv">
                                        <label class="form-label col-xs-4 col-sm-2">水量：</label>
                                        <div class="formControls col-xs-8 col-sm-9">
                                            <input type="text" class="input-text" value="" placeholder="" id="water"
                                                   name="water">
                                        </div>
                                    </div>


                                    <div class="row cl">
                                        <label class="form-label col-xs-4 col-sm-2">相关内容及文件：</label>
                                        <div class="formControls col-xs-8 col-sm-9">
                                            <script id="content" name="content" type="text/plain"></script>
                                        </div>
                                    </div>

                                    <div class="row cl">
                                        <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
                                            <button onClick="article_save_submit();" class="btn btn-primary radius"
                                                    type="button" id="btnSave"><i class="Hui-iconfont">&#xe632;</i> 保存并提交审核
                                            </button>
                                        </div>
                                    </div>
                                </form>
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
                        <li>
                            <a href="<%=ctxPath%>/Notice?action=noticeView&noticeId=${notice.id}">${notice.title}</a>
                        </li>
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
<script type="text/javascript" src="<%=ctxPath%>//assets/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="<%=ctxPath%>//assets/lib/jquery.SuperSlide/2.1.1/jquery.SuperSlide.min.js"></script>
<script type="text/javascript" src="<%=ctxPath %>/assets/lib/Validform/5.3.2/Validform.min.js"></script>
<script type="text/javascript" src="<%=ctxPath%>//assets/static/h-ui/js/H-ui.js"></script>

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="<%=ctxPath%>/assets/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="<%=ctxPath%>/assets/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="<%=ctxPath%>/assets/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="<%=ctxPath%>/assets/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript" src="<%=ctxPath%>/assets/lib/webuploader/0.1.5/webuploader.min.js"></script>
<script type="text/javascript" src="<%=ctxPath%>/assets/lib/ueditor/1.4.3/ueditor.config.js"></script>
<script type="text/javascript" src="<%=ctxPath%>/assets/lib/ueditor/1.4.3/ueditor.all.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vue@2.5.16/dist/vue.js"></script>

<script type="text/javascript">
    $("#waterdiv").hide();
    var ue = UE.getEditor('content',{
        height:500
    });
    $("#shenqingtype").change(function(e){
        if ($(this).val()==1||$(this).val()==2)
        {
            $("#waterdiv").show();
        }
        else
        {
            $("#waterdiv").hide();
        }

//添加所需要执行的操作代码
    })
    //表单验证
    $("#form-article-add").validate({
        rules: {
            title: {
                required: true,
            },
            content: {
                required: true,
            }

        },
        onkeyup: false,
        focusCleanup: true,
        success: "valid",
        submitHandler: function (form) {
            //$(form).ajaxSubmit();
            var index = parent.layer.getFrameIndex(window.name);
            //parent.$('.btn-refresh').click();
            parent.layer.close(index);
        }
    });


    $(function(){

        //必须放到页面初始化的时候，不能放到按钮点击里面
        var validForm = $("#form-article-add").Validform({tiptype:2});//初始化校验器
        $("#btnSave").click(function(){
            if(validForm.check(false)==false)//表单校验不通过
            {
                return;
            }

            var data = $("#form-article-add").serializeArray();//<input type="hidden" name="action" value="addSubmit"/>
            $.ajax({
                url:"<%=ctxPath%>/Application",type:"post",
                data:data,
                success:function(result){
                    if(result.status=="ok")
                    {
                        alert("添加成功");
                        location.replace("<%=ctxPath%>/Index?action=center");
                    }
                    else
                    {
                        alert("保存失败"+result.msg);
                    }
                },
                error:function(){alert("保存网络请求失败");}
            });
        });
    });

</script>

</body>
</html>