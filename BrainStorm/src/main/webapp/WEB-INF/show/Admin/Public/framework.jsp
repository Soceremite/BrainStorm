<%--
  Created by IntelliJ IDEA.
  User: liuyadong
  Date: 2020/12/18
  description:
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <link rel="shortcut icon" href="/img/logo.png">
    <title>
        ${options.optionSiteTitle}创作者中心
            <rapid:block name="title"></rapid:block>
    </title>
    <link rel="stylesheet" href="/plugin/layui/css/layui.css">
    <link rel="stylesheet" href="/css/back.css">
    <link rel="stylesheet" href="/plugin/font-awesome/css/font-awesome.min.css">
    <rapid:block name="header-style"></rapid:block>
    <rapid:block name="header-script"></rapid:block>
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo"><a href="/admin" style="color:#009688;">
        ${options.optionSiteTitle}创作者中心
        </a>
        </div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="/" target="_blank">首页</a></li>
            <li class="layui-nav-item">
                <a href="javascript:;">新建</a>
                <dl class="layui-nav-child">
                    <dd><a href="/admin/thought/insert">想法</a></dd>
                    <dd><a href="/admin/category/insert">分类</a></dd>
                    <dd><a href="/admin/tag/insert">标签</a></dd>
                    <dd><a href="/admin/page/insert">页面</a></dd>
                </dl>
            </li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="${loginUser.userAvatar}" class="layui-nav-img">
                    ${loginUser.userName}
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="/admin/user/profile/${loginUser.userId}">基本资料</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item">
                <a href="/admin/logout">退出</a>
            </li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">想法</a>
                    <dl class="layui-nav-child">
                        <dd><a href="/admin/thought">全部想法</a></dd>
                        <dd><a href="/admin/thought/insert">写想法</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="/admin/comment">
                        评论
                    </a>
                </li>
                <li class="layui-nav-item">
                    <a href="/admin/category">
                        分类
                    </a>
                </li>
                <li class="layui-nav-item">
                    <a href="/admin/tag">
                        标签
                    </a>
                </li>
                <li class="layui-nav-item">
                    <a class="" href="javascript:;">
                        用户
                    </a>
                    <dl class="layui-nav-child">
                        <dd><a href="/admin/user">全部用户</a></dd>
                        <dd><a href="/admin/user/insert">添加用户</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">页面</a>
                    <dl class="layui-nav-child">
                        <dd><a href="/admin/page">全部页面</a></dd>
                        <dd><a href="/admin/page/insert">添加页面</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">设置</a>
                    <dl class="layui-nav-child">
                        <dd><a href="/admin/menu">菜单</a></dd>
                        <dd><a href="/admin/options">主要选项</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <rapid:block name="content">

            </rapid:block>
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © ${options.optionSiteTitle} - 创作者中心
    </div>
</div>

<script src="/js/jquery.min.js"></script>
<script src="/plugin/layui/layui.all.js"></script>
<script src="/js/back.js"></script>
<rapid:block name="footer-script">

</rapid:block>
<script>
    //给文本编辑器的iframe引入代码高亮的css
    $("iframe").contents().find("head").append("<link rel=\"stylesheet\" href=\"/css/highlight.css\">\n");

</script>

</body>
</html>