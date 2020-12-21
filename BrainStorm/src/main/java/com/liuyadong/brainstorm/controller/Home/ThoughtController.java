package com.liuyadong.brainstorm.controller.Home;


import com.liuyadong.brainstorm.entity.custom.ThoughtCustom;
import com.liuyadong.brainstorm.entity.custom.ThoughtDetailVo;
import com.liuyadong.brainstorm.entity.custom.ThoughtSearchVo;
import com.liuyadong.brainstorm.service.ThoughtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import java.util.List;


@Controller
public class ThoughtController {

	@Autowired
	private ThoughtService thoughtService;
	
	@ModelAttribute
	public void init(Model model) throws Exception {
		
	}
	
	//想法详情页显示
	@RequestMapping(value = "/thought/{thoughtId}")
	@ResponseBody //适合RESTful
	public ModelAndView ThoughtDetailView(@PathVariable("thoughtId") Integer thoughtId) throws Exception{
		ModelAndView modelAndView = new ModelAndView();

		//想法信息，分类，标签，作者，评论
		ThoughtDetailVo thoughtDetailVo  = thoughtService.getThoughtDetailById(thoughtId);
		if(thoughtDetailVo!=null) {

			modelAndView.addObject("thoughtDetailVo", thoughtDetailVo);
			//相关想法
			Integer parentCategoryId = thoughtService.getThoughtById(1, thoughtId).getThoughtParentCategoryId();
			Integer childCategoryId = thoughtService.getThoughtById(1, thoughtId).getThoughtChildCategoryId();
			List<ThoughtCustom> similarThoughtList = thoughtService.listThoughtWithSameCategory(1, parentCategoryId, childCategoryId, 5);
			modelAndView.addObject("similarThoughtList", similarThoughtList);

			//猜你喜欢
			List<ThoughtCustom> mostViewThoughtList = thoughtService.listThoughtByViewCount(1, 5);
			modelAndView.addObject("mostViewThoughtList", mostViewThoughtList);
			//获取下一篇想法
			ThoughtCustom afterThought = thoughtService.getAfterThought(1, thoughtId);
			modelAndView.addObject("afterThought", afterThought);
			//获取上一篇想法
			ThoughtCustom preThought = thoughtService.getPreThought(1, thoughtId);
			modelAndView.addObject("preThought", preThought);
			modelAndView.setViewName("Home/Page/thoughtDetail");
		} else {
			modelAndView.setViewName("Home/Error/404");
		}
		return modelAndView;//不会被解析为跳转路径，而是直接写入HTTP response body中
		
	}
	
	//想法点赞数增加
	@RequestMapping(value = "/thought/addLike/{id}",method = {RequestMethod.POST})
	@ResponseBody
	public Integer increaseLikeCount(@PathVariable("id") Integer id)
		throws Exception {
		ThoughtCustom thoughtCustom = thoughtService.getThoughtById(1,id);
		int thoughtCount = thoughtCustom.getThoughtLikeCount();
		thoughtCustom.setThoughtLikeCount(thoughtCount + 1);
		thoughtService.updateThought(id, thoughtCustom);
		return thoughtCount+1;
	}
	
	//想法访问量数增加
	@RequestMapping(value = "/thought/addView/{id}",method = {RequestMethod.POST})
	@ResponseBody
	public Integer increaseViewCount(@PathVariable("id") Integer id)
		throws Exception {
		ThoughtCustom thoughtCustom = thoughtService.getThoughtById(1,id);
		int thoughtCount = thoughtCustom.getThoughtViewCount();
		thoughtCustom.setThoughtViewCount(thoughtCount + 1);
		thoughtService.updateThought(id, thoughtCustom);
		return thoughtCount+1;
	}

	


	//想法信息修改提交
	@RequestMapping(value = "/editThoughtSubmit",method = RequestMethod.POST)
	public String editThoughtSubmit(Integer id ,
								 ThoughtCustom thoughtCustom
	) throws Exception {
		
		
		thoughtService.updateThought(id,thoughtCustom);
		
		return "redirect:thoughtList.action";
	}

	//想法搜索实现
	@RequestMapping("/search")
	@ResponseBody
	public ModelAndView SearchPageView(HttpServletRequest request,Model model) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		//设置每页显示的数量
		int pageSize = 10;
		String query = request.getParameter("query");
		List<ThoughtSearchVo> thoughtSearchVoList = thoughtService.listSearchResultByPage(1,request,model,null,pageSize,query);
		if(thoughtSearchVoList!=null) {
			modelAndView.addObject("thoughtSearchVoList", thoughtSearchVoList);
		} else {
			modelAndView.addObject("thoughtSearchVoList", null);
		}
		modelAndView.setViewName("Home/Page/search");
		return modelAndView;
	}

	//想法搜索分页实现
	@RequestMapping("/p/{pageNow}/search")
	@ResponseBody
	public  ModelAndView SearchPageByPageView(HttpServletRequest request, Model model,@PathVariable("pageNow") Integer pageNow) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		//设置每页显示的数量
		int pageSize = 10;
		String query = request.getParameter("query");
		List<ThoughtSearchVo> thoughtSearchVoList = thoughtService.listSearchResultByPage(1,request,model,pageNow,pageSize,query);
		modelAndView.addObject("thoughtSearchVoList", thoughtSearchVoList);
		modelAndView.setViewName("Home/Page/search");
		return modelAndView;
	}
	






	
	
}
