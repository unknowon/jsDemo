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
        <input type="hidden" name="action" value="updatePwdSubmit"/>
        <input type="hidden" name="id" value="${company.id}"/>
        <div class="row cl">
            <label class="form-label col-3"><span class="c-red">*</span>${company.name}</label>

            <div class="col-4"> </div>
        </div>

        <div class="row cl">
            <label class="form-label col-3"><span class="c-red">*</span>修改密码：</label>
            <div class="formControls col-5">
                <input type="password" class="input-text" value="" placeholder="" id="password" name="password" datatype="*" nullmsg="初始密码不能为空">
            </div>
            <div class="col-4"> </div>
        </div>
        <div class="row cl">
            <label class="form-label col-3"><span class="c-red">*</span>确认密码：</label>
            <div class="formControls col-5">
                <input type="password" class="input-text" value="" placeholder="" recheck="password"  id="password2" name="password2" datatype="*" nullmsg="确认密码不能为空">
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
                url:"<%=ctxPath%>/Company",type:"post",
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