<%--
  Created by IntelliJ IDEA.
  User: liuyadong
  Date: 2020/12/13
  description:
  To change this template use File | Settings | File Templates.
--%>
<%--保留此处 start--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>
<%--保留此处 end--%>
<rapid:override name="title">
    - 修改想法
</rapid:override>
<rapid:override name="header-style">

</rapid:override>

<rapid:override name="content">
    <blockquote class="layui-elem-quote">
        <span class="layui-breadcrumb" lay-separator="/">
              <a href="/admin">首页</a>
              <a href="/admin/thought">想法列表</a>
              <a><cite>修改想法</cite></a>
        </span>
    </blockquote>



    <form class="layui-form"  method="post" id="myForm" action="/admin/thought/editSubmit">
        <input type="hidden" name="thoughtId" value="${thoughtCustom.thoughtId}">
        <div class="layui-form-item">
            <label class="layui-form-label">标题  <span style="color: #FF5722; ">*</span></label>
            <div class="layui-input-block">
                <input type="text" name="thoughtTitle" lay-verify="title" id="title" value="${thoughtCustom.thoughtTitle}" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">内容  <span style="color: #FF5722; ">*</span></label>
            <div class="layui-input-block">
                <textarea class="layui-textarea layui-hide" name="thoughtContent" id="content">${thoughtCustom.thoughtContent}</textarea>
            </div>
        </div>


        <div class="layui-form-item">
            <label class="layui-form-label">分类  <span style="color: #FF5722; ">*</span></label>
            <div class="layui-input-inline">
                <select name="thoughtParentCategoryId" id="thoughtParentCategoryId" lay-filter="thoughtParentCategoryId">
                    <option value="">一级分类</option>
                    <c:forEach items="${categoryCustomList}" var="c">
                        <c:if test="${c.categoryPid==0}">
                            <option value="${c.categoryId}"
                                <c:if test="${thoughtCustom.thoughtParentCategoryId==c.categoryId}">
                                    selected
                                </c:if>
                            >${c.categoryName}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
            <div class="layui-input-inline">
                <select name="thoughtChildCategoryId" id="thoughtChildCategoryId"  lay-filter="thoughtChildCategoryId" >
                    <c:forEach items="${categoryCustomList}" var="c">
                        <c:if test="${c.categoryPid==thoughtCustom.thoughtParentCategoryId}">
                            <option value="${c.categoryId}" <c:if test="${thoughtCustom.thoughtChildCategoryId==c.categoryId}">selected</c:if>>${c.categoryName}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="layui-form-item" pane="">
            <label class="layui-form-label">标签</label>
            <c:set var="tagIds" value="${fn:split(thoughtCustom.thoughtTagIds,',')}"/>
            <div class="layui-input-block">
                <c:forEach items="${tagCustomList}" var="t">
                   <input type="checkbox" name="thoughtTagIds" lay-skin="primary" title="${t.tagName}" value="${t.tagId}"
                    <c:forEach items="${tagIds}" var="id">
                        <c:if test="${t.tagId==id}">checked</c:if>
                    </c:forEach>>
                </c:forEach>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">order</label>
            <div class="layui-input-inline">
                <input type="number" name="thoughtOrder" value="${thoughtCustom.thoughtOrder}"   autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux">输入1-10的数字,order越大排序越前</div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">状态</label>
            <div class="layui-input-block">
                <input type="radio" name="thoughtStatus" value="1" title="发布" <c:if test="${thoughtCustom.thoughtStatus==1}">checked</c:if>>
                <input type="radio" name="thoughtStatus" value="0" title="草稿" <c:if test="${thoughtCustom.thoughtStatus==0}">checked</c:if>>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="demo1">保存</button>
                <button type="reset" class="layui-btn layui-btn-primary" >重置</button>
            </div>
        </div>
    </form>

    <blockquote class="layui-elem-quote layui-quote-nm">
        温馨提示：
        1、插入代码前，可以点击 <a href="http://liuyanzhao.com/code-highlight.html" target="_blank">代码高亮</a>,将代码转成HTML格式
    </blockquote>

</rapid:override>


<rapid:override name="footer-script">

    <script>


        layui.use(['form', 'layedit', 'laydate'], function() {
            var form = layui.form
                , layer = layui.layer
                , layedit = layui.layedit
                , laydate = layui.laydate;


            //上传图片,必须放在 创建一个编辑器前面
            layedit.set({
                uploadImage: {
                    url: '/UploadFile' //接口url
                    ,type: 'post' //默认post
                }
            });



            //创建一个编辑器
            var editIndex = layedit.build('content',{
                    height:350,
                }
            );

//            layui.code({
//                elem: 'pre', //默认值为.layui-code
//            });

            //自定义验证规则
            form.verify({
                title: function (value) {
                    if (value.length < 5) {
                        return '标题至少得5个字符啊';
                    }
                }
                ,content: function (value) {
                    layedit.sync(editIndex);
                }
            });
            layedit.build('content', {
                tool: [
                    'strong' //加粗
                    ,'italic' //斜体
                    ,'underline' //下划线
                    ,'del' //删除线

                    ,'|' //分割线

                    ,'left' //左对齐
                    ,'center' //居中对齐
                    ,'right' //右对齐
                    ,'link' //超链接
                    ,'unlink' //清除链接
                    ,'face' //表情
                    ,'image' //插入图片
                    ,'code'
                ]
            });



            //二级联动
            form.on("select(thoughtParentCategoryId)",function () {
                var optionstring = "";
                var thoughtParentCategoryId = $("#thoughtParentCategoryId").val();
                <c:forEach items="${categoryCustomList}" var="c">
                if(thoughtParentCategoryId==${c.categoryPid}) {
                    optionstring += "<option name='childCategory' value='${c.categoryId}'<c:if test='${thoughtCustom.thoughtChildCategoryId==c.categoryId}'>selected</c:if>>${c.categoryName}</option>";
                }
                </c:forEach>
                $("#thoughtChildCategoryId").html("  <option value=''selected>二级分类</option>"+optionstring);
                form.render('select'); //这个很重要
            })

        });
        //end




    </script>

</rapid:override>


<%--此句必须放在最后--%>
<%@ include file="../Public/framework.jsp"%>

