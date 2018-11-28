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
    <br/>
    <article class="cl pd-20">
        <div class="text-c"> 日期范围：
            <input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')||\'%y-%M-%d\'}'})" id="startTime" value="${startTime}" name="startTime" class="input-text Wdate" style="width:120px;">
            -
            <input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',maxDate:'%y-%M-%d'})" id="endTime" value="${endTime}" name="endTime" class="input-text Wdate" style="width:120px;">

            <button type="submit" class="btn btn-success" id="btnSearch" name="btnSearch"><i class="Hui-iconfont">&#xe665;</i> 搜用户</button>
        </div>
        <h3>企业类报表统计</h3>
        <table class="table table-border table-bordered table-bg">
            <tbody>
            <tr class="text-c">
                <td rowspan="3">用途</td>
                <td rowspan="3">用水人数</td>
                <td rowspan="3">产品种类</td>
                <td rowspan="3">产量</td>
                <td colspan="3">产值</td>
                <td rowspan="3">水源种类</td>
                <td colspan="5">新水量（米3）</td>
                <td colspan="4">重复利用水量（米3）</td>
                <td rowspan="3">重复利用率（%）</td>
                <td rowspan="3">备注</td>
            </tr>
            <tr class="text-c">
                <td rowspan="2">本季度</td>
                <td colspan="2">增加值</td>
                <td rowspan="2">本月计划量</td>
                <td colspan="3">本月实际取用水量</td>
                <td rowspan="2">本年累计数</td>
                <td colspan="3">本月重复利用水量</td>
                <td rowspan="2" >本年累计数</td>
            </tr>
            <tr class="text-c">
                <td >本季度</td>
                <td >本年累计</td>
                <td >新取用水量</td>
                <td >非常规水利用量</td>
                <td >合计</td>
                <td >冷却</td>
                <td >工艺</td>
                <td >锅炉</td>
            </tr>
            <tr>
                <td>工业用水</td>
                <td>${result[0]}</td>
                <td>${result[1]}</td>
                <td>${result[2]}</td>
                <td>${result[3]}</td>
                <td>${result[4]}</td>
                <td>${result[5]}</td>
                <td>${result[6]}</td>
                <td>${result[7]}</td>
                <td>${result[8]}</td>
                <td>${result[9]}</td>
                <td>${result[10]}</td>
                <td>${result[11]}</td>
                <td>${result[12]}</td>
                <td>${result[13]}</td>
                <td>${result[14]}</td>
                <td>${result[15]}</td>
                <td>${result[16]}</td>
                <td></td>
            </tr>
            </tbody>
        </table>
    </article>
    <hr/>

    <article class="cl pd-20">
        <h3>服务业、医院类报表统计</h3>
        <table class="table table-border table-bordered table-bg">
            <tbody>
            <tr class="text-c">
                <td rowspan="3">用途</td>
                <td rowspan="3">职工(居民)人数(人)</td>
                <td rowspan="3">床位或座位数</td>
                <td rowspan="3">服务或门诊人数</td>
                <td colspan="4">产值</td>
                <td rowspan="3">月用水定额</td>
                <td rowspan="3">水源种类</td>
                <td colspan="3">新水量（米3）</td>
                <td colspan="2">重复利用水量（米3）</td>
                <td rowspan="3">重复利用率（%）</td>
                <td rowspan="3">备注</td>
            </tr>
            <tr class="text-c">
                <td rowspan="2">本季度</td>
                <td rowspan="2">本年</td>
                <td colspan="2">增加值</td>
                <td rowspan="2">本月计划量</td>
                <td rowspan="2">非常规用水量</td>
                <td rowspan="2">本年累计数</td>
                <td rowspan="2" >本年累计数</td>
                <td rowspan="2" >本年累计数</td>
            </tr>
            <tr class="text-c">
                <td >本季度</td>
                <td >本年累计</td>
            </tr>
            <tr>
                <td>工业用水</td>
                <td>${result[17]}</td>
                <td>${result[18]}</td>
                <td>${result[19]}</td>
                <td>${result[20]}</td>
                <td>${result[21]}</td>
                <td>${result[22]}</td>
                <td>${result[23]}</td>
                <td>${result[24]}</td>
                <td>${result[25]}</td>
                <td>${result[26]}</td>
                <td>${result[27]}</td>
                <td>${result[28]}</td>
                <td>${result[29]}</td>
                <td>${result[30]}</td>
                <td>${result[31]}</td>
                <td></td>
            </tr>
            </tbody>
        </table>
    </article>
    <hr/>

    <article class="cl pd-20">
        <h3>机关、学校类报表统计</h3>
        <table class="table table-border table-bordered table-bg">
            <tbody>
            <tr class="text-c">
                <td rowspan="3">用途</td>
                <td rowspan="3">职工人数(人)</td>
                <td rowspan="2" colspan="2">学生人数(人)</td>
                <td rowspan="3">月用水定额</td>
                <td rowspan="3">水源种类</td>
                <td colspan="2">新水量（米3）</td>
                <td colspan="3">非常规用水量</td>
                <td colspan="2">重复利用水量（米3）</td>
                <td rowspan="3">重复利用率（%）</td>
                <td rowspan="3">备注</td>
            </tr>
            <tr class="text-c">
                <td rowspan="2">本季度</td>
                <td rowspan="2">本年</td>
                <td rowspan="2">雨水利用量</td>
                <td rowspan="2">中水利用量</td>
                <td rowspan="2">本年累计</td>
                <td rowspan="2" >本月累计数</td>
                <td rowspan="2" >本年累计数</td>
            </tr>
            <tr class="text-c">
                <td>住校</td>
                <td>走读</td>
            </tr>
            <tr>
                <td>工业用水</td>
                <td>${result[32]}</td>
                <td>${result[33]}</td>
                <td>${result[34]}</td>
                <td>${result[35]}</td>
                <td>${result[36]}</td>
                <td>${result[37]}</td>
                <td>${result[38]}</td>
                <td>${result[39]}</td>
                <td>${result[40]}</td>
                <td>${result[41]}</td>
                <td>${result[42]}</td>
                <td>${result[43]}</td>
                <td>${result[44]}</td>
                <td></td>
            </tr>
            </tbody>
        </table>
    </article>
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