package com.liuyadong.brainstorm.controller.Admin;

import com.liuyadong.brainstorm.entity.Thought;
import com.liuyadong.brainstorm.entity.custom.*;
import com.liuyadong.brainstorm.service.ThoughtService;
import com.liuyadong.brainstorm.service.CategoryService;
import com.liuyadong.brainstorm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/admin/thought")
public class BackThoughtController {
    @Autowired
    private ThoughtService thoughtService;


    @Autowired
    private TagService tagService;

    @Autowired
    private CategoryService categoryService;

    //创作者中心想法列表显示
    @RequestMapping(value = "")
    public ModelAndView index() throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        //分页显示已发布想法
        Integer pageSize = 20;
        List<ThoughtListVo> publishedThoughtListVoList = thoughtService.listThoughtByPage(1,null,pageSize);
        modelAndView.addObject("publishedThoughtListVoList",publishedThoughtListVoList);

        //不分页显示 草稿想法
        List<ThoughtListVo> draftThoughtList = thoughtService.listThought(0);
        modelAndView.addObject("draftThoughtList",draftThoughtList);
        modelAndView.setViewName("Admin/Thought/index");
        return modelAndView;
    }

    //想法分页显示
    @RequestMapping("/p/{pageNow}")
    public @ResponseBody  ModelAndView ThoughtListByPageView(@PathVariable("pageNow") Integer pageNow) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        //分页显示已发布想法
        Integer pageSize = 20;
        List<ThoughtListVo> publishedThoughtListVoList = thoughtService.listThoughtByPage(1,pageNow,pageSize);
        modelAndView.addObject("publishedThoughtListVoList",publishedThoughtListVoList);

        //不分页显示 草稿想法
        List<ThoughtListVo> draftThoughtList = thoughtService.listThought(0);
        modelAndView.addObject("draftThoughtList",draftThoughtList);
        modelAndView.setViewName("Admin/Thought/index");
        return modelAndView;
    }

    //创作者中心添加想法页面显示
    @RequestMapping(value = "/insert")
    public ModelAndView insertThoughtView() throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        List<CategoryCustom> categoryCustomList = categoryService.listCategory(1);
        List<TagCustom> tagCustomList = tagService.listTag(1);

        modelAndView.addObject("categoryCustomList",categoryCustomList);
        modelAndView.addObject("tagCustomList",tagCustomList);

        modelAndView.setViewName("Admin/Thought/insert");
        return modelAndView;
    }

    //创作者中心添加想法提交操作
    @RequestMapping(value = "/insertSubmit",method = RequestMethod.POST)
    public String insertThoughtSubmit(Thought thought) throws Exception {

        thought.setThoughtPostTime(new Date());
        thought.setThoughtUpdateTime(new Date());
        thought.setThoughtIsComment(1);
        thought.setThoughtViewCount(0);
        thought.setThoughtLikeCount(0);
        thought.setThoughtCommentCount(0);
        thought.setThoughtStatus(1);
        thought.setThoughtOrder(1);

        thoughtService.insertThought(thought);

        return "redirect:/admin/thought";
    }

    //创作者中心添加想法提交操作
    @RequestMapping(value = "/insertDraftSubmit",method = RequestMethod.POST)
    public String insertThoughtDraftSubmit(Thought thought) throws Exception {

        thought.setThoughtPostTime(new Date());
        thought.setThoughtUpdateTime(new Date());
        thought.setThoughtIsComment(1);
        thought.setThoughtViewCount(0);
        thought.setThoughtLikeCount(0);
        thought.setThoughtCommentCount(0);
        thought.setThoughtStatus(0);
        thought.setThoughtOrder(1);

        thoughtService.insertThought(thought);

        return "redirect:/admin/thought";
    }


    //搜索实现
    @RequestMapping("/search")
    @ResponseBody
    public ModelAndView SearchPageView(HttpServletRequest request,Model model) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        //设置每页显示的数量
        int pageSize = 20;
        String query = request.getParameter("query");
        List<ThoughtSearchVo> thoughtSearchVoList = thoughtService.listSearchResultByPage(1,request,model,null,pageSize,query);
        modelAndView.addObject("thoughtSearchVoList", thoughtSearchVoList);
        modelAndView.setViewName("Admin/Thought/search");
        return modelAndView;
    }

    //搜索分页实现
    @RequestMapping("/p/{pageNow}/search")
    @ResponseBody
    public  ModelAndView SearchPageByPageView(HttpServletRequest request, Model model,@PathVariable("pageNow") Integer pageNow) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        //设置每页显示的数量
        int pageSize = 20;
        String query = request.getParameter("query");
        List<ThoughtSearchVo> thoughtSearchVoList = thoughtService.listSearchResultByPage(1,request,model,pageNow,pageSize,query);
        modelAndView.addObject("thoughtSearchVoList", thoughtSearchVoList);
        modelAndView.setViewName("/Admin/Thought/search");
        return modelAndView;
    }



    //删除想法
    @RequestMapping(value = "/delete/{id}")
    public void deleteThought(@PathVariable("id") Integer id) throws Exception {
        //调用service批量删除
        thoughtService.deleteThought(id);
    }

    //批量删除想法
    @RequestMapping(value = "/deleteBatch")
    public void deleteThoughts(HttpServletRequest request) throws Exception {
        String str = request.getParameter("ids");
        String[] arr = str.split(",");
        Integer[] ids = new Integer[arr.length];
        for(int i=0;i<arr.length;i++) {
            ids[i] = Integer.valueOf(arr[i]);
        }
        //调用service批量删除
        thoughtService.deleteThoughtBatch(ids);

    }

    //编辑想法页面显示
    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editThoughtView(@PathVariable("id") Integer id) throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        ThoughtCustom thoughtCustom =  thoughtService.getThoughtById(null,id);
        modelAndView.addObject("thoughtCustom",thoughtCustom);

        List<CategoryCustom> categoryCustomList = categoryService.listCategory(1);
        modelAndView.addObject("categoryCustomList",categoryCustomList);

        List<TagCustom> tagCustomList = tagService.listTag(1);
        modelAndView.addObject("tagCustomList",tagCustomList);


        modelAndView.setViewName("Admin/Thought/edit");
        return modelAndView;
    }


    //编辑想法提交
    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String editThoughtSubmit(ThoughtCustom thoughtCustom) throws Exception {
        Integer id = thoughtCustom.getThoughtId();
        thoughtCustom.setThoughtUpdateTime(new Date());
        thoughtService.updateThought(id,thoughtCustom);
        return "redirect:/admin/thought";
    }



}



