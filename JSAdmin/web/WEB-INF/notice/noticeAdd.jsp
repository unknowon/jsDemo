<%--
  Created by IntelliJ IDEA.
  User: Lulu
  Date: 2018/5/18
  Time: 19:34
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@include file="/WEB-INF/header.jsp"%>
    <title>新增文章</title>
</head>
<body>
<div class="pd-20">
    <form action="" method="post" class="form form-horizontal" id="form-article-add">
        <input type="hidden" name="action" value="addSubmit"/>
        <input type="hidden" name="companyId" value="${companyId}"/>

        <div class="row cl">
            <label class="form-label col-2"><span class="c-red">*</span>通知标题：</label>
            <div class="formControls col-10">
                <input type="text" class="input-text" value="" placeholder="" id="" name="title">
            </div>
        </div>

        <div class="row cl">
            <label class="form-label col-2">通知描述：</label>
            <div class="formControls col-10">
                <textarea name="desc" cols="" rows="" class="textarea"  placeholder="说点什么...最少输入10个字符" datatype="*10-100" dragonfly="true" nullmsg="备注不能为空！" onKeyUp="textarealength(this,200)"></textarea>
                <p class="textarea-numberbar"><em class="textarea-length">0</em>/200</p>
            </div>
        </div>

        <div class="row cl">
            <label class="form-label col-2">通知内容：</label>
            <div class="formControls col-10">
                <script id="editor" name="content" type="text/plain" style="width:100%;height:400px;"></script></div>
        </div>

        <div class="row cl">
            <div class="col-10 col-offset-2">
                <input class="btn btn-primary radius" type="button" id="btnSave" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
                <button onClick="layer_close();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
            </div>
        </div>
    </form>
</div>


<script type="text/javascript" src="lib/icheck/jquery.icheck.min.js"></script>
<script type="text/javascript" src="lib/webuploader/0.1.5/webuploader.min.js"></script>
<script type="text/javascript" src="lib/ueditor/1.4.3/ueditor.config.js"></script>
<script type="text/javascript" src="lib/ueditor/1.4.3/ueditor.all.min.js"> </script>
<script type="text/javascript" src="lib/ueditor/1.4.3/lang/zh-cn/zh-cn.js"></script>

<script type="text/javascript">
    var ue = UE.getEditor('editor');

    $(function(){

        //必须放到页面初始化的时候，不能放到按钮点击里面
        var validForm = $("#form-article-add").Validform({tiptype:2});//初始化校验器
        $("#btnSave").click(function(){
            if(validForm.check(false)==false)//表单校验不通过
            {
                return;
            }

            var data = $("#form-article-add").serializeArray();//<input type="hidden" name="action" value="addSubmit"/>
            $.ajax({
                url:"<%=ctxPath%>/Notice",type:"post",
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