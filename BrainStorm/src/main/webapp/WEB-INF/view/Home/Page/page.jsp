<%--
  Created by IntelliJ IDEA.
  User: liuyadong
  Date: 2020/12/12
  description:
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>



<rapid:override name="breadcrumb">
    <%--面包屑导航 start--%>
    <nav class="breadcrumb">
        <a class="crumbs" href="/">
            <i class="fa fa-home"></i>首页
        </a>
        <i class="fa fa-angle-right">
            ${pageCustom.pageTitle}
        </i>

        <i class="fa fa-angle-right"></i>
        正文
    </nav>
    <%--面包屑导航 end--%>
</rapid:override>


<rapid:override name="left">
    <%--头脑风暴主体-左侧想法正文 start--%>
    <div id="primary" class="content-area">
        <main id="main" class="site-main" role="main">
            <article class="post" >
                <header class="entry-header">
                    <h1 class="entry-title">
                            ${pageCustom.pageTitle}
                    </h1>
                </header><!-- .entry-header -->
                <div class="entry-content" style="min-height:400px;">
                    <div class="single-content">
                            ${pageCustom.pageContent}
                    </div>

                    <br><br>

                    <footer class="single-footer">
                        <ul class="single-meta">
                            <li class="r-hide">
                                <a href="javascript:pr()" title="侧边栏">
                                    <i class="fa fa-caret-left"></i>
                                    <i class="fa fa-caret-right"></i>
                                </a>
                            </li>
                        </ul>
                        <ul id="fontsize">
                            <li>A+</li>
                        </ul>
                        <div class="single-cat-tag">
                            <div class="single-cat">日期：<fmt:formatDate value="${pageCustom.pageCreateTime}" pattern="yyyy年MM月dd日"/>
                            </div>
                        </div>
                    </footer><!-- .entry-footer -->


                    <div class="clear"></div>
                </div><!-- .entry-content -->
            </thought><!-- #post -->



        </main>
        <!-- .site-main -->
    </div>
    <%--头脑风暴主体-左侧想法正文end--%>
</rapid:override>



<%@ include file="../Public/framework.jsp" %>