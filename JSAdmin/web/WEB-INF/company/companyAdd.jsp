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
        <input type="hidden" name="action" value="addSubmit"/>
        <div class="row cl">
            <label class="form-label col-3"><span class="c-red">*</span>公司名称：</label>
            <div class="formControls col-5">
                <input type="text" class="input-text" value="" placeholder="" id="name" name="name" datatype="*" nullmsg="公司名称不能为空">
            </div>
            <div class="col-4"> </div>
        </div>
        <div class="row cl">
            <label class="form-label col-3"><span class="c-red">*</span>公司管理员手机号：</label>
            <div class="formControls col-5">
                <input type="text" class="input-text" value="" placeholder="" id="phoneNum" name="phoneNum" datatype="m" nullmsg="手机号不能为空">
            </div>
            <div class="col-4"> </div>
        </div>
        <div class="row cl">
            <label class="form-label col-3"><span class="c-red">*</span>公司管理员姓名：</label>
            <div class="formControls col-5">
                <input type="text" class="input-text" value="" placeholder="" id="companyAdminName" name="companyAdminName" datatype="*" nullmsg="姓名不能为空">
            </div>
            <div class="col-4"> </div>
        </div>
        <div class="row cl">
            <label class="form-label col-3"><span class="c-red">*</span>公司地址：</label>
            <div class="formControls col-5">
                <input type="text" class="input-text" value="" placeholder="" id="location" name="location" datatype="*" nullmsg="公司地址不能为空">
            </div>
            <div class="col-4"> </div>
        </div>

        <div class="row cl">
            <label class="form-label col-3"><span class="c-red">*</span>公司人数：</label>
            <div class="formControls col-5">
                <input type="text" class="input-text" value="" placeholder="" id="peopleNum" name="peopleNum" datatype="*" nullmsg="公司人数不能为空">
            </div>
            <div class="col-4"> </div>
        </div>
        <div class="row cl">
            <label class="form-label col-3"><span class="c-red">*</span>公司联系方式：</label>
            <div class="formControls col-5">
                <input type="text" class="input-text" value="" placeholder="" id="tel" name="tel" datatype="*" nullmsg="公司联系方式不能为空">
            </div>
            <div class="col-4"> </div>
        </div>
        <div class="row cl">
            <label class="form-label col-3"><span class="c-red">*</span>月最大用水量：</label>
            <div class="formControls col-5">
                <input type="text" class="input-text" value="" placeholder="" id="maxWaterMonth" name="maxWaterMonth" datatype="*" nullmsg="月最大用水量">
            </div>
            <div class="col-4"> </div>
        </div>

        <div class="row cl">
            <label class="form-label col-3"><span class="c-red">*</span>报表类型：</label>
            <div class="formControls col-5">
                <select id="reportTypeId" name="reportTypeId" datatype="*">
                    <option value="1">企业节水用水表</option>
                    <option value="2">机关学校节水用水表</option>
                    <option value="3">服务业医院节水用水表</option>
                </select>
            </div>
            <div class="col-4"> </div>
        </div>

        <div class="row cl">
            <label class="form-label col-3"><span class="c-red">*</span>初始密码：</label>
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