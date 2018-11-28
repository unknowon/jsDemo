<%--
  User: Lulu
  Date: 2018/5/29
  Time: 17:14
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE HTML>
<html>
<head>
    <%String ctxPath = request.getContextPath(); %>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>网站标题，控制在25个字、50个字节以内</title>
    <meta name="keywords" content="关键词,5个左右,单个8汉字以内">
    <meta name="description" content="网站描述，字数尽量空制在80个汉字，160个字符以内！">
    <link rel="Bookmark" href="favicon.ico">
    <link rel="Shortcut Icon" href="favicon.ico"/>
    <!--[if lt IE 9]>
    <script type="text/javascript" src="<%=ctxPath%>/assets/lib/html5shiv.js"></script>
    <script type="text/javascript" src="<%=ctxPath%>/assets/lib/respond.min.js"></script>
    <![endif]-->
    <link href="<%=ctxPath%>/assets/static/h-ui/css/H-ui.css" rel="stylesheet" type="text/css"/>
    <link href="<%=ctxPath%>/assets/lib/Hui-iconfont/1.0.8/iconfont.css" rel="stylesheet" type="text/css"/>
    <link href="<%=ctxPath%>/assets/static/jieshui/css/global.css" rel="stylesheet" type="text/css"/><!--自己的样式-->
    <link href="<%=ctxPath%>/assets/static/jieshui/css/style.css" rel="stylesheet" type="text/css"/><!--自己的样式-->
    <!--[if IE 6]>
    <script type="text/javascript" src="<%=ctxPath%>/assets/lib/DD_belatedPNG_0.0.8a-min.js"></script>
    <script>DD_belatedPNG.fix('.pngfix,.icon');</script>
    <![endif]-->
</head>
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
            <a class="" href="/aboutHui.shtml"><img src="<%=ctxPath %>/assets/logo.png"></a>
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
                    <li id="1" ><a href="<%=ctxPath %>/Article?action=articleList&typeId=1">机构简介</a></li>
                    <li id="2" ><a href="<%=ctxPath %>/Article?action=articleList&typeId=2">政务公开</a></li>
                    <li id="3" ><a href="<%=ctxPath %>/Article?action=articleList&typeId=3">政策法规</a></li>
                    <li id="4" ><a href="<%=ctxPath %>/Article?action=articleList&typeId=4">业内新闻</a></li>
                    <li id="5" ><a href="<%=ctxPath %>/Article?action=articleList&typeId=5">人水和谐</a></li>
                    <li id="6" ><a href="<%=ctxPath %>/Article?action=articleList&typeId=6">节水型城市</a></li>
                    <li id="7" ><a href="<%=ctxPath %>/Article?action=articleList&typeId=7">联系我们</a></li>
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
                    <a href="<%=ctxPath %>/Article?action=articleList&typeId=10">
                        <img src="<%=ctxPath %>/assets/static/jieshui/img/zhinan.png">
                        <h3 style="color: white">办事指南</h3>
                    </a>
                </div>
                <div class="col-xs-3 col-sm-3">
                    <a href="<%=ctxPath %>/Article?action=download">
                        <img src="<%=ctxPath %>/assets/static/jieshui/img/down.png">
                        <h3 style="color: white">文件下载</h3>
                    </a>
                </div>
            </div>
        </div>
    </div>
    <div style="clear: both;"></div>
    <div class="container" style="min-width: 1090px !important;">
        <article class="page-container">
            <form class="form form-horizontal" id="form-article-add">
                <input type="hidden" name="action" value="addSubmit3"/>
                <div class="row cl">
                    <table  class="table  table-border table-bordered  radius table-striped table-hover" style="margin: 10px;background: white">
                        <thead>
                        <tr>
                            <td  colspan="2" class="text-r" style="width: 200px;white-space: nowrap">
                                表号：节水统计3表
                            </td>
                            <td rowspan="5" colspan="3" class="text-c"><h1 style="text-align: center">毕节市七星关区机关、学校节水用水月报表</h1></td>

                        </tr>
                        <tr>
                            <td class="text-r" colspan="2" style="width: 200px;white-space: nowrap">
                                制表机关：毕节市七星关区节水办
                            </td>
                        </tr>
                        <tr>
                            <td  class="text-r" colspan="2" style="width: 200px;white-space: nowrap">
                                批准机关：毕节市七星关区统计局
                            </td>
                        </tr>
                        <tr>
                            <td  class="text-r" colspan="2" style="width: 200px;white-space: nowrap">
                                有效期：2016年5月至2021年5月
                            </td>
                        </tr>
                        <tr>
                            <td  class="text-r" colspan="2" style="width: 200px;white-space: nowrap">
                                批准文号：七星统字（2016）7号
                            </td>
                        </tr>
                        </thead>
                        <tbody class="table table-border  table-bordered radius table-striped table-hover">
                        <tr>
                            <td colspan="3" class="text-r">用途</td>
                            <td class="text-c">1</td>
                            <td width="60%" class="text-c">业务用水</td>
                        </tr>
                        <tr>
                            <td colspan="3" class="text-r">职工人数(人)</td>
                            <td class="text-c">2</td>
                            <td><input class="input-text" id="input1" name="input1" placeholder="请输入职工人数(人)" /></td>
                        </tr>
                        <tr>
                            <td colspan="2" rowspan="2" class="text-r">学生人数（人）</td>
                            <td class="text-r">住校</td>
                            <td class="text-c">3</td>
                            <td><input class="input-text" id="input2" name="input2" placeholder="请输入住校学生人数" /></td>
                        </tr>
                        <tr>
                            <td class="text-r">走读</td>
                            <td class="text-c">4</td>
                            <td><input class="input-text" id="input3" name="input3" placeholder="请输入走读学生人数" /></td>
                        </tr>
                        <tr>
                            <td class="text-r" colspan="3">水源种类</td>
                            <td class="text-c">5</td>
                            <td><input class="input-text" id="input4" name="input4" placeholder="请输入水源种类" /></td>
                        </tr>
                        <tr>
                            <td class="text-r" colspan="3">取用水定额</td>
                            <td class="text-c">6</td>
                            <td><input class="input-text" id="input5" name="input5" placeholder="请输入取用水定额" /></td>
                        </tr>
                        <tr>
                            <td colspan="2" rowspan="2" class="text-r">新取用水量（立方米）</td>
                            <td class="text-r" width="100" style="white-space: nowrap">本月</td>
                            <td class="text-c">7</td>
                            <td><input class="input-text" id="input6" name="input6" placeholder="请输入本月新取用水量" /></td>
                        </tr>
                        <tr>
                            <td class="text-r">本年累计</td>
                            <td class="text-c">8</td>
                            <td><input class="input-text" id="input7" name="input7" placeholder="请输入本年累计新取用水量" /></td>
                        </tr>
                        <tr>
                            <td colspan="2" rowspan="3" class="text-r">非常规水利用量(立方米）</td>
                            <td class="text-r" width="100" style="white-space: nowrap">雨水利用量</td>
                            <td class="text-c">9</td>
                            <td><input class="input-text" id="input8" name="input8" placeholder="请输入本月非常规雨水利用量" /></td>
                        </tr>
                        <tr>
                            <td class="text-r">中水利用量</td>
                            <td class="text-c">10</td>
                            <td><input class="input-text" id="input9" name="input9" placeholder="请输入中非常规雨水利用量" /></td>
                        </tr>
                        <tr>
                            <td class="text-r">本年累计</td>
                            <td class="text-c">11</td>
                            <td><input class="input-text" id="input10" name="input10" placeholder="请输入本年累计非常规雨水利用量" /></td>
                        </tr>
                        <tr>
                            <td colspan="2" rowspan="2" class="text-r">重复利用水量(米3)</td>
                            <td class="text-r" width="100" style="white-space: nowrap">本月</td>
                            <td class="text-c">12</td>
                            <td><input class="input-text" id="input11" name="input11" placeholder="请输入本月累计重复利用水量" /></td>
                        </tr>
                        <tr>
                            <td class="text-r">本年累计</td>
                            <td class="text-c">13</td>
                            <td><input class="input-text" id="input12" name="input12" placeholder="请输入本年累计重复利用水量（米3）" /></td>
                        </tr>
                        <tr>
                            <td colspan="3" class="text-r">重复利用率%(自动计算，不需要填写)</td>
                            <td class="text-c">14</td>
                            <td><input class="input-text" id="input13" name="input13" placeholder="请输入重复利用率%" /></td>
                        </tr>
                        <tr>
                            <td colspan="3" class="text-r">备注</td>
                            <td class="text-c">15</td>
                            <td width="600"><input class="input-text" id="input14" name="input14" placeholder="请输入备注" /></td>
                        </tr>
                        <tr>
                            <td colspan="3" class="text-r">填报单位(章)</td>
                            <td class="text-c">16</td>
                            <td width="600"><input class="input-text" id="input15" name="input15" placeholder="请输入填报单位" /></td>
                        </tr>
                        <tr>
                            <td colspan="3" class="text-r">行业号</td>
                            <td class="text-c">17</td>
                            <td width="600"><input class="input-text" id="input16" name="input16" placeholder="请输入行业号" /></td>
                        </tr>
                        <tr>
                            <td colspan="3" class="text-r">系统号</td>
                            <td class="text-c">18</td>
                            <td width="600"><input class="input-text" id="input17" name="input17" placeholder="请输入系统号" /></td>
                        </tr>
                        <tr>
                            <td colspan="3" class="text-r">填报日期</td>
                            <td class="text-c">19</td>
                            <td width="600"><input class="input-text" id="input18" name="input18" placeholder="请输入填报日期" /></td>
                        </tr>
                        <tr>
                            <td colspan="3" class="text-r">单位节水管理部门</td>
                            <td class="text-c">20</td>
                            <td width="600"><input class="input-text" id="input19" name="input19" placeholder="请输入单位节水管理部门" /></td>
                        </tr>
                        <tr>
                            <td colspan="3" class="text-r">电话</td>
                            <td class="text-c">21</td>
                            <td width="600"><input class="input-text" id="input20" name="input20" placeholder="请输入电话" /></td>
                        </tr>
                        <tr>
                            <td colspan="3" class="text-r">填表人</td>
                            <td class="text-c">22</td>
                            <td width="600"><input class="input-text" id="input21" name="input21" placeholder="请输入填表人" /></td>
                        </tr>
                        <tr>
                            <td colspan="3" class="text-r">水表号</td>
                            <td class="text-c">23</td>
                            <td width="600"><input class="input-text" id="input22" name="input22" placeholder="请输入水表号" /></td>
                        </tr>
                        <tr>
                            <td colspan="3" class="text-r"> </td>
                            <td class="text-c"></td>
                            <td width="600">
                                <button class="btn btn-secondary radius btn-block" type="button" id="btnSave"> 确认提交</button>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>

                <div class="row cl">
                    <label class="form-label col-2">附件：</label>
                    <div class="formControls col-10">
                        <script id="editor" name="attachments" type="text/plain"></script>
                    </div>
                </div>

            </form>
        </article>

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

<script type="text/javascript" src="<%=ctxPath%>/assets/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=ctxPath%>/assets/static/h-ui/js/H-ui.js"></script>

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="<%=ctxPath%>/assets/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="<%=ctxPath%>/assets/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="<%=ctxPath%>/assets/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="<%=ctxPath%>/assets/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript" src="<%=ctxPath%>/assets/lib/webuploader/0.1.5/webuploader.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vue@2.5.16/dist/vue.js"></script>

<script type="text/javascript" src="<%=ctxPath%>/assets/lib/ueditor/1.4.3/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="<%=ctxPath%>/assets/lib/ueditor/1.4.3/ueditor.all.js"></script>
<!-- 语言包文件(建议手动加载语言包，避免在ie下，因为加载语言失败导致编辑器加载失败) -->
<script type="text/javascript" src="<%=ctxPath%>/assets/lib/ueditor/1.4.3/lang/zh-cn/zh-cn.js"></script>


<script type="text/javascript">


    //表单验证
    $("#btnSave").click(function () {
        if ($("#input1").val().trim() == "") {
            layer.msg('表单不能存在空项!', {icon: 2, time: 1000});
            $("#input1").focus()
        }
        else if ($("#input2").val()==0) {
            layer.msg('表单不能存在空项!', {icon: 2, time: 1000});
            $("#input2").focus()
        }
        else if ($("#input3").val().trim() == "") {
            layer.msg('表单不能存在空项!', {icon: 2, time: 1000});
            $("#input3").focus()
        }
        else if ($("#input4").val().trim() == "") {
            layer.msg('表单不能存在空项!', {icon: 2, time: 1000});
            $("#input4").focus()
        }
        else if ($("#input5").val().trim() == "") {
            layer.msg('表单不能存在空项!', {icon: 2, time: 1000});
            $("#input5").focus()
        }
        else if ($("#input6").val().trim() == "") {
            layer.msg('表单不能存在空项!', {icon: 2, time: 1000});
            $("#input6").focus()
        }
        else if ($("#input7").val().trim() == "") {
            layer.msg('表单不能存在空项!', {icon: 2, time: 1000});
            $("#input7").focus()
        }
        else if ($("#input8").val().trim() == "") {
            layer.msg('表单不能存在空项!', {icon: 2, time: 1000});
            $("#input8").focus()
        }
        else if ($("#input9").val().trim() == "") {
            layer.msg('表单不能存在空项!', {icon: 2, time: 1000});
            $("#input9").focus()
        }
        else if ($("#input10").val().trim() == "") {
            layer.msg('表单不能存在空项!', {icon: 2, time: 1000});
            $("#input10").focus()
        }
        else if ($("#input11").val().trim() == "") {
            layer.msg('表单不能存在空项!', {icon: 2, time: 1000});
            $("#input11").focus()
        }
        else if ($("#input12").val().trim() == "") {
            layer.msg('表单不能存在空项!', {icon: 2, time: 1000});
            $("#input12").focus()
        }
        else if ($("#input13").val().trim() == "") {
            layer.msg('表单不能存在空项!', {icon: 2, time: 1000});
            $("#input13").focus()
        }
        else if ($("#input14").val().trim() == "") {
            layer.msg('表单不能存在空项!', {icon: 2, time: 1000});
            $("#input14").focus()
        }
        else if ($("#input15").val().trim() == "") {
            layer.msg('表单不能存在空项!', {icon: 2, time: 1000});
            $("#input15").focus()
        }
        else if ($("#input16").val().trim() == "") {
            layer.msg('表单不能存在空项!', {icon: 2, time: 1000});
            $("#input16").focus()
        }
        else if ($("#input17").val().trim() == "") {
            layer.msg('表单不能存在空项!', {icon: 2, time: 1000});
            $("#input17").focus()
        }
        else if ($("#input18").val().trim() == "") {
            layer.msg('表单不能存在空项!', {icon: 2, time: 1000});
            $("#input18").focus()
        }
        else if ($("#input19").val().trim() == "") {
            layer.msg('表单不能存在空项!', {icon: 2, time: 1000});
            $("#input19").focus()
        }
        else if ($("#input20").val().trim() == "") {
            layer.msg('表单不能存在空项!', {icon: 2, time: 1000});
            $("#input20").focus()
        }
        else if ($("#input21").val().trim() == "") {
            layer.msg('表单不能存在空项!', {icon: 2, time: 1000});
            $("#input21").focus()
        }
        else if ($("#input22").val().trim() == "") {
            layer.msg('表单不能存在空项!', {icon: 2, time: 1000});
            $("#input22").focus()
        }


        else {
            layer.load();
            $.ajax({
                cache: true,
                type: "POST",
                url: "<%=ctxPath%>/Report",//url
                data: $('#form-article-add').serialize(),// 你的formid
                async: false,
                error: function (request) {
//                layer.msg("Connection error");
                    layer.closeAll('loading');
                    layer.msg('添加出错!', {icon: 2, time: 1000});
                },
                success:function(result){
                    if(result.status=="ok")
                    {
                        alert("添加成功");
                        location.replace("<%=ctxPath%>/Index?action=index");
                    }
                    else
                    {
                        alert("保存失败"+result.msg);
                    }
                },
            });

        }


    });

</script>

<script type="text/javascript">
    var ue = UE.getEditor('editor');
</script>

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