<%--
  User: Lulu
  Date: 2018/5/18
  Time: 9:44
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@include file="/WEB-INF/header.jsp" %>
    <title>管理员列表</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 管理员管理 <span class="c-gray en">&gt;</span> 管理员列表 <a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="pd-20">
    <%--<div class="text-c"> 日期范围：
        <input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}'})" id="datemin" class="input-text Wdate" style="width:120px;">
        -
        <input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d'})" id="datemax" class="input-text Wdate" style="width:120px;">
        <input type="text" class="input-text" style="width:250px" placeholder="输入管理员名称" id="" name="">
        <button type="submit" class="btn btn-success" id="" name=""><i class="Hui-iconfont">&#xe665;</i> 搜用户</button>
    </div>--%>

    <div class="cl pd-5 bg-1 bk-gray mt-20">
        <span class="l"> <a href="javascript:;" onclick="admin_add('添加管理员','<%=ctxPath %>/AdminUser?action=add','800','500')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加管理员</a></span>  </div>
    <table class="table table-border table-bordered table-bg">
        <thead>
        <tr>
            <th scope="col" colspan="9">管理员列表</th>
        </tr>
        <tr class="text-c">

            <th width="80">ID</th>
            <th width="200">姓名</th>
            <th width="250">手机</th>
            <th>角色</th>
            <th width="130">创建时间</th>
            <th width="100">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${adminUsers}" var="adminUser" varStatus="loop">
            <tr class="text-c">

                <td><c:out value="${adminUser.id }"/> </td>
                <td><c:out value="${adminUser.name }"/></td>
                <td><c:out value="${adminUser.phoneNum }"/></td>
                <td><c:out value="${adminRoles[loop.count-1] }"/></td>
                <td><c:out value="${adminUser.createDateTime }"/></td>
                <td class="td-manage">
                    <a title="编辑" href="javascript:;" onclick="layer_show('编辑','<%=ctxPath %>/AdminUser?action=edit&id=${adminUser.id }','800','510')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a>
                    <a title="删除" href="javascript:;" onclick="adminUser_del(this,'${adminUser.id}')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a>
                </td>
            </tr>
        </c:forEach>
        <%--<tr class="text-c">
            <td><input type="checkbox" value="1" name=""></td>
            <td>1</td>
            <td>admin</td>
            <td>13000000000</td>
            <td>admin@mail.com</td>
            <td>超级管理员</td>
            <td>2014-6-11 11:11:42</td>
            <td class="td-status"><span class="label label-success radius">已启用</span></td>
            <td class="td-manage"><a style="text-decoration:none" onClick="admin_stop(this,'10001')" href="javascript:;" title="停用"><i class="Hui-iconfont">&#xe631;</i></a> <a title="编辑" href="javascript:;" onclick="admin_edit('管理员编辑','admin-add.html','1','800','500')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a> <a title="删除" href="javascript:;" onclick="admin_del(this,'1')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
        </tr>
        <tr class="text-c">
            <td><input type="checkbox" value="2" name=""></td>
            <td>2</td>
            <td>zhangsan</td>
            <td>13000000000</td>
            <td>admin@mail.com</td>
            <td>栏目编辑</td>
            <td>2014-6-11 11:11:42</td>
            <td class="td-status"><span class="label radius">已停用</span></td>
            <td class="td-manage"><a style="text-decoration:none" onClick="admin_start(this,'10001')" href="javascript:;" title="启用"><i class="Hui-iconfont">&#xe615;</i></a> <a title="编辑" href="javascript:;" onclick="admin_edit('管理员编辑','admin-add.html','2','800','500')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a> <a title="删除" href="javascript:;" onclick="admin_del(this,'1')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
        </tr>--%>
        </tbody>
    </table>
</div>

<script type="text/javascript">
    /*
        参数解释：
        title	标题
        url		请求的url
        id		需要操作的数据id
        w		弹出层宽度（缺省调默认值）
        h		弹出层高度（缺省调默认值）
    */
    /*管理员-增加*/
    function admin_add(title,url,w,h){
        layer_show(title,url,w,h);
    }

    /*管理员-删除*/
    function adminUser_del(link,id){
        layer.confirm('确认要删除吗？',function(index){
            $.ajax({
                url:"<%=ctxPath%>/AdminUser",type:"post",
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
    /*管理员-编辑*/
    function admin_edit(title,url,id,w,h){
        layer_show(title,url,w,h);
    }

</script>
</body>
</html>
