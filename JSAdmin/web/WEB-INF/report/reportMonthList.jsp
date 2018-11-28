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
    <br/>
    <div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"> <a class="btn btn-primary radius" href="<%=ctxPath%>/Report?action=downloadMonthReport"><i class="Hui-iconfont">&#xe674;</i> 下载当月报表</a></span> </div>
    <br/>
    <table class="table table-border table-bordered table-bg">
        <thead>
        <tr>
            <th colspan="7" scope="col">信息统计</th>
        </tr>
        <tr class="text-c">
            <th>总用水量</th>
            <th>计划总用水量</th>
            <th>已上报计划总用水量</th>
            <th>单位总数</th>
            <th>已上报单位总数</th>
            <th>超过计划用水量单位数</th>
        </tr>
        </thead>
        <tbody>
        <tr class="text-c">
            <td>${listInf[0]}</td>
            <td>${listInf[1]}</td>
            <td>${listInf[2]}</td>
            <td>${listInf[3]}</td>
            <td>
                <a title="查看已填报单位" href="javascript:;" onclick="nowWater('查看已填报单位')" class="ml-5" style="text-decoration:none">${listInf[4]}</a>
            </td>
            <td>
                <a title="查看超量单位" href="javascript:;" onclick="member_edit('查看超量单位')" class="ml-5" style="text-decoration:none">${listInf[5]}</a>
            </td>
        </tr>
        </tbody>
    </table>



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


    function nowWater(title,url,id,w,h){
        var startTime = $("#startTime").val();
        var endTime = $("#endTime").val();
        layer_show(title,'<%=ctxPath%>/Report?action=listNowWater&startTime='+startTime+'&endTime='+endTime,w,h);

    }

    /*用户-编辑*/
    function member_edit(title,url,id,w,h){
        var startTime = $("#startTime").val();
        var endTime = $("#endTime").val();
        layer_show(title,'<%=ctxPath%>/Report?action=listExWater&startTime='+startTime+'&endTime='+endTime,w,h);

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
            window.location.href = "<%=ctxPath%>/Report?action=searchMonthList&startTime="+startTime+"&endTime="+endTime;
        });
    });


</script>
</body>
</html>
