<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="zf" uri="http://www.js.gov/functions" %>


<h1>出错啦！</h1>
<c:out escapeXml="false" value="${errorMsg }"></c:out>


<c:if test="${zf:equals(errorMsg, '未登录')}">
    <%
        response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
        response.sendRedirect(request.getContextPath() + "/Index?action=login");
    %>
</c:if>
