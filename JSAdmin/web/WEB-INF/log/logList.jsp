<%--
  Created by IntelliJ IDEA.
  User: Lulu
  Date: 2018/5/25
  Time: 21:41
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
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 用户中心 <span class="c-gray en">&gt;</span> 用户管理 <a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="pd-20">


    <div class="mt-20">
        <table class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
            <tr class="text-c">
                <th width="80">ID</th>
                <th width="300">操作时间</th>
                <th width="300">操作人姓名</th>
                <th width="300">操作人电话</th>
                <th>操作内容</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${logs}" var="log">
                <tr class="text-c">
                    <td><c:out value="${log.id}"/></td>
                    <td><c:out value="${log.createDateTime}"/></td>
                    <td><c:out value="${log.adminUserName}"/></td>
                    <td><c:out value="${log.adminUserPhoneNum}"/></td>
                    <td><c:out value="${log.message}"/></td>
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




</script>
</body>
</html>