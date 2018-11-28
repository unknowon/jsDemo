<%--
  Created by IntelliJ IDEA.
  User: Lulu
  Date: 2018/6/7
  Time: 22:39
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String ctxPath = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>My JSP 'index.jsp' starting page</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!-- 配置文件 -->
    <script type="text/javascript" src="<%=ctxPath%>/lib/ueditor/1.4.3/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="<%=ctxPath%>/lib/ueditor/1.4.3/ueditor.all.js"></script>
    <!-- 语言包文件(建议手动加载语言包，避免在ie下，因为加载语言失败导致编辑器加载失败) -->
    <script type="text/javascript" src="<%=ctxPath%>/lib/ueditor/1.4.3/lang/zh-cn/zh-cn.js"></script>
</head>
<body>
<script id="container" name="content" type="text/plain">这里写你的初始化内容</script>
<script type="text/javascript">
    var editor = UE.getEditor('container')
</script>
</body>
</html>