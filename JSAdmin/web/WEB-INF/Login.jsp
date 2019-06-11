<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String ctxPath = request.getContextPath();
%><!DOCTYPE html>
<html>

<head>
 <meta charset="utf-8" />
 <title>登录</title>
 <link rel="stylesheet" type="text/css" href="<%=ctxPath%>/css/loginForm2.css" />
</head>

<body>

<img class="bgone" src="<%=ctxPath%>/images/1.jpg" />
<img class="pic" src="<%=ctxPath%>/images/a.png" />

<div class="table">
 <div class="wel">某某系统后台登录</div>
 <div class="wel1">MOU MOU XI TONG HUO TAI DENG LU</div>
 <div class="user">
  <div style=""><img src="<%=ctxPath%>/images/yhm.png" /></div>
  <input type="text" name="phoneNum" placeholder="用户名" />
 </div>
 <div class="password">
  <div id="yonghu"><img src="<%=ctxPath%>/images/mm.png" /></div>
  <input type="password" name="password"  placeholder="●●●●●●"/>
 </div>
 <input id="btnLogin" class="btn" type="button" name="登录" value="登录" />
</div>

</body>
</html>