<%--
  Created by IntelliJ IDEA.
  User: liuyadong
  Date: 2020/12/14
  description:
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>


<title>
    <rapid:block name="title">
        - 搜索想法
    </rapid:block>
</title>
<rapid:override name="header-style">
    <style>
        /*覆盖 layui*/
        .layui-input {
            display: inline-block;
            width: 33.333%!important;
        }

        .layui-input-block {
            margin:0px 10px;
        }
    </style>
</rapid:override>
<rapid:override name="content">
    <blockquote class="layui-elem-quote">
         <span class="layui-breadcrumb" lay-separator="/">
              <a href="/admin">首页</a>
              <a href="/admin/thought">想法列表</a>
              <a><cite>
                  搜索 ${thoughtSearchVoList[0].query} 找到 ${thoughtSearchVoList[0].page.totalCount} 条数据
                </cite>
              </a>
        </span>
    </blockquote>

    <form id="thoughtForm" method="post">
        <div class="layui-form-item">
            <div class="layui-input-block">
                <input type="text" name="query" placeholder="请输入关键词" autocomplete="off" class="layui-input" >
                <button class="layui-btn" lay-filter="formDemo" onclick="queryThought()">搜索</button>
                <button class="layui-btn" lay-filter="formDemo" style="float: right;" onclick="confirmDeleteThoughtBatch()">批量删除</button>
            </div>
        </div>
    <input type="hidden" name="currentUrl" id="currentUrl" value="">
    <c:choose>
        <%--查询结果不为0--%>
        <c:when test="${thoughtSearchVoList[0].page.totalCount!=0}">
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
                    <th><input type="checkbox" id="allSelect"  onclick="javascript:DoCheck()"></th>
                    <th>id</th>
                    <th>标题</th>
                    <th>所属分类</th>
                    <th>所带标签</th>
                    <th>order</th>
                    <th>发布时间</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${thoughtSearchVoList}" var="a">
                    <tr>
                        <td><input type="checkbox" name="ids" value="${a.thoughtCustom.thoughtId}"></td>
                        <td>${a.thoughtCustom.thoughtId}</td>
                        <td><a href="/thought/${a.thoughtCustom.thoughtId}" target="_blank">
                                ${fn:substring(a.thoughtCustom.thoughtTitle, 0,20 )}

                        </a></td>
                        <td>
                            <c:forEach items="${a.categoryCustomList}" var="c">
                                <a href="/category/${c.categoryId}" target="_blank">${c.categoryName}</a>
                                &nbsp;
                            </c:forEach>
                        </td>

                        <td>
                            <c:forEach items="${a.tagCustomList}" var="t">
                                <a href="/tag/${t.tagId}" target="_blank">${t.tagName}</a>
                                &nbsp;
                            </c:forEach>
                        </td>
                        <td>
                            ${a.thoughtCustom.thoughtOrder}
                        </td>
                        <td>
                            <fmt:formatDate value="${a.thoughtCustom.thoughtPostTime}" pattern="MM月dd日 HH:mm"/>
                        </td>
                        <td>
                            <a href="/admin/thought/edit/${a.thoughtCustom.thoughtId}"
                               class="layui-btn layui-btn-mini">编辑</a>
                            <a href="javascript:void(0)"
                               onclick="confirmDeleteThought(${a.thoughtCustom.thoughtId})"
                               class="layui-btn layui-btn-danger layui-btn-mini">删除</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            </form>
            <%--分页 start--%>
            <nav class="navigation pagination" role="navigation">
                <div class="nav-links">
                    <c:choose>
                        <c:when test="${thoughtSearchVoList[0].page.totalPageCount <= 3 }">
                            <c:set var="begin" value="1"/>
                            <c:set var="end" value="${thoughtSearchVoList[0].page.totalPageCount }"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="begin" value="${thoughtSearchVoList[0].page.pageNow-1 }"/>
                            <c:set var="end" value="${thoughtSearchVoList[0].page.pageNow + 2}"/>
                            <c:if test="${begin < 2 }">
                                <c:set var="begin" value="1"/>
                                <c:set var="end" value="3"/>
                            </c:if>
                            <c:if test="${end > thoughtSearchVoList[0].page.totalPageCount }">
                                <c:set var="begin" value="${thoughtSearchVoList[0].page.totalPageCount-2 }"/>
                                <c:set var="end" value="${thoughtSearchVoList[0].page.totalPageCount }"/>
                            </c:if>
                        </c:otherwise>
                    </c:choose>
                        <%--上一页 --%>
                    <c:choose>
                        <c:when test="${thoughtSearchVoList[0].page.pageNow eq 1 }">
                            <%--当前页为第一页，隐藏上一页按钮--%>
                        </c:when>
                        <c:otherwise>
                            <a class="page-numbers" href="/admin/thought/p/${thoughtSearchVoList[0].page.pageNow-1}/search?query=${thoughtSearchVoList[0].query}" >
                                <i class="layui-icon">&#xe603;</i>
                            </a>
                        </c:otherwise>
                    </c:choose>
                        <%--显示第一页的页码--%>
                    <c:if test="${begin >= 2 }">
                        <a class="page-numbers" href="/admin/thought/p/1/search?query=${thoughtSearchVoList[0].query}">1</a>
                    </c:if>
                        <%--显示点点点--%>
                    <c:if test="${begin  > 2 }">
                        <span class="page-numbers dots">…</span>
                    </c:if>
                        <%--打印 页码--%>
                    <c:forEach begin="${begin }" end="${end }" var="i">
                        <c:choose>
                            <c:when test="${i eq thoughtSearchVoList[0].page.pageNow }">
                                <a class="page-numbers current" >${i}</a>
                            </c:when>
                            <c:otherwise>
                                <a  class="page-numbers" href="/admin/thought/p/${i}/search?query=${thoughtSearchVoList[0].query}">${i }</a>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                        <%-- 显示点点点 --%>
                    <c:if test="${end < thoughtSearchVoList[0].page.totalPageCount-1 }">
                        <span class="page-numbers dots">…</span>
                    </c:if>
                        <%-- 显示最后一页的数字 --%>
                    <c:if test="${end < thoughtSearchVoList[0].page.totalPageCount }">
                        <a href="/admin/thought/p/${thoughtSearchVoList[0].page.totalPageCount}/search?query=${thoughtSearchVoList[0].query}">
                                ${thoughtSearchVoList[0].page.totalPageCount}
                        </a>
                    </c:if>
                        <%--下一页 --%>
                    <c:choose>
                        <c:when test="${thoughtSearchVoList[0].page.pageNow eq thoughtSearchVoList[0].page.totalPageCount }">
                            <%--到了尾页隐藏，下一页按钮--%>
                        </c:when>
                        <c:otherwise>
                            <a class="page-numbers" href="/admin/thought/p/${thoughtSearchVoList[0].page.pageNow+1}/search?query=${thoughtSearchVoList[0].query}">
                                <i class="layui-icon">&#xe602;</i>
                            </a>
                        </c:otherwise>
                    </c:choose>

                </div>
            </nav>
            <%--分页 end--%>
        </c:when>
        <%--查询结果为0--%>
        <c:otherwise>
           <center><br>很遗憾，没有查询到带有 <font style="color: red;"> ${thoughtSearchVoList[0].query} </font> 的内容，换一个关键词再试试吧。</center>
        </c:otherwise>
    </c:choose>


</rapid:override>

<rapid:override name="footer-script">
    <script>
        var currentUrl = window.location.href;
        function queryThought() {
            //提交form
            $("#thoughtForm").attr("action","/admin/thought/search");
            $("#thoughtForm").submit();
        }

        function confirmDeleteThoughtBatch() {
            var msg = "您真的确定要删除吗？";
            if (confirm(msg)==true){
                //提交form
                $("#currentUrl").attr("value",currentUrl);
                $("#thoughtForm").attr("action","/admin/thought/deleteBatch");
                $("#thoughtForm").submit();
            }else{
                return false;
            }
        }

        function confirmDeleteThought(id) {
            var msg = "您真的确定要删除吗？";
            if (confirm(msg)==true){
                //提交form
                $("#currentUrl").attr("value",currentUrl);
                $("#thoughtForm").attr("action","/admin/thought/delete/"+id);
                $("#thoughtForm").submit();
            }else{
                return false;
            }
        }
    </script>
</rapid:override>

<%@ include file="../Public/framework.jsp"%>
