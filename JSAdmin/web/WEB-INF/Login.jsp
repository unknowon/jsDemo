<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String ctxPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>
<title>后台登录</title>
<meta name="author" content="" />
<link rel="stylesheet" type="text/css" href="css/styleLogin.css" />


<style type="text/css">
body{
	height:100%;background:#16a085;overflow:hidden;
}
canvas{
	z-index:-1;position:absolute;
}
img{
	z-index:1;position:absolute;
}
</style>


<script type="text/javascript" src="lib/jquery/1.9.1/jquery.js"></script>
<script type="text/javascript" src="lib/Particleground.js"></script> 

<script type="text/javascript">
	$(function(){
		$("#imgVerifyCode").click();
		$("#imgVerifyCode,#kanbuq").click(function(){
			$("#imgVerifyCode").attr("src","<%=ctxPath%>/Index?action=verifyCode&ts="+Math.random());
		});
		
		$("#btnLogin").click(function(){
			var data = $("#formLogin").serializeArray();
			$.ajax({
				type:"post",
				url:"<%=ctxPath%>/Index",
				data:data,
				success:function(result){
					if(result.status == "ok"){
						location.href="<%=ctxPath%>/Index?action=index";
					} else{
						
						alert("登录出错" + result.msg);
						$("#imgVerifyCode").click();
					}
				},
				error:function(){
					alert("登录请求失败");
				}
			});
		});
	});
	
	
</script>

<script>
$(document).ready(function() {
  //粒子背景特效
  $('body').particleground({
    dotColor: '#5cbdaa',
    lineColor: '#5cbdaa'
    //
    
  });
 
});
</script>


</head>
<body >
<form action="index.html" id="formLogin" method="post">
<input type="hidden" name="action" value="loginSubmit"/>

<dl class="admin_login">
 <dt>
  <strong>毕业论文管理系统</strong>
  <em>Management System</em>
 </dt>
 <dd class="user_icon">
  <input type="text" name="phoneNum" placeholder="手机号" value="" class="login_txtbx"/>
 </dd>
 <dd class="pwd_icon">
  <input type="password" name="password" placeholder="密码" value="" class="login_txtbx"/>
 </dd>

 <dd>
  <input type="button" value="立即登陆" id="btnLogin" class="submit_btn" />
 </dd>
 <dd>

 </dd>
</dl>
</form>
</body>
</html>
