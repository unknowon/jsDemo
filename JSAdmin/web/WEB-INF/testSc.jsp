<%--
  Created by IntelliJ IDEA.
  User: Lulu
  Date: 2018/8/31
  Time: 0:15
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>

<body>
    <table>
        <tr>
            <th>区域</th>
            <th>驻村干部</th>
        </tr>
        <tr>
            <td>${wcf.d.regionname}</td>
            <td>${wcf.d.zcgb}</td>
        </tr>
    </table>
</body>
</html>