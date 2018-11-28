<%--
  Created by IntelliJ IDEA.
  User: Lulu
  Date: 2018/5/18
  Time: 19:34
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@include file="/WEB-INF/header.jsp" %>
    <title>添加管理员</title>
</head>
<body>
<div class="pd-20">
    <form action="" method="post" class="form form-horizontal" id="form-add">
        <input type="hidden" name="action" value="linkAddSubmit"/>
        <div class="row cl">
            <label class="form-label col-3"><span class="c-red">*</span>名称</label>
            <div class="formControls col-5">
                <input type="text" class="input-text" value="" placeholder="" id="name" name="name" >
            </div>
            <div class="col-4"> </div>
        </div>
        <div class="row cl">
            <label class="form-label col-3"><span class="c-red">*</span>链接</label>
            <div class="formControls col-5">
                <input type="text" class="input-text" value="" placeholder="" id="url" name="url" >
            </div>
            <div class="col-4"> </div>
        </div>




        <div class="row cl">
            <div class="col-9 col-offset-3">
                <input class="btn btn-primary radius" type="button" id="btnSave" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
            </div>
        </div>
    </form>
</div>


<script type="text/javascript">
    $(function(){

        //必须放到页面初始化的时候，不能放到按钮点击里面
        var validForm = $("#form-add").Validform({tiptype:2});//初始化校验器
        $("#btnSave").click(function(){
            if(validForm.check(false)==false)//表单校验不通过
            {
                return;
            }

            var data = $("#form-add").serializeArray();//<input type="hidden" name="action" value="addSubmit"/>
            $.ajax({
                url:"<%=ctxPath%>/Link",type:"post",
                data:data,
                success:function(result){
                    if(result.status=="ok")
                    {
                        parent.location.reload();//刷新父窗口
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