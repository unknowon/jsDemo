<%--
  Created by IntelliJ IDEA.
  User: Lulu
  Date: 2018/5/22
  Time: 23:40
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@include file="/WEB-INF/header.jsp"%>
    <title>用户管理</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 报表管理 <span class="c-gray en">&gt;</span> 报表管理 <a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="pd-20">

    <div class="text-c"> 日期范围：
        <input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')||\'%y-%M-%d\'}'})" id="startTime" value="${startTime}" name="startTime" class="input-text Wdate" style="width:120px;">
        -
        <input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',maxDate:'%y-%M-%d'})" id="endTime" value="${endTime}" name="endTime" class="input-text Wdate" style="width:120px;">

        <button type="submit" class="btn btn-success" id="btnSearch" name="btnSearch"><i class="Hui-iconfont">&#xe665;</i> 搜用户</button>
    </div>

    <div class="cl pd-5 bg-1 bk-gray mt-20"><span class="l"><a href="javascript:;" onclick="member_add('导入月用水量','<%=ctxPath%>/Report?action=updateMonthWater','','510')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 导入月用水量</a></span></div>


    <div class="mt-20">
        <table class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
            <tr class="text-c">
                <th width="80">ID</th>
                <th width="200">填报公司</th>
                <th>水表号</th>
                <th width="200">报表类型</th>

                <th width="80">添加时间</th>
                <th width="80">月最大用水量</th>
                <th width="80">本月用水量</th>
                <th width="80">本月实际用水量</th>
                <th width="100">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${reports}" var="report">
                <tr class="text-c">
                    <td><c:out value="${report.id}"/></td>
                    <td><c:out value="${report.companyName}"/></td>

                    <td><c:out value="${report.content}"/></td>

                    <td><c:out value="${report.reportTypeId}"/></td>
                    <td><c:out value="${report.addTime}"/></td>

                    <td><c:out value="${report.companyMaxWaterMonth}"/></td>

                    <td><c:choose><c:when test="${report.companyMaxWaterMonth < report.newWater}"><span class="label label-warning radius"><c:out value="${report.newWater}"/></span></c:when><c:otherwise><span class="label label-success radius"><c:out value="${report.newWater}"/></span></c:otherwise></c:choose></td>

                    <td><c:out value="${report.monthWater}"/></td>

                    <td class="td-manage">
                        <a title="下载" href="${report.fileUrl}"><i class="Hui-iconfont">&#xe6de;</i></a>
                        <a title="删除" href="javascript:;" onclick="member_del(this,'${report.id}')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a>
                        <a title="发送通知" href="javascript:;" onclick="member_edit('发送通知','<%=ctxPath%>/Notice?action=addnew&companyId=${report.companyId}')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe603;</i></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<script type="text/javascript">
    $(function(){
        $('.table-sort').dataTable({
            "aaSorting": [[ 0, "asc" ]],//默认第几个排序
            "bStateSave": true,//状态保存
            "aoColumnDefs": [
                //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
                {"orderable":false,"aTargets":[4]}// 制定列不参与排序
            ]
        });
        $('.table-sort tbody').on( 'click', 'tr', function () {
            if ( $(this).hasClass('selected') ) {
                $(this).removeClass('selected');
            }
            else {
                table.$('tr.selected').removeClass('selected');
                $(this).addClass('selected');
            }
        });
    });
    function member_add(title,url,w,h){
        layer_show(title,url,w,h);
    }

    /*用户-编辑*/
    function member_edit(title,url,id,w,h){
        layer_show(title,url,w,h);
    }

    function member_del(link,id){
        layer.confirm('确认要删除吗？',function(index){
            $.ajax({
                url:"<%=ctxPath%>/Report",type:"post",
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
    $(function(){
        $('#btnSearch').click(function(){
            var startTime = $("#startTime").val();
            var endTime = $("#endTime").val();
            window.location.href = "<%=ctxPath%>/Report?action=search&startTime="+startTime+"&endTime="+endTime;


        });
    });


</script>
</body>
</html>
