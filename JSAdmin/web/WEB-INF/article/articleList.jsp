<%--
  User: Lulu
  Date: 2018/5/19
  Time: 23:02
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@include file="/WEB-INF/header.jsp" %>
    <title>资讯列表</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 文章管理 <span class="c-gray en">&gt;</span> ${articles[0].articleTypeName} <a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="pd-20">
    <%--<div class="text-c">  日期范围：
        <input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'logmax\')||\'%y-%M-%d\'}'})" id="logmin" class="input-text Wdate" style="width:120px;">
        -
        <input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'logmin\')}',maxDate:'%y-%M-%d'})" id="logmax" class="input-text Wdate" style="width:120px;">
        <input type="text" name="" id="" placeholder=" 资讯名称" style="width:250px" class="input-text">
        <button name=""     id="" class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i> 搜文章</button>
    </div>--%>
    <div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"> <a class="btn btn-primary radius" onclick="article_add('添加资讯','<%=ctxPath%>/Article?action=articleAdd&typeId=${typeId}')" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加${articles[0].articleTypeName}</a></span> </div>
    <div class="mt-20">
        <table class="table table-border table-bordered table-bg table-hover table-sort">
            <thead>
            <tr class="text-c">
                <th width="100">ID</th>
                <th>标题</th>
                <th width="120">作者</th>
                <th width="120">更新时间</th>
                <th width="120">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${articles}" var="article">
                <tr class="text-c">
                    <td>${article.articleId}</td>
                    <td class="text-l"><u style="cursor:pointer" class="text-primary" onClick="article_edit('查看','<%=ctxPath%>/Article?action=articleView&id=${article.articleId}','10001')" title="查看">${article.articleTitle}</u></td>
                    <td>${article.articleAuthor}</td>
                    <td>${article.articleUpTime}</td>
                    <td class="f-14 td-manage"> <a style="text-decoration:none" class="ml-5" onClick="article_edit('编辑','<%=ctxPath%>/Article?action=edit&id=${article.articleId}','10001')" href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a>
                        <a style="text-decoration:none" class="ml-5" onClick="article_del(this,'${article.articleId}')" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<script type="text/javascript">
    $('.table-sort').dataTable({
        "aaSorting": [[ 0, "asc" ]],//默认第几个排序
        "bStateSave": true,//状态保存
        "aoColumnDefs": [
            //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
            {"orderable":false,"aTargets":[4]}// 制定列不参与排序
        ]
    });

    /*资讯-添加*/
    function article_add(title,url,w,h){
        var index = layer.open({
            type: 2,
            title: title,
            content: url
        });
        layer.full(index);
    }
    /*资讯-编辑*/
    function article_edit(title,url,id,w,h){
        var index = layer.open({
            type: 2,
            title: title,
            content: url
        });
        layer.full(index);
    }
    /*资讯-删除*/
    function article_del(link,id){
        layer.confirm('确认要删除吗？',function(index){
            $.ajax({
                url:"<%=ctxPath%>/Article",type:"post",
                data:{action:"delete",id:id},
                success:function(obj) {
                    if(obj.status=="ok") {
                        $(link).parents("tr").remove();
                        layer.msg('已删除!',{icon:1,time:1000});
                    }
                    else {
                        alert("删除失败");
                    }
                },
                error:function(){alert("删除处理失败");}
            })
        });
    }

</script>
</body>
</html>