package com.liuyadong.brainstorm.controller.Home;


import com.liuyadong.brainstorm.entity.custom.ThoughtListVo;
import com.liuyadong.brainstorm.entity.custom.CategoryCustom;
import com.liuyadong.brainstorm.service.CategoryService;
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
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;

	@ModelAttribute
	public void init(Model model) throws Exception {

		
	}
	
	//根据分类查询想法
	@RequestMapping("/category/{cateId}")
	@ResponseBody
	public ModelAndView ThoughtListByCategoryView(@PathVariable("cateId") Integer cateId) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		//设置每页显示条数
		int pageSize = 10;
		List<ThoughtListVo> thoughtListVoList = categoryService.listThoughtWithCategoryByPage(1,null,pageSize,cateId);

		//如果thoughtListVoList=null表示该分类不存在，如果=0表示该分类暂时没有想法
        modelAndView.addObject("thoughtListVoList",thoughtListVoList);

		//该分类信息
		CategoryCustom categoryCustom = categoryService.getCategoryById(1,cateId);
		modelAndView.addObject("categoryCustom",categoryCustom);

		modelAndView.setViewName("Home/Page/thoughtListByCategory");
		return modelAndView;
	}
	
	//根据分类查询想法分页
	@RequestMapping("/category/{cateId}/p/{pageNow}")
	@ResponseBody
	public  ModelAndView ThoughtListByCategoryAndPageView(@PathVariable("pageNow") Integer pageNow,@PathVariable("cateId") Integer cateId) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		//设置每页显示条数
		int pageSize = 10;
		List<ThoughtListVo> thoughtListVoList = categoryService.listThoughtWithCategoryByPage(1,pageNow,pageSize,cateId);
		modelAndView.addObject("thoughtListVoList",thoughtListVoList);
		modelAndView.setViewName("Home/Page/thoughtListByCategory");

        //该分类信息
        CategoryCustom categoryCustom = categoryService.getCategoryById(1,cateId);
        modelAndView.addObject("categoryCustom",categoryCustom);
		return modelAndView;
	}

}
