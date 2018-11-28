<%--
  Created by IntelliJ IDEA.
  User: Lulu
  Date: 2018/5/20
  Time: 22:38
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
        <input type="hidden" name="action" value="editSubmit"/>
        <input type="hidden" name="id" value="${article.articleId}"/>

        <div class="row cl">
            <label class="form-label col-2"><span class="c-red">*</span>文章标题：</label>
            <div class="formControls col-10">
                <input type="text" class="input-text" value="${article.articleTitle}" placeholder="" id="" name="articleTitle">
            </div>
        </div>

        <div class="row cl">
            <label class="form-label col-2">关键词：</label>
            <div class="formControls col-10">
                <input type="text" class="input-text" value="${article.articleKeyWords}" placeholder="" id="" name="articleKeyWord">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-2">文章描述：</label>
            <div class="formControls col-10">
                <textarea name="articleDesc" cols="" rows="" class="textarea"  placeholder="说点什么...最少输入10个字符" datatype="*10-100" dragonfly="true" nullmsg="备注不能为空！" onKeyUp="textarealength(this,200)">${article.articleDesc}</textarea>
                <p class="textarea-numberbar"><em class="textarea-length">0</em>/200</p>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-2">文章作者：</label>
            <div class="formControls col-2">
                <input type="text" class="input-text" value="${article.articleAuthor}" placeholder="" id="" name="articleAuthor">
            </div>
        </div>

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">缩略图：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <div class="uploader-thum-container">
                    <div class="layui-upload">
                        <button type="button" class="layui-btn" id="test1">上传图片</button>
                        <div class="layui-upload-list">
                            <img class="layui-upload-img" src="${article.articleImg}" style="width: 200px;height: 200px" id="demo1">
                            <p id="demoText"></p>
                        </div>
                    </div>
                </div>
            </div>
            <input value="${article.articleImg}" type="hidden" name="articleImg" id="goods_thumb"/>
        </div>



        <div class="row cl">
            <label class="form-label col-2">文章内容：</label>
            <div class="formControls col-10">
                <script id="editor" name="articleContent" type="text/plain" style="width:100%;height:400px;">${article.articleContent}</script></div>
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
                    layui.use('upload', function () {
                        var $ = layui.jquery
                            , upload = layui.upload;

                        //普通图片上传
                        var uploadInst = upload.render({
                            elem: '#test1'
                            , url: '<%=ctxPath%>/Article?action=uploadImage1'
                            , accept: 'image' //普通文件
                            , exts: 'jpg|jpeg|gif|png' //只允许上传压缩文件
                            , size: 3000 //限制文件大小，单位 KB
                            , before: function (obj) {
                                //预读本地文件示例，不支持ie8
                                obj.preview(function (index, file, result) {
                                    $('#demo1').attr('src', result); //图片链接（base64）
                                });
                            }
                            , done: function (res) {
                                //如果上传失败
                                console.log(res.code);
                                if (res.status != 0) {
                                    layer.msg(res.msg);
                                    var demoText = $('#demoText');
                                    demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
                                    demoText.find('.demo-reload').on('click', function () {
                                        uploadInst.upload();
                                    });
                                }
                                else {
                                    layer.msg(res.msg);
                                    var demoText = $('#demoText');
                                    demoText.html('');
                                    var goods_thumb = $('#goods_thumb');
                                    var timg = $('#timg');
                                    goods_thumb.val(res.data);

                                    $('#timg').attr('src', res.data);
                                }
                                //上传成功
                            }
                            , error: function () {
                                //演示失败状态，并实现重传
                                var demoText = $('#demoText');
                                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
                                demoText.find('.demo-reload').on('click', function () {
                                    uploadInst.upload();
                                });
                            }
                        });

                        //多图片上传
                        upload.render({
                            elem: '#test2'
                            , url: '/upload/'
                            , multiple: true
                            , before: function (obj) {
                                //预读本地文件示例，不支持ie8
                                obj.preview(function (index, file, result) {
                                    $('#demo2').append('<img src="' + result + '" alt="' + file.name + '" class="layui-upload-img">')
                                });
                            }
                            , done: function (res) {
                                //上传完毕
                            }
                        });

                        //指定允许上传的文件类型
                        upload.render({
                            elem: '#test3'
                            , url: '/upload/'
                            , accept: 'file' //普通文件
                            , done: function (res) {
                                console.log(res)
                            }
                        });
                        upload.render({ //允许上传的文件后缀
                            elem: '#test4'
                            , url: '/upload/'
                            , accept: 'file' //普通文件
                            , exts: 'zip|rar|7z' //只允许上传压缩文件
                            , done: function (res) {
                                console.log(res)
                            }
                        });
                        upload.render({
                            elem: '#test5'
                            , url: '/upload/'
                            , accept: 'video' //视频
                            , done: function (res) {
                                console.log(res)
                            }
                        });
                        upload.render({
                            elem: '#test6'
                            , url: '/upload/'
                            , accept: 'audio' //音频
                            , done: function (res) {
                                console.log(res)
                            }
                        });

                        //设定文件大小限制
                        upload.render({
                            elem: '#test7'
                            , url: '/upload/'
                            , size: 60 //限制文件大小，单位 KB
                            , done: function (res) {
                                console.log(res)
                            }
                        });

                        //同时绑定多个元素，并将属性设定在元素上
                        upload.render({
                            elem: '.demoMore'
                            , before: function () {
                                layer.tips('接口地址：' + this.url, this.item, {tips: 1});
                            }
                            , done: function (res, index, upload) {
                                var item = this.item;
                                console.log(item); //获取当前触发上传的元素，layui 2.1.0 新增
                            }
                        })

                        //选完文件后不自动上传
                        upload.render({
                            elem: '#test8'
                            , url: '/upload/'
                            , auto: false
                            //,multiple: true
                            , bindAction: '#test9'
                            , done: function (res) {
                                console.log(res)
                            }
                        });

                        //拖拽上传
                        upload.render({
                            elem: '#test10'
                            , url: '/upload/'
                            , done: function (res) {
                                console.log(res)
                            }
                        });

                        //多文件列表示例
                        var demoListView = $('#demoList')
                            , uploadListIns = upload.render({
                            elem: '#testList'
                            , url: '/upload/'
                            , accept: 'file'
                            , multiple: true
                            , auto: false
                            , bindAction: '#testListAction'
                            , choose: function (obj) {
                                var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
                                //读取本地文件
                                obj.preview(function (index, file, result) {
                                    var tr = $(['<tr id="upload-' + index + '">'
                                        , '<td>' + file.name + '</td>'
                                        , '<td>' + (file.size / 1014).toFixed(1) + 'kb</td>'
                                        , '<td>等待上传</td>'
                                        , '<td>'
                                        , '<button class="layui-btn layui-btn-mini demo-reload layui-hide">重传</button>'
                                        , '<button class="layui-btn layui-btn-mini layui-btn-danger demo-delete">删除</button>'
                                        , '</td>'
                                        , '</tr>'].join(''));

                                    //单个重传
                                    tr.find('.demo-reload').on('click', function () {
                                        obj.upload(index, file);
                                    });

                                    //删除
                                    tr.find('.demo-delete').on('click', function () {
                                        delete files[index]; //删除对应的文件
                                        tr.remove();
                                        uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
                                    });

                                    demoListView.append(tr);
                                });
                            }
                            , done: function (res, index, upload) {
                                if (res.code == 0) { //上传成功
                                    var tr = demoListView.find('tr#upload-' + index)
                                        , tds = tr.children();
                                    tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
                                    tds.eq(3).html(''); //清空操作
                                    return delete this.files[index]; //删除文件队列已经上传成功的文件
                                }
                                this.error(index, upload);
                            }
                            , error: function (index, upload) {
                                var tr = demoListView.find('tr#upload-' + index)
                                    , tds = tr.children();
                                tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
                                tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传
                            }
                        });

                        //绑定原始文件域
                        upload.render({
                            elem: '#test20'
                            , url: '/upload/'
                            , done: function (res) {
                                console.log(res)
                            }
                        });

                    });


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
                                url:"<%=ctxPath%>/Article",type:"post",
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
