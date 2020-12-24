<%--
  Created by IntelliJ IDEA.
  User: liuyadong
  Date: 2020/12/20
  description:
  To change this template use File | Settings | File Templates.
--%>
<%--
    一般用于正文侧边栏：
    包括 搜索，所有标签， 等小工具
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%--头脑风暴主体-右侧侧边栏 start--%>
<div id="sidebar" class="widget-area all-sidebar"
     style="position: relative; overflow: visible; box-sizing: border-box; min-height: 1px;">

        <%--搜索框--%>
        <aside class="widget widget_search">
            <div class="searchbar">
                <form method="get" id="searchform1" action="/search">
                        <span> <input type="text" value="" name="query" id="s1" placeholder="输入搜索内容" required="">
                            <button type="submit" id="searchsubmit1">搜索</button>
                        </span>
                </form>
            </div>
            <div class="clear"></div>
        </aside>
        <%--搜索框--%>


        <%--所有标签 start--%>
        <aside class="widget">
            <h3 class="widget-title">
                <i class="fa fa-bars"></i>所有标签
            </h3>
            <div class="tagcloud">
                <c:forEach items="${tagList}" var="t">
                    <a href="/tag/${t.tagId}"
                       class="tag-link-129 tag-link-position-1" title="${t.thoughtCount}个话题"
                       style="font-size: 14px;">
                            ${t.tagName}
                    </a>
                </c:forEach>
                <div class="clear"></div>
            </div>
            <div class="clear"></div>
        </aside>
        <%--所有标签 end--%>

</div>
<%--头脑风暴主体-右侧侧边栏 end--%>