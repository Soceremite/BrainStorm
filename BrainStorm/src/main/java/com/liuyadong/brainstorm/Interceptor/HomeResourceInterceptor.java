package com.liuyadong.brainstorm.Interceptor;
import com.liuyadong.brainstorm.entity.Options;
import com.liuyadong.brainstorm.entity.custom.*;
import com.liuyadong.brainstorm.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

import java.util.ArrayList;
import java.util.List;

public class HomeResourceInterceptor implements WebRequestInterceptor {
    @Autowired
    private ThoughtService thoughtService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;


    @Autowired
    private OptionsService optionsService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private CommentService commentService;
    /**
     * 在请求处理之前执行，该方法主要是用于准备资源数据的，然后可以把它们当做请求属性放到WebRequest中
     */
    @Override
    public void preHandle(WebRequest request) throws Exception {
        System.out.println("HomeResourceInterceptor...preHandle......");
     //导航主要菜单显示
        //分类目录显示
        List<CategoryCustom> categoryList = categoryService.listCategory(1);
        request.setAttribute("categoryList",categoryList,WebRequest.SCOPE_REQUEST);
        //菜单显示
        List<MenuCustom> menuCustomList = menuService.listMenu(1);
        request.setAttribute("menuCustomList",menuCustomList,WebRequest.SCOPE_REQUEST);

      //侧边栏显示
        //标签列表显示
		List<TagCustom> tagList = tagService.listTag(1);
		request.setAttribute("tagList",tagList,WebRequest.SCOPE_REQUEST);
		//获得随机想法
		List<ThoughtCustom> randomThoughtList = thoughtService.listRandomThought(1,8);
		request.setAttribute("randomThoughtList",randomThoughtList,WebRequest.SCOPE_REQUEST);
		//获得热评想法
		List<ThoughtCustom> mostCommentThoughtList = thoughtService.listThoughtByCommentCount(1,8);
		request.setAttribute("mostCommentThoughtList",mostCommentThoughtList,WebRequest.SCOPE_REQUEST);
		//最新评论
        List<CommentListVo> recentCommentList = commentService.listRecentComment(10);
        request.setAttribute("recentCommentList",recentCommentList,WebRequest.SCOPE_REQUEST);

		//获得网站概况
		List<String> siteBasicStatistics = new ArrayList<String>();
		siteBasicStatistics.add(thoughtService.countThought(1)+"");
		siteBasicStatistics.add(thoughtService.countThoughtComment(1)+"");
		siteBasicStatistics.add(categoryService.countCategory(1)+"");
		siteBasicStatistics.add(tagService.countTag(1)+"");
		siteBasicStatistics.add(thoughtService.countThoughtView(1)+"");
		request.setAttribute("siteBasicStatistics",siteBasicStatistics,WebRequest.SCOPE_REQUEST);
		//最后更新的想法
        ThoughtCustom lastUpdateThought = thoughtService.getLastUpdateThought();
        request.setAttribute("lastUpdateThought",lastUpdateThought,WebRequest.SCOPE_REQUEST);

        //页脚显示
		//头脑风暴基本信息显示(Options)
		Options options = optionsService.getOptions();
		request.setAttribute("options", options,WebRequest.SCOPE_REQUEST);

    }
    /**
     * 该方法将在Controller执行之后，返回视图之前执行，ModelMap表示请求Controller处理之后返回的Model对象，所以可以在
     * 这个方法中修改ModelMap的属性，从而达到改变返回的模型的效果。
     */
    @Override
    public void postHandle(WebRequest request, ModelMap map) throws Exception {
        //System.out.println("postHandle.......");
    }

    /**
     * 该方法将在整个请求完成之后，也就是说在视图渲染之后进行调用，主要用于进行一些资源的释放
     */
    @Override
    public void afterCompletion(WebRequest request, Exception exception)
            throws Exception {
        //System.out.println("afterCompletion");
    }
}