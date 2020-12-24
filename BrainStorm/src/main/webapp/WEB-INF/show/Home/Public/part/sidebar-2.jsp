<%--
  Created by IntelliJ IDEA.
  User: liuyadong
  Date: 2020/12/20
  description:
  To change this template use File | Settings | File Templates.
--%>
<%--
    一般用于首页侧边栏：
    包括 关于本站，网站概况，所有标签等小工具

--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%--头脑风暴主体-右侧侧边栏 start--%>
<div id="sidebar" class="widget-area all-sidebar"
     style="position: relative; overflow: visible; box-sizing: border-box; min-height: 1px;">

    <%--关于本站 start--%>
    <aside class="widget about">
        <h3 class="widget-title">
            <i class="fa fa-bars"></i>名言警句
        </h3>
        <div id="feed_widget">
            <div class="feed-about">
                <div class="about-main">
                    <div class="about-img">
                        <img src="${options.optionAboutsiteAvatar}"
                        alt="QR Code">
                    </div>
                    <div class="about-name">${options.optionAboutsiteTitle}</div>
                    <div class="about-the">
                        ${options.optionAboutsiteContent}
                    </div>
                </div>
                <div class="clear"></div>
                <div class="about-inf">
                    <span class="about-pn">想法 ${siteBasicStatistics[0]} </span>
                    <span class="about-cn">留言 ${siteBasicStatistics[1]} </span>
                </div>
            </div>
        </div>
        <div class="clear"></div>
    </aside>
    <%--关于本站 start--%>


    <%--所有标签 start--%>
    <aside class="widget">
        <h3 class="widget-title">
            <i class="fa fa-bars"></i>所有标签
        </h3>
        <div class="tagcloud">
            <c:forEach items="${tagList}" var="tag">
                <a href="/tag/${tag.tagId}"
                   class="tag-link-129 tag-link-position-1" title="${tag.thoughtCount}个话题"
                   style="font-size: 14px;">
                        ${tag.tagName}
                </a>
            </c:forEach>
            <div class="clear"></div>
        </div>
        <div class="clear"></div>
    </aside>
    <%--所有标签 end--%>

    <%--网站概况 start--%>
    <aside id="php_text-22" class="widget php_text">
        <h3 class="widget-title">
            <i class="fa fa-bars"></i>网站概况
        </h3>
        <div class="textwidget widget-text">
            <ul class="site-profile">
                <li><i class="fa fa-file-o"></i> 想法总数：${siteBasicStatistics[0]} 篇</li>
                <li><i class="fa fa-commenting-o"></i> 留言数量：${siteBasicStatistics[1]} 条</li>
                <li><i class="fa fa-folder-o"></i> 分类数量：${siteBasicStatistics[2]} 个</li>
                <li><i class="fa fa-tags"></i> 标签总数：${siteBasicStatistics[3]} 个</li>
                <li><i class="fa fa-eye"></i> 浏览总量：${siteBasicStatistics[4]} 次</li>
                <li><i class="fa fa-pencil-square-o"></i> 最后更新：
                    <span style="color:#2F889A">
                        <fmt:formatDate value="${lastUpdateThought.thoughtUpdateTime}" pattern="yyyy年MM月dd日"/>

                    </span>
                </li>
            </ul>
        </div>
            <div class="clear"></div>
        </aside>
        <%--网站概况 end--%>

</div>



<%--头脑风暴主体-右侧侧边栏 end--%>
