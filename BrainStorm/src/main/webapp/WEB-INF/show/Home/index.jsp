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
<%@ taglib uri="/WEB-INF/myTag.tld" prefix="lyz" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>


    <rapid:override name="left">
        <div id="primary" class="content-area">

            <main id="main" class="site-main" role="main">
                <c:forEach items="${thoughtListVoList}" var="a">

                    <thought  class="post type-post">

                        <figure class="thumbnail">
                            <a href="/thought/${a.thoughtCustom.thoughtId}">
                                <img width="280" height="210"
                                     src="/img/thumbnail/random/img_${a.thoughtCustom.thoughtId%400}.jpg"
                                     class="attachment-content size-content wp-post-image"
                                     alt="${a.thoughtCustom.thoughtTitle}">
                            </a>
                            <span class="cat">
                                <a href="/category/${a.categoryCustomList[a.categoryCustomList.size()-1].categoryId}">
                                        ${a.categoryCustomList[a.categoryCustomList.size()-1].categoryName}
                                </a>
                            </span>
                        </figure>

                        <header class="entry-header">
                            <h2 class="entry-title">
                                <a href="/thought/${a.thoughtCustom.thoughtId}"
                                   rel="bookmark">
                                        ${a.thoughtCustom.thoughtTitle}
                                </a>
                            </h2>
                        </header>

                        <div class="entry-content">
                            <div class="archive-content">
                                <lyz:htmlFilter>${a.thoughtCustom.thoughtContent}</lyz:htmlFilter>......
                            </div>
                            <span class="title-l"></span>
                            <span class="new-icon">
                                    <c:choose>
                                        <c:when test="${a.thoughtCustom.thoughtStatus==2}">
                                            <i class="fa fa-bookmark-o"></i>
                                        </c:when>
                                        <c:otherwise>
                                            <jsp:useBean id="nowDate" class="java.util.Date"/>
                                            <c:set var="interval"
                                                   value="${nowDate.time - a.thoughtCustom.thoughtPostTime.time}"/><%--时间差毫秒数--%>
                                            <fmt:formatNumber value="${interval/1000/60/60/24}" pattern="#0"
                                                              var="days"/>
                                            <c:if test="${days <= 7}">新</c:if>
                                        </c:otherwise>
                                    </c:choose>


                                </span>
                            <span class="entry-meta">
                                    <span class="date">
                                        <fmt:formatDate value="${a.thoughtCustom.thoughtPostTime}" pattern="yyyy年MM月dd日"/>
                                    &nbsp;&nbsp;
                                    </span>
                                    <span class="views">
                                        <i class="fa fa-eye"></i>
                                            ${a.thoughtCustom.thoughtViewCount} views
                                    </span>
                                    <span class="comment">&nbsp;&nbsp;
                                        <a href="/thought/${a.thoughtCustom.thoughtId}#comments" rel="external nofollow">
                                          <i class="fa fa-comment-o"></i>
                                            <c:choose>
                                                <c:when test="${a.thoughtCustom.thoughtCommentCount==0}">
                                                    发表评论
                                                </c:when>
                                                <c:otherwise>
                                                    ${a.thoughtCustom.thoughtCommentCount}
                                                </c:otherwise>
                                            </c:choose>

                                        </a>
                                    </span>
                                </span>
                            <div class="clear"></div>
                        </div><!-- .entry-content -->

                        <span class="entry-more">
                                <a href="/thought/${a.thoughtCustom.thoughtId}"
                                   rel="bookmark">
                                    阅读全文
                                </a>
                            </span>
                    </thought>
                </c:forEach>
            </main>

            <c:if test="${thoughtListVoList[0].page.totalPageCount>1}">
            <nav class="navigation pagination" role="navigation">
                <div class="nav-links">
                    <c:choose>
                        <c:when test="${thoughtListVoList[0].page.totalPageCount <= 3 }">
                            <c:set var="begin" value="1"/>
                            <c:set var="end" value="${thoughtListVoList[0].page.totalPageCount }"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="begin" value="${thoughtListVoList[0].page.pageNow-1 }"/>
                            <c:set var="end" value="${thoughtListVoList[0].page.pageNow + 2}"/>
                            <c:if test="${begin < 2 }">
                                <c:set var="begin" value="1"/>
                                <c:set var="end" value="3"/>
                            </c:if>
                            <c:if test="${end > thoughtListVoList[0].page.totalPageCount }">
                                <c:set var="begin" value="${thoughtListVoList[0].page.totalPageCount-2 }"/>
                                <c:set var="end" value="${thoughtListVoList[0].page.totalPageCount }"/>
                            </c:if>
                        </c:otherwise>
                    </c:choose>
                        <%--上一页 --%>
                    <c:choose>
                        <c:when test="${thoughtListVoList[0].page.pageNow eq 1 }">
                            <%--当前页为第一页，隐藏上一页按钮--%>
                        </c:when>
                        <c:otherwise>
                            <a class="page-numbers" href="/p/${thoughtListVoList[0].page.pageNow-1}" >
                                <span class="fa fa-angle-left"></span>
                            </a>
                        </c:otherwise>
                    </c:choose>
                        <%--显示第一页的页码--%>
                    <c:if test="${begin >= 2 }">
                        <a class="page-numbers" href="/p/1">1</a>
                    </c:if>
                        <%--显示点点点--%>
                    <c:if test="${begin  > 2 }">
                        <span class="page-numbers dots">…</span>
                    </c:if>
                        <%--打印 页码--%>
                    <c:forEach begin="${begin }" end="${end }" var="i">
                        <c:choose>
                            <c:when test="${i eq thoughtListVoList[0].page.pageNow }">
                                <a class="page-numbers current" >${i}</a>
                            </c:when>
                            <c:otherwise>
                                <a  class="page-numbers" href="/p/${i}">${i }</a>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                        <%-- 显示点点点 --%>
                    <c:if test="${end < thoughtListVoList[0].page.totalPageCount-1 }">
                        <span class="page-numbers dots">…</span>
                    </c:if>
                        <%-- 显示最后一页的数字 --%>
                    <c:if test="${end < thoughtListVoList[0].page.totalPageCount }">
                        <a href="/p/${thoughtListVoList[0].page.totalPageCount}">
                                ${thoughtListVoList[0].page.totalPageCount}
                        </a>
                    </c:if>
                        <%--下一页 --%>
                    <c:choose>
                        <c:when test="${thoughtListVoList[0].page.pageNow eq thoughtListVoList[0].page.totalPageCount }">
                            <%--到了尾页隐藏，下一页按钮--%>
                        </c:when>
                        <c:otherwise>
                            <a class="page-numbers" href="/p/${thoughtListVoList[0].page.pageNow+1}">
                                <span class="fa fa-angle-right"></span>
                            </a>
                        </c:otherwise>
                    </c:choose>

                </div>
            </nav>
                <%--分页 end--%>
            </c:if>
        </div>
    </rapid:override>
    <%--左侧区域 end--%>

    <%--侧边栏 start--%>
    <rapid:override name="right">
        <%@include file="Public/part/sidebar-2.jsp" %>
    </rapid:override>
    <%--侧边栏 end--%>


<%@ include file="Public/framework.jsp" %>