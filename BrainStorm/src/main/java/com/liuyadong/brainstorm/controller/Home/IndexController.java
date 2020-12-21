package com.liuyadong.brainstorm.controller.Home;

import com.liuyadong.brainstorm.entity.custom.ThoughtListVo;
import com.liuyadong.brainstorm.entity.custom.NoticeCustom;
import com.liuyadong.brainstorm.service.ThoughtService;
import com.liuyadong.brainstorm.service.NoticeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class IndexController {
	
	@Autowired
	private ThoughtService thoughtService;


	@Autowired
	private NoticeService noticeService;
	
	@ModelAttribute
	public void init(Model model)  throws Exception {

		//公告
		List<NoticeCustom> noticeCustomList = noticeService.listNotice(1);
		model.addAttribute("noticeCustomList",noticeCustomList);
	}
	
	//首页显示
	@RequestMapping("/")
	public ModelAndView IndexView() throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		//想法列表
		int pageSize = 10;
		List<ThoughtListVo> thoughtListVoList = thoughtService.listThoughtByPage(1,null,pageSize);
		modelAndView.addObject("thoughtListVoList",thoughtListVoList);

		modelAndView.setViewName("/Home/index");
		return modelAndView;
	}
	
	//想法分页显示
	@RequestMapping("p/{pageNow}")
	//适合RESTful
	public @ResponseBody  ModelAndView ThoughtListByPageView(@PathVariable("pageNow") Integer pageNow) throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		//设置每页显示的数量
		int pageSize = 10;
		List<ThoughtListVo> thoughtListVoList = thoughtService.listThoughtByPage(1,pageNow,pageSize);
		modelAndView.addObject("thoughtListVoList",thoughtListVoList);
		modelAndView.setViewName("Home/index");
		return modelAndView;//不会被解析为跳转路径，而是直接写入HTTP response body中
	}

}




