<%--
  Created by IntelliJ IDEA.
  User: Lulu
  Date: 2018/6/8
  Time: 10:11
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--_meta 作为公共模版分离出去-->
<!DOCTYPE HTML>
<html>
<head>
    <%@include file="/WEB-INF/header.jsp"%>
    <title>审核</title>
</head>
<style>
    .pd-20 .form-label
    {
        text-align: right;
    }
</style>
<body>

    <nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页
        <span class="c-gray en">&gt;</span>
        申请中心
        <span class="c-gray en">&gt;</span>
        申请管理
        <span class="c-gray en">&gt;</span>
        审核
        <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
    </nav>

    <div class="Hui-article">
        <div class="cl pd-20" style=" background-color:#5bacb6">
            <dl style="margin-left:80px; color:#fff">
                <dt><span class="f-18">${application.companyName}</span> <span class="pl-10 f-12">${company.tel}</span></dt>
                <dd class="pt-10 f-12" style="margin-left:0">${company.location}</dd>
            </dl>
        </div>
        <div class="pd-20">
            <h3 style="text-align: center">${application.title}</h3>
            <div>
                ${application.content}
            </div>
        </div>
        <hr/>
        <div class="pd-20">
            <div class="cl pd-5 bg-1 bk-gray mt-20">
                <span class="l">工作人员审批</span>
            </div>

            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2">是否通过：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <select  class="select">

                        <option selected value="1" class="select-option">是</option>

                    </select>
                </div>
            </div>
            <br/>

            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2">理由：</label>
                <div class="formControls col-xs-8 col-sm-9">
					<textarea style="width: 100%;height: 200px"  id="onereason">
                        ${application.oneContent}
					</textarea>
                </div>
            </div>
            <br/>


        </div>
        <hr/>
        <div class="pd-20">
            <div class="cl pd-5 bg-1 bk-gray mt-20">
                <span class="l">管理人员审批</span>
            </div>

            <form action="" method="post" class="form form-horizontal" id="form-add">
                <input type="hidden" name="action" value="changeStatusSubmit2"/>
                <input type="hidden" name="id" value="${application.id}"/>

                <div class="pd-20">
                    <label class="form-label col-xs-4 col-sm-2">是否通过：</label>
                    <div class="formControls col-xs-8 col-sm-9">
                        <select id="twotype" name="twotype" class="select">
                            <option class="select-option">请先选择类型</option>
                            <option value="1" class="select-option">是</option>
                            <option value="2" class="select-option">否</option>
                        </select>
                    </div>
                </div>

                <br/>
                <div class="pd-20">
                    <label class="form-label col-xs-4 col-sm-2">理由：</label>
                    <div class="formControls col-xs-8 col-sm-9">
                        <textarea style="width: 100%;height: 200px" cols="7" id="tworeason" name="twoContent">
                        </textarea>
                    </div>
                </div>
                <br/>

                <div class="row cl">
                    <label class="form-label col-xs-4 col-sm-2"></label>
                    <div class="formControls col-xs-8 col-sm-9">
                        <button id="btnSave" type="button" class="btn btn-block btn-primary radius">提交</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <script type="text/javascript">
        $(function(){

            //必须放到页面初始化的时候，不能放到按钮点击里面
            var validForm = $("#tform-add").Validform({tiptype:2});//初始化校验器
            $("#btnSave").click(function(){
                if(validForm.check(false)==false)//表单校验不通过
                {
                    return;
                }

                var data = $("#form-add").serializeArray();//<input type="hidden" name="action" value="addSubmit"/>

                $.ajax({
                    url:"<%=ctxPath%>/Application",type:"post",
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