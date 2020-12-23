<%--
  Created by IntelliJ IDEA.
  User: liuyadong
  Date: 2020/12/10
  description:
  To change this template use File | Settings | File Templates.
--%>
<%--
    头脑风暴页脚部分
    包括：页脚部分
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<script src="https://cdn.jsdelivr.net/npm/jquery/dist/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/font-awesome/css/font-awesome.min.css"/>
<script src="https://cdn.jsdelivr.net/gh/stevenjoezhang/live2d-widget@latest//autoload.js"></script>
<%--页脚 start--%>
<footer id="colophon" class="site-footer" role="contentinfo">
    <div class="site-info">
        <p style="text-align: center;">Copyright © 2020
            <a href="/" target="_blank" rel="noopener noreferrer">${options.optionSiteTitle}</a>
            All rights reserved.
            <a target="_blank" href="/map" >
                <span class="font-text">站点地图</span>
            </a>
        </p>
    </div>
    <!-- .site-info -->
</footer><!-- .site-footer -->
<%--页脚 end--%>

