package com.liuyadong.brainstorm.controller.Home;

import com.liuyadong.brainstorm.entity.custom.ThoughtListVo;
import com.liuyadong.brainstorm.entity.custom.TagCustom;
import com.liuyadong.brainstorm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class TagController {

	@Autowired
	private TagService tagService;

	@ModelAttribute
	public void init(Model model) throws Exception {

	}
	
	//根据标签查询想法
	@RequestMapping("tag/{tagId}")
	@ResponseBody
	public ModelAndView ThoughtListByTagView(@PathVariable("tagId") Integer tagId) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		//设置每页显示条数、
		int pageSize = 3;
		List<ThoughtListVo> thoughtListVoList = tagService.getThoughtListByPage(1,null,pageSize,tagId);

		modelAndView.addObject("thoughtListVoList",thoughtListVoList);

		//标签信息
		TagCustom tagCustom = tagService.getTagById(tagId);
		modelAndView.addObject("tagCustom",tagCustom);

		modelAndView.setViewName("Home/Page/thoughtListByTag");
		return modelAndView;
	}
	
	//根据标签查询想法分页
	@RequestMapping("tag/{tagId}/p/{pageNow}")
	@ResponseBody
	public  ModelAndView ThoughtListByTagAndPageView(@PathVariable("pageNow") Integer pageNow,@PathVariable("tagId") Integer tagId) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		
		//设置每页显示条数
		int pageSize = 3;
		List<ThoughtListVo> thoughtListVoList = tagService.getThoughtListByPage(1,pageNow,pageSize,tagId);
		modelAndView.addObject("thoughtListVoList",thoughtListVoList);
		modelAndView.setViewName("Home/Page/thoughtListByTag");
		//标签信息
		TagCustom tagCustom = tagService.getTagById(tagId);
		modelAndView.addObject("tagCustom",tagCustom);
		
		return modelAndView;
	}
}
