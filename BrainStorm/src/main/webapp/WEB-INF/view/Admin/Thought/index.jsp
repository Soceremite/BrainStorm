<%--
  Created by IntelliJ IDEA.
  User: liuyadong
  Date: 2020/12/10
  description:
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>
<rapid:override name="title">
    - 想法列表
</rapid:override>
<rapid:override name="header-style">
    <style>
        /*覆盖 layui*/
        .layui-input {
            display: inline-block;
            width: 33.333% !important;
        }

        .layui-input-block {
            margin: 0px 10px;
        }


    </style>
</rapid:override>

<rapid:override name="content">
    <blockquote class="layui-elem-quote">
        <span class="layui-breadcrumb" lay-separator="/">
          <a href="/admin">首页</a>
          <a><cite>想法列表</cite></a>
        </span>
    </blockquote>

    <div class="layui-tab layui-tab-card">
        <ul class="layui-tab-title">
            <li class="layui-this">已发布(${publishedThoughtListVoList[0].page.totalCount})</li>
            <li>草稿(${draftThoughtList.size()})</li>
        </ul>
        <div class="layui-tab-content">
            <div class="layui-tab-item layui-show">
                <form id="thoughtForm" method="post">
                    <div class="layui-form-item">
                        <div class="layui-input-block">
                            <input type="text" name="query" placeholder="请输入关键词" autocomplete="off" class="layui-input">
                            <button class="layui-btn" lay-filter="formDemo" onclick="queryThought()">搜索</button>
                            <button class="layui-btn" lay-filter="formDemo" style="float: right;"
                                    onclick="confirmDeleteThoughtBatch()">批量删除
                            </button>
                        </div>
                    </div>
                    <input type="hidden" name="currentUrl" id="currentUrl" value="">
                    <table class="layui-table">
                        <colgroup>
                            <col width="50">
                            <col width="50">
                            <col width="300">
                            <col width="200">
                            <col width="200">
                            <col width="50">
                            <col width="150">
                            <col width="100">
                        </colgroup>
                        <thead>
                        <tr>
                            <th><input type="checkbox" id="allSelect" onclick="javascript:DoCheck()"></th>
                            <th>id</th>
                            <th>标题</th>
                            <th>所属分类</th>
                            <th>所带标签</th>
                            <td>order</td>
                            <th>发布时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${publishedThoughtListVoList}" var="a">
                            <c:if test="${a.thoughtCustom.thoughtStatus==1}">
                                <tr>
                                    <td><input type="checkbox" name="ids" value="${a.thoughtCustom.thoughtId}"></td>
                                    <td>${a.thoughtCustom.thoughtId}</td>
                                    <td>
                                        <a href="/thought/${a.thoughtCustom.thoughtId}"
                                           target="_blank">
                                               ${a.thoughtCustom.thoughtTitle}

                                        </a></td>
                                    <td>
                                        <c:forEach items="${a.categoryCustomList}" var="c">
                                            <a href="/category/${c.categoryId}"
                                               target="_blank">${c.categoryName}</a>
                                            &nbsp;
                                        </c:forEach>
                                    </td>

                                    <td>
                                        <c:forEach items="${a.tagCustomList}" var="t">
                                            <a href="/tag/${t.tagId}"
                                               target="_blank">${t.tagName}</a>
                                            &nbsp;
                                        </c:forEach>
                                    </td>
                                    <td>${a.thoughtCustom.thoughtOrder}</td>
                                    <td>
                                        <fmt:formatDate value="${a.thoughtCustom.thoughtPostTime}"
                                                        pattern="MM月dd日 HH:mm"/>
                                    </td>
                                    <td>
                                        <a href="/admin/thought/edit/${a.thoughtCustom.thoughtId}"
                                           class="layui-btn layui-btn-mini">编辑</a>
                                        <a href="javascript:void(0)"
                                           onclick="deleteThought(${a.thoughtCustom.thoughtId})"
                                           class="layui-btn layui-btn-danger layui-btn-mini">删除</a>
                                    </td>
                                </tr>
                            </c:if>
                        </c:forEach>
                        </tbody>
                    </table>
                </form>

                <c:if test="${publishedThoughtListVoList.size()!=0}">
                    <%--分页 start--%>
                <nav class="navigation pagination" role="navigation">
                    <div class="nav-links">
                        <c:choose>
                            <c:when test="${publishedThoughtListVoList[0].page.totalPageCount <= 3 }">
                                <c:set var="begin" value="1"/>
                                <c:set var="end" value="${publishedThoughtListVoList[0].page.totalPageCount }"/>
                            </c:when>
                            <c:otherwise>
                                <c:set var="begin" value="${publishedThoughtListVoList[0].page.pageNow-1 }"/>
                                <c:set var="end" value="${publishedThoughtListVoList[0].page.pageNow + 2}"/>
                                <c:if test="${begin < 2 }">
                                    <c:set var="begin" value="1"/>
                                    <c:set var="end" value="3"/>
                                </c:if>
                                <c:if test="${end > publishedThoughtListVoList[0].page.totalPageCount }">
                                    <c:set var="begin" value="${publishedThoughtListVoList[0].page.totalPageCount-2 }"/>
                                    <c:set var="end" value="${publishedThoughtListVoList[0].page.totalPageCount }"/>
                                </c:if>
                            </c:otherwise>
                        </c:choose>
                            <%--上一页 --%>
                        <c:choose>
                            <c:when test="${publishedThoughtListVoList[0].page.pageNow eq 1 }">
                                <%--当前页为第一页，隐藏上一页按钮--%>
                            </c:when>
                            <c:otherwise>
                                <a class="page-numbers"
                                   href="/admin/thought/p/${publishedThoughtListVoList[0].page.pageNow-1}">
                                    <i class="layui-icon">&#xe603;</i>
                                </a>
                            </c:otherwise>
                        </c:choose>
                            <%--显示第一页的页码--%>
                        <c:if test="${begin >= 2 }">
                            <a class="page-numbers" href="/admin/thought/p/1">1</a>
                        </c:if>
                            <%--显示点点点--%>
                        <c:if test="${begin  > 2 }">
                            <span class="page-numbers dots">…</span>
                        </c:if>
                            <%--打印 页码--%>
                        <c:forEach begin="${begin }" end="${end }" var="i">
                            <c:choose>
                                <c:when test="${i eq publishedThoughtListVoList[0].page.pageNow }">
                                    <a class="page-numbers current">${i}</a>
                                </c:when>
                                <c:otherwise>
                                    <a class="page-numbers"
                                       href="/admin/thought/p/${i}">${i}</a>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                            <%-- 显示点点点 --%>
                        <c:if test="${end < publishedThoughtListVoList[0].page.totalPageCount-1 }">
                            <span class="page-numbers dots">…</span>
                        </c:if>
                            <%-- 显示最后一页的数字 --%>
                        <c:if test="${end < publishedThoughtListVoList[0].page.totalPageCount }">
                            <a href="/admin/thought/p/${publishedThoughtListVoList[0].page.totalPageCount}">
                                    ${publishedThoughtListVoList[0].page.totalPageCount}
                            </a>
                        </c:if>
                            <%--下一页 --%>
                        <c:choose>
                            <c:when test="${publishedThoughtListVoList[0].page.pageNow eq publishedThoughtListVoList[0].page.totalPageCount }">
                                <%--到了尾页隐藏，下一页按钮--%>
                            </c:when>
                            <c:otherwise>
                                <a class="page-numbers"
                                   href="/admin/thought/p/${publishedThoughtListVoList[0].page.pageNow+1}">
                                    <i class="layui-icon">&#xe602;</i>
                                </a>
                            </c:otherwise>
                        </c:choose>

                    </div>
                </nav>
                    <%--分页 end--%>
                </c:if>
            </div>
            <div class="layui-tab-item">
                <table class="layui-table">
                    <colgroup>
                        <col width="50">
                        <col width="300">
                        <col width="200">
                        <col width="200">
                        <col width="200">
                        <col width="100">
                    </colgroup>
                    <thead>
                    <tr>
                        <th>id</th>
                        <th>标题</th>
                        <th>所属分类</th>
                        <th>所带标签</th>
                        <th>发布时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${draftThoughtList}" var="a">
                        <tr>
                            <td>${a.thoughtCustom.thoughtId}</td>
                            <td><a href="/thought/${a.thoughtCustom.thoughtId}"
                                   target="_blank">
                                    ${a.thoughtCustom.thoughtTitle}

                            </a></td>
                            <td>
                                <c:forEach items="${a.categoryCustomList}" var="c">
                                    <a href="/category/${c.categoryId}"
                                       target="_blank">${c.categoryName}</a>
                                    &nbsp;
                                </c:forEach>
                            </td>

                            <td>
                                <c:forEach items="${a.tagCustomList}" var="t">
                                    <a href="/tag/${t.tagId}"
                                       target="_blank">${t.tagName}</a>
                                    &nbsp;
                                </c:forEach>
                            </td>

                            <td>
                                <fmt:formatDate value="${a.thoughtCustom.thoughtPostTime}"
                                                pattern="yyyy-MM-dd HH:mm:ss"/>
                            </td>
                            <td>
                                <a href="/admin/thought/edit/${a.thoughtCustom.thoughtId}"
                                   class="layui-btn layui-btn-mini">编辑</a>
                                <a href="javascript:void(0)"
                                   onclick="deleteThought(${a.thoughtCustom.thoughtId})"
                                   class="layui-btn layui-btn-danger layui-btn-mini">删除</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>


</rapid:override>
<rapid:override name="footer-script">
    <script>


    </script>
</rapid:override>
<%@ include file="../Public/framework.jsp" %>
