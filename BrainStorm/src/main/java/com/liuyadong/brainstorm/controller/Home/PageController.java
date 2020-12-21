package com.liuyadong.brainstorm.controller.Home;

import com.liuyadong.brainstorm.entity.custom.ThoughtListVo;
import com.liuyadong.brainstorm.entity.custom.CategoryCustom;
import com.liuyadong.brainstorm.entity.custom.PageCustom;
import com.liuyadong.brainstorm.entity.custom.TagCustom;
import com.liuyadong.brainstorm.service.ThoughtService;
import com.liuyadong.brainstorm.service.CategoryService;
import com.liuyadong.brainstorm.service.PageService;
import com.liuyadong.brainstorm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PageController {
	@Autowired
	private PageService pageService;

	@Autowired
	private ThoughtService thoughtService;

	@Autowired
	private CategoryService categoryService;


	@Autowired
	private TagService tagService;


	@ModelAttribute
	public void init(Model model) throws Exception {

	}

	//页面显示
	@RequestMapping(value = "/{key}")
	public ModelAndView ThoughtDetailView(@PathVariable("key") String key) throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		PageCustom pageCustom = pageService.getPageByKey(1,key);
		if(pageCustom!=null) {
			modelAndView.addObject("pageCustom",pageCustom);
			modelAndView.setViewName("Home/Page/page");
		} else {
			modelAndView.setViewName("Home/Error/404");
		}
		return modelAndView;

	}


	//想法归档页面显示
	@RequestMapping(value = "/thoughtFile")
	public ModelAndView thoughtFile() throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("Home/Page/thoughtFile");
		List<ThoughtListVo> thoughtList = thoughtService.listThought(1);
		modelAndView.addObject("thoughtList",thoughtList);
		return modelAndView;
	}

	//站点地图显示
	@RequestMapping(value = "/map")
	public ModelAndView siteMap() throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("Home/Page/siteMap");
		//想法显示
		List<ThoughtListVo> thoughtList = thoughtService.listThought(1);
		modelAndView.addObject("thoughtList",thoughtList);
        //分类显示
        List<CategoryCustom> categoryCustomList = categoryService.listCategory(1);
        modelAndView.addObject("categoryCustomList",categoryCustomList);
        //标签显示
        List<TagCustom> tagCustomList = tagService.listTag(1);
        modelAndView.addObject("tagCustomList",tagCustomList);

		return modelAndView;
	}

	//留言板
	@RequestMapping(value = "/message")
	public ModelAndView message() throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("Home/Page/message");
		return modelAndView;
	}
}
