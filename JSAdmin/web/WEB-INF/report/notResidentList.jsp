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
    <title>非居数据</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 报表管理 <span class="c-gray en">&gt;</span> 非居数据 <a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="pd-20">

    <div class="text-c"> 水量
        <input type="text" value="" name="water" id="water" style="width:120px;">
        以上

        <button type="submit" class="btn btn-success" id="btnSearch" name="btnSearch"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
    </div>

    <div class="cl pd-5 bg-1 bk-gray mt-20"><span class="l"><a href="javascript:;" onclick="member_add('导入月用水量','<%=ctxPath%>/Report?action=notResidentAdd','','510')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 导入非居数据</a></span></div>


    <div class="mt-20">
        <table class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
            <tr class="text-c">
                <th width="80">账户编号</th>
                <th width="200">用户名称</th>
                <th width="80">起度</th>
                <th width="80">止度</th>
                <th width="80">水量</th>
                <th width="80">金额</th>
                <th width="80">性质</th>
                <th width="80">口径</th>
                <th width="80">备注</th>
                <th width="80">水表地址</th>
                <th width="100">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${objs}" var="obj">
                <tr class="text-c">
                    <td><c:out value="${obj.number}"/></td>
                    <td><c:out value="${obj.name}"/></td>
                    <td><c:out value="${obj.start}"/></td>
                    <td><c:out value="${obj.end}"/></td>
                    <td><c:out value="${obj.water}"/></td>
                    <td><c:out value="${obj.money}"/></td>
                    <td><c:out value="${obj.type}"/></td>
                    <td><c:out value="${obj.caliber}"/></td>
                    <td><c:out value="${obj.remark}"/></td>
                    <td><c:out value="${obj.location}"/></td>

                    <td class="td-manage">
                        <a title="发送通知" href="javascript:;" onclick="member_edit('添加该公司','<%=ctxPath%>/Company?action=add4NotResident&name=${obj.name}')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe603;</i></a>
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
                {"orderable":false,"aTargets":[10]}// 制定列不参与排序
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
            var water = $("#water").val();
            window.location.href = "<%=ctxPath%>/Report?action=notResidentSearch&water="+water;
        });
    });


</script>
</body>
</html>
