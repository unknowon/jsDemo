<%--
  Created by IntelliJ IDEA.
  User: Lulu
  Date: 2018/6/2
  Time: 5:43
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@include file="/WEB-INF/header.jsp"%>
    <title>我的桌面</title>
</head>
<body>

<div class="pd-20" style="padding-top:20px;">

    <p class="f-20 text-success">欢迎使用水务后台管理系统! <span class="f-14"></span></p>
    <br/>

    <table class="table table-border table-bordered table-bg">
        <thead>
        <tr>
            <th colspan="7" scope="col">当月信息统计</th>
        </tr>
        <tr class="text-c">
            <th>总用水量</th>
            <th>计划总用水量</th>
            <th>已上报计划总用水量</th>
            <th>单位总数</th>
            <th>已上报单位总数</th>
            <th>超过计划用水量单位数</th>
        </tr>
        </thead>
        <tbody>
        <tr class="text-c">
            <td>${monthListInf[0]}</td>
            <td>${monthListInf[1]}</td>
            <td>${monthListInf[2]}</td>
            <td>${monthListInf[3]}</td>
            <td>${monthListInf[4]}</td>
            <td>
               ${monthListInf[5]}
            </td>
        </tr>
        </tbody>
    </table>
    <br/>

    <table class="table table-border table-bordered table-bg">
        <thead>
        <tr>
            <th colspan="7" scope="col">当季信息统计</th>
        </tr>
        <tr class="text-c">
            <th>总用水量</th>
            <th>计划总用水量</th>
            <th>已上报计划总用水量</th>
            <th>单位总数</th>
            <th>已上报单位总数</th>
            <th>超过计划用水量单位数</th>
        </tr>
        </thead>
        <tbody>
        <tr class="text-c">
            <td>${quarterListInf[0]}</td>
            <td>${quarterListInf[1]}</td>
            <td>${quarterListInf[2]}</td>
            <td>${quarterListInf[3]}</td>
            <td>${quarterListInf[4]}</td>
            <td>
                ${quarterListInf[5]}
            </td>
        </tr>
        </tbody>
    </table>
    <br/>

    <table class="table table-border table-bordered table-bg">
        <thead>
        <tr>
            <th colspan="7" scope="col">当年信息统计</th>
        </tr>
        <tr class="text-c">
            <th>总用水量</th>
            <th>计划总用水量</th>
            <th>已上报计划总用水量</th>
            <th>单位总数</th>
            <th>已上报单位总数</th>
            <th>超过计划用水量单位数</th>
        </tr>
        </thead>
        <tbody>
        <tr class="text-c">
            <td>${yearListInf[0]}</td>
            <td>${yearListInf[1]}</td>
            <td>${yearListInf[2]}</td>
            <td>${yearListInf[3]}</td>
            <td>${yearListInf[4]}</td>
            <td>
                ${yearListInf[5]}
            </td>
        </tr>
        </tbody>
    </table>
    <br/>
</div>


<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="js/H-ui.js"></script>
<script>
    var _hmt = _hmt || [];
    (function() {
        var hm = document.createElement("script");
        hm.src = "//hm.baidu.com/hm.js?080836300300be57b7f34f4b3e97d911";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
    })();
    var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
    document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3F080836300300be57b7f34f4b3e97d911' type='text/javascript'%3E%3C/script%3E"));


</script>
</body>
</html>