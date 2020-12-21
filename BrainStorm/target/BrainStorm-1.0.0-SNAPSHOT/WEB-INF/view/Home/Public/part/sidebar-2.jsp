<%--
  Created by IntelliJ IDEA.
  User: liuyadong
  Date: 2020/12/20
  description:
  To change this template use File | Settings | File Templates.
--%>
<%--
    一般用于首页侧边栏：
    包括 关于本站，网站概况，热评想法，所有标签，随机想法 等小工具

--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%--头脑风暴主体-右侧侧边栏 start--%>
<div id="sidebar" class="widget-area all-sidebar"
     style="position: relative; overflow: visible; box-sizing: border-box; min-height: 1px;">

    <%--关于本站 start--%>
    <aside class="widget about">
        <h3 class="widget-title">
            <i class="fa fa-bars"></i>关于本站
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
                <ul>
                    <li class="weixin">
                        <a title="微信" id="weixin_btn" rel="external nofollow">
                            <i class="fa fa-weixin"> </i>
                            <div id="weixin_code" class="hide" >
                                <img src="${options.optionAboutsiteWechat}" alt="">
                            </div>
                        </a>
                    </li>
                    <li class="tqq">
                        <a target="blank" rel="external nofollow"
                           href="http://wpa.qq.com/msgrd?V=3&amp;uin=${options.optionAboutsiteQq}&amp;Site=QQ&amp;Menu=yes"
                           title="QQ在线">
                            <i class="fa fa-qq"></i>
                        </a>
                    </li>
                    <li class="tsina">
                        <a title=""
                           href="http://weibo.com/${options.optionAboutsiteWeibo}"
                           target="_blank" rel="external nofollow">
                            <i class="fa fa-weibo"></i>
                        </a>
                    </li>
                    <li class="feed">
                        <a title="" href="https://github.com/${options.optionAboutsiteGithub}" target="_blank"
                           rel="external nofollow">
                            <i class="fa fa-github"></i>
                        </a>
                    </li>
                </ul>
                <div class="about-inf">
                    <span class="about-pn">想法 ${siteBasicStatistics[0]} </span>
                    <span class="about-cn">留言 ${siteBasicStatistics[1]} </span>
                </div>
            </div>
        </div>
        <div class="clear"></div>
    </aside>
    <%--关于本站 start--%>

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
                <li><i class="fa fa-link"></i> 链接数量：${siteBasicStatistics[4]} 个</li>
                <li><i class="fa fa-eye"></i> 浏览总量：${siteBasicStatistics[5]} 次</li>
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

    <%--热评想法 start--%>
    <aside class="widget hot_comment">
        <h3 class="widget-title">
            <i class="fa fa-bars"></i>热评想法
        </h3>
        <div id="hot_comment_widget">
            <ul>
                <c:forEach items="${mostCommentThoughtList}" var="m">
                    <li>
                        <a href="/thought/${m.thoughtId}" rel="bookmark"
                           title=" (${m.thoughtCommentCount}条评论)">
                                ${m.thoughtTitle}
                        </a>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div class="clear"></div>
    </aside>
    <%--热评想法 end--%>

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


    <%--随机想法 start--%>
    <aside id="random_post-7" class="widget random_post wow fadeInUp" data-wow-delay="0.3s">
        <h3 class="widget-title">
            <i class="fa fa-bars"></i>随机想法
        </h3>
        <div id="random_post_widget">
            <ul>
                <c:forEach items="${randomThoughtList}" var="r">
                    <li>
                        <a href="/thought/${r.thoughtId}" rel="bookmark">
                                ${r.thoughtTitle}
                        </a>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div class="clear"></div>
    </aside>
    <%--近期想法 end--%>

    <%--最新评论 start--%>
    <aside id="recent_comments-2" class="widget recent_comments wow fadeInUp" data-wow-delay="0.3s"><h3
            class="widget-title"><i class="fa fa-bars"></i>近期评论</h3>
        <div id="message" class="message-widget">
            <ul>
                <c:forEach items="${recentCommentList}" var="r">
                <li style="border: none;">
                    <a href="/thought/${r.thoughtCustom.thoughtId}/#anchor-comment-${r.commentCustom.commentId}" title="${r.thoughtCustom.thoughtTitle}" rel="external nofollow">
                        <img alt=""src="${r.commentCustom.commentAuthorAvatar}" class="avatar avatar-64 photo" height="64" width="64">
                        <span class="comment_author">
                            <strong>${r.commentCustom.commentAuthorName}</strong>
                        </span>
                            ${r.commentCustom.commentContent}
                    </a>
                </li>
                </c:forEach>
            </ul>
        </div>
        <div class="clear"></div>
    </aside>
    <%--最新评论 end--%>

</div>



<%--头脑风暴主体-右侧侧边栏 end--%>
