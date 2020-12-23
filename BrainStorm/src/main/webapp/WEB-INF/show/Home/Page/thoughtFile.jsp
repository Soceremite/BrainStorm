<%--
  Created by IntelliJ IDEA.
  User: liuyadong
  Date: 2020/12/17
  description:
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>

    <rapid:override name="description">
        <meta name="description" content="想法归档"/>
    </rapid:override>

    <rapid:override name="keywords">
        <meta name="keywords" content="想法,归档"/>
    </rapid:override>

    <rapid:override name="title">
        <title>想法归档--${options.optionSiteTitle}</title>
    </rapid:override>

    <rapid:override name="breadcrumb">
        <%--面包屑导航 start--%>
        <nav class="breadcrumb">
            <a class="crumbs" href="/">
                <i class="fa fa-home"></i>首页
            </a>
            <i class="fa fa-angle-right"></i>
            想法归档
            <i class="fa fa-angle-right"></i>
            正文
        </nav>
        <%--面包屑导航 end--%>
    </rapid:override>

    <rapid:override name="left">
        <%--头脑风暴主体-左侧正文 start--%>
        <section id="primary" class="content-area">
            <main id="main" class="site-main" role="main">
                <ul class="search-page">
                    <c:forEach items="${thoughtList}" var="a">
                        <li class="search-inf">
                            <fmt:formatDate value="${a.thoughtCustom.thoughtPostTime}" pattern="yyyy年MM月dd日"/>
                        </li>
                        <li class="entry-title">
                            <a href="/thought/${a.thoughtCustom.thoughtId}">${a.thoughtCustom.thoughtTitle}</a>
                        </li>
                    </c:forEach>
                </ul>
            </main>
        </section>
    </rapid:override>

<%@ include file="../Public/framework.jsp" %>