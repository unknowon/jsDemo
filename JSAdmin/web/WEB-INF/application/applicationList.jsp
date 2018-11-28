<%--
  Created by IntelliJ IDEA.
  User: Lulu
  Date: 2018/6/8
  Time: 9:00
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@include file="/WEB-INF/header.jsp"%>
    <title>申请管理</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 申请中心 <span class="c-gray en">&gt;</span> 申请管理 <a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="pd-20">

        <table class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
            <tr class="text-c">
                <th width="80">ID</th>
                <th width="200">申请标题</th>
                <th width="200">申请公司</th>
                <th width="80">申请人</th>
                <th width="200">申请时间</th>
                <th width="200">申请类型</th>
                <th width="100">当前状态</th>
                <th width="100">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${applications}" var="application">
                <tr class="text-c">
                    <td><c:out value="${application.id}"/></td>
                    <td><c:out value="${application.title}"/></td>
                    <td><c:out value="${application.companyName}"/></td>
                    <td><c:out value="${application.applicant}"/></td>
                    <td><c:out value="${application.addTime}"/></td>
                    <td>
                        <c:choose>
                            <c:when test="${application.type == 1}"><c:out value="增加用水量"/></c:when>
                            <c:when test="${application.type == 2}"><c:out value="降低用水量"/></c:when>
                            <c:when test="${application.type == 3}"><c:out value="停止用水"/></c:when>
                            <c:otherwise><c:out value="没有选择类型"/></c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${application.status == 0}"><c:out value="待审核"/></c:when>
                            <c:when test="${application.status == 1}"><c:out value="待同意"/></c:when>
                            <c:when test="${application.status == 2}"><c:out value="已同意"/></c:when>
                            <c:otherwise><c:out value="状态不明"/></c:otherwise>
                        </c:choose>
                    </td>
                    <td class="td-manage">

                        <c:choose>
                            <c:when test="${application.status == 0}"><a title="审核" href="javascript:;" onclick="member_edit('审核','<%=ctxPath%>/Application?action=changeStatus&id=${application.id}')" class="ml-5" style="text-decoration:none">审核</a></c:when>
                            <c:when test="${application.status == 1}"><a title="同意" href="javascript:;" onclick="member_edit('同意','<%=ctxPath%>/Application?action=changeStatus2&id=${application.id}')" class="ml-5" style="text-decoration:none">同意</a></c:when>
                            <c:when test="${application.status == 2}"><c:out value="已同意"/></c:when>
                            <c:when test="${application.status == 3}"><c:out value="审核失败"/></c:when>
                            <c:otherwise><c:out value="不可操作"/></c:otherwise>
                        </c:choose>
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
                {"orderable":false,"aTargets":[7]}// 制定列不参与排序
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


    /*用户-编辑*/
    function member_edit(title,url,id,w,h){
        layer_show(title,url,w,h);
    }


</script>
</body>
</html>
