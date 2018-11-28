<%--&lt;%&ndash; Created by IntelliJ IDEA. &ndash;%&gt;--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title></title>
  </head>
  <body>
  <%
    response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
    response.sendRedirect(request.getContextPath() + "/Index?action=index");
  %>
  </body>
</html>

