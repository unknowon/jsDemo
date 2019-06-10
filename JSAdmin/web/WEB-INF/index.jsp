<%--
  User: Lulu
  Date: 2018/5/8
  Time: 17:25
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@include file="/WEB-INF/header.jsp" %>
    <title>毕节市七星关区城市节水数字平台数据填报系统</title>

</head>
<body>
<header class="Hui-header cl"> <a class="Hui-logo l" title="毕节市七星关区城市节水数字平台数据填报系统" href="<%=ctxPath%>/">毕节市七星关区城市节水数字平台数据填报系统</a> <a class="Hui-logo-m l" href="<%=ctxPath%>/" title="毕节市七星关区城市节水数字平台数据填报系统"></a> <span class="Hui-subtitle l"></span>

    <ul class="Hui-userbar">
        <li class="dropDown dropDown_hover"><a href="#" class="dropDown_A">${adminUser.name} </a>

        </li>
        <li id="Hui-skin" class="dropDown right dropDown_hover"><a href="javascript:;" title="换肤"><i class="Hui-iconfont" style="font-size:18px">&#xe62a;</i></a>
            <ul class="dropDown-menu radius box-shadow">
                <li><a href="javascript:;" data-val="default" title="默认（黑色）">默认（黑色）</a></li>
                <li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
                <li><a href="javascript:;" data-val="green" title="绿色">绿色</a></li>
                <li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
                <li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>
                <li><a href="javascript:;" data-val="orange" title="绿色">橙色</a></li>
            </ul>
        </li>
    </ul>
    <a aria-hidden="false" class="Hui-nav-toggle" href="#"></a> </header>
<aside class="Hui-aside">
    <input runat="server" id="divScrollValue" type="hidden" value="" />
    <div class="menu_dropdown bk_2">
        <dl id="menu-article">
            <dt><i class="Hui-iconfont">&#xe616;</i> 文章管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a _href="<%=ctxPath %>/Article?action=articleList&typeId=1" href="javascript:void(0)">机构简介</a></li>
                    <li><a _href="<%=ctxPath %>/Article?action=articleList&typeId=2" href="javascript:void(0)">政务公开</a></li>
                    <li><a _href="<%=ctxPath %>/Article?action=articleList&typeId=3" href="javascript:void(0)">政策法规</a></li>
                    <li><a _href="<%=ctxPath %>/Article?action=articleList&typeId=4" href="javascript:void(0)">业内新闻</a></li>
                    <li><a _href="<%=ctxPath %>/Article?action=articleList&typeId=5" href="javascript:void(0)">人水和谐</a></li>
                    <li><a _href="<%=ctxPath %>/Article?action=articleList&typeId=6" href="javascript:void(0)">节水型城市</a></li>
                    <li><a _href="<%=ctxPath %>/Article?action=articleList&typeId=7" href="javascript:void(0)">联系我们</a></li>
                    <li><a _href="<%=ctxPath %>/Article?action=articleList&typeId=8" href="javascript:void(0)">节水技术</a></li>
                    <li><a _href="<%=ctxPath %>/Article?action=articleList&typeId=9" href="javascript:void(0)">水平衡指南</a></li>
                    <li><a _href="<%=ctxPath %>/Article?action=articleList&typeId=10" href="javascript:void(0)">办事指南</a></li>
                    <li><a _href="<%=ctxPath %>/Article?action=articleList&typeId=11" href="javascript:void(0)">文件下载</a></li>
                    <li><a _href="<%=ctxPath %>/Article?action=articleList&typeId=12" href="javascript:void(0)">信息提交</a></li>
                </ul>
            </dd>
        </dl>
        <dl id="menu-picture">
            <dt><i class="Hui-iconfont">&#xe613;</i> 轮播图管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a _href="<%=ctxPath %>/Banner?action=bannerList" href="javascript:void(0)">轮播图管理123</a></li>
                </ul>
            </dd>
        </dl>
        <%--<dl id="menu-product">
            <dt><i class="Hui-iconfont">&#xe620;</i> 产品管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a _href="product-brand.html" href="javascript:void(0)">品牌管理</a></li>
                    <li><a _href="product-category.html" href="javascript:void(0)">分类管理</a></li>
                    <li><a _href="product-list.html" href="javascript:void(0)">产品管理</a></li>
                </ul>
            </dd>
        </dl>--%>
        <!--<dl id="menu-page">
            <dt><i class="Hui-iconfont">&#xe626;</i> 页面管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a _href="page-home.html" href="javascript:void(0)">首页管理</a></li>
                    <li><a _href="page-flinks.html" href="javascript:void(0)">友情链接</a></li>
                </ul>
            </dd>
        </dl>-->
        <dl id="menu-comments">
            <dt><i class="Hui-iconfont">&#xe622;</i> 外链管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a _href="<%=ctxPath%>/Link?action=linkList" href="javascript:;">外链列表</a></li>
                </ul>
            </dd>
        </dl>
        <!--<dl id="menu-order">
            <dt><i class="Hui-iconfont">&#xe63a;</i> 财务管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a _href="order-list.html" href="javascript:void(0)">订单列表</a></li>
                    <li><a _href="recharge-list.html" href="javascript:void(0)">充值管理</a></li>
                    <li><a _href="invoice-list.html" href="javascript:void(0)">发票管理</a></li>
                </ul>
            </dd>
        </dl>-->
        <dl id="menu-member">
            <dt><i class="Hui-iconfont">&#xe60d;</i> 公司管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a _href="<%=ctxPath %>/Company?action=list" href="javascript:;">会员列表</a></li>
                    <%--<li><a _href="member-del.html" href="javascript:;">删除的会员</a></li>
                    <li><a _href="member-level.html" href="javascript:;">等级管理</a></li>
                    <li><a _href="member-scoreoperation.html" href="javascript:;">积分管理</a></li>
                    <li><a _href="member-record-browse.html" href="javascript:void(0)">浏览记录</a></li>
                    <li><a _href="member-record-download.html" href="javascript:void(0)">下载记录</a></li>
                    <li><a _href="member-record-share.html" href="javascript:void(0)">分享记录</a></li>--%>
                </ul>
            </dd>
        </dl>
        <dl id="menu-member">
            <dt><i class="Hui-iconfont">&#xe66d;</i> 申请管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a _href="<%=ctxPath %>/Application?action=list&status=9" href="javascript:;">申请列表</a></li>
                    <li><a _href="<%=ctxPath %>/Application?action=list&status=0" href="javascript:;">待审核列表</a></li>
                    <li><a _href="<%=ctxPath %>/Application?action=list&status=1" href="javascript:;">待同意列表</a></li>
                </ul>
            </dd>
        </dl>
        <dl id="menu-admin">
            <dt><i class="Hui-iconfont">&#xe62d;</i> 管理员管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a _href="<%=ctxPath %>/Role?action=list" href="javascript:void(0)">角色管理</a></li>
                    <li><a _href="<%=ctxPath %>/AdminUser?action=list" href="javascript:void(0)">管理员管理</a></li>
                </ul>
            </dd>
        </dl>
        <dl id="menu-tongji">
            <dt><i class="Hui-iconfont">&#xe61a;</i> 报表管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a _href="<%=ctxPath%>/Report?action=countList" href="javascript:void(0)">报表统计</a></li>
                    <li><a _href="<%=ctxPath%>/Report?action=list" href="javascript:void(0)">报表管理</a></li>
                    <li><a _href="<%=ctxPath%>/Report?action=monthList" href="javascript:void(0)">月报表管理</a></li>
                    <li><a _href="<%=ctxPath%>/Report?action=quarterList" href="javascript:void(0)">季度报表管理</a></li>
                    <li><a _href="<%=ctxPath%>/Report?action=yearList" href="javascript:void(0)">年报表管理</a></li>
                    <li><a _href="<%=ctxPath%>/Report?action=notResidentList" href="javascript:void(0)">非居数据</a></li>
                    <%--<li><a _href="charts-2.html" href="javascript:void(0)">时间轴折线图</a></li>
                    <li><a _href="charts-3.html" href="javascript:void(0)">区域图</a></li>
                    <li><a _href="charts-4.html" href="javascript:void(0)">柱状图</a></li>
                    <li><a _href="charts-5.html" href="javascript:void(0)">饼状图</a></li>
                    <li><a _href="charts-6.html" href="javascript:void(0)">3D柱状图</a></li>
                    <li><a _href="charts-7.html" href="javascript:void(0)">3D饼状图</a></li>--%>
                </ul>
            </dd>
        </dl>
        <dl id="notice-system">
            <dt><i class="Hui-iconfont">&#xe62b;</i> 通知公告<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a _href="<%=ctxPath %>/Notice?action=list" href="javascript:void(0)">通知列表</a></li>
                    <%--<li><a _href="system-category.html" href="javascript:void(0)">栏目管理</a></li>
                    <li><a _href="system-data.html" href="javascript:void(0)">数据字典</a></li>
                    <li><a _href="system-shielding.html" href="javascript:void(0)">屏蔽词</a></li>--%>
                </ul>
            </dd>
        </dl>
        <dl id="menu-system">
            <dt><i class="Hui-iconfont">&#xe62e;</i> 系统管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a _href="<%=ctxPath %>/Setting?action=list" href="javascript:void(0)">系统设置</a></li>
                    <li><a _href="<%=ctxPath %>/Log?action=list" href="javascript:void(0)">系统日志</a></li>
                    <%--<li><a _href="system-category.html" href="javascript:void(0)">栏目管理</a></li>
                    <li><a _href="system-data.html" href="javascript:void(0)">数据字典</a></li>
                    <li><a _href="system-shielding.html" href="javascript:void(0)">屏蔽词</a></li>--%>
                </ul>
            </dd>
        </dl>
    </div>
</aside>
<div class="dislpayArrow"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
<section class="Hui-article-box">
    <div id="Hui-tabNav" class="Hui-tabNav">
        <div class="Hui-tabNav-wp">
            <ul id="min_title_list" class="acrossTab cl">
                <li class="active"><span title="我的桌面" data-href="welcome.html">我的桌面</span><em></em></li>
            </ul>
        </div>
        <div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a></div>
    </div>
    <div id="iframe_box" class="Hui-article">
        <div class="show_iframe">
            <div style="display:none" class="loading"></div>
            <iframe scrolling="yes" frameborder="0" src="<%=ctxPath %>/Index?action=welcome"></iframe>
        </div>
    </div>
</section>

<script type="text/javascript">
    /*资讯-添加*/

    function article_add(title,url){
        var index = layer.open({
            type: 2,
            title: title,
            content: url
        });
        layer.full(index);
    }
    /*图片-添加*/
    function picture_add(title,url){
        var index = layer.open({
            type: 2,
            title: title,
            content: url
        });
        layer.full(index);
    }
    /*产品-添加*/
    function product_add(title,url){
        var index = layer.open({
            type: 2,
            title: title,
            content: url
        });
        layer.full(index);
    }
    /*用户-添加*/
    function member_add(title,url,w,h){
        layer_show(title,url,w,h);
    }
</script>
<script type="text/javascript">
    var _hmt = _hmt || [];
    (function() {
        var hm = document.createElement("script");
        hm.src = "//hm.baidu.com/hm.js?080836300300be57b7f34f4b3e97d911";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s)})();
    var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
    document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3F080836300300be57b7f34f4b3e97d911' type='text/javascript'%3E%3C/script%3E"));
</script>
</body>
</html>
