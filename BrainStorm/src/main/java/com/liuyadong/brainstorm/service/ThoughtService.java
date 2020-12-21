package com.liuyadong.brainstorm.service;

import com.liuyadong.brainstorm.entity.Thought;
import com.liuyadong.brainstorm.entity.custom.ThoughtCustom;
import com.liuyadong.brainstorm.entity.custom.ThoughtDetailVo;
import com.liuyadong.brainstorm.entity.custom.ThoughtListVo;
import com.liuyadong.brainstorm.entity.custom.ThoughtSearchVo;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface ThoughtService {
	//获取想法总数
	public Integer countThought(Integer status) throws Exception;
	
	//获取评论总数
	public Integer countThoughtComment(Integer status) throws Exception;
	
	//获得浏览量总数
	public Integer countThoughtView(Integer status) throws Exception;
	
	//获得所有想法不分页
	public List<ThoughtListVo> listThought(Integer status) throws Exception;
	
	//根据id获得想法
	public ThoughtCustom getThoughtById(Integer status,Integer id) throws Exception;

	//修改想法信息
	public void updateThought(Integer id, Thought thought) throws Exception;

	//批量删除想法
	public void deleteThoughtBatch(Integer[] ids) throws  Exception;

	//删除想法
	public void deleteThought(Integer id) throws Exception;

	//分页显示()
	public List<ThoughtListVo> listThoughtByPage(Integer status,Integer pageNow,Integer pageSize) throws Exception;
	
	//想法详情页面显示
	public ThoughtDetailVo getThoughtDetailById(Integer id) throws Exception;
	
	//想法查询分页显示
	public List<ThoughtSearchVo> listSearchResultByPage(Integer status,HttpServletRequest request, Model model, Integer pageNow, Integer pageSize, String s) throws Exception;
 
	//获得相关想法
	public List<ThoughtCustom> listThoughtWithSameCategory(Integer status,Integer parentCategoryId,Integer childCategoryId, Integer limit) throws Exception;
	
	//获取访问量较多的想法
	public List<ThoughtCustom> listThoughtByViewCount(Integer status,Integer limit) throws Exception;
	
	//获得上一篇想法
	public ThoughtCustom getAfterThought(Integer status,Integer id) throws Exception;
	
	//获得下一篇想法
	public ThoughtCustom getPreThought(Integer status,Integer id) throws Exception;
	
	//获得随机想法
	public List<ThoughtCustom> listRandomThought(Integer status,Integer limit) throws  Exception;
	
	//获得评论数较多的想法
	public List<ThoughtCustom> listThoughtByCommentCount(Integer status,Integer limit) throws Exception;

	//添加想法
	public void insertThought(Thought thought) throws Exception;

	//获得某个分类的想法数
	public Integer countThoughtWithCategory(Integer status,Integer id) throws Exception;

	//获得某个标签的想法数
	public Integer countThoughtWithTag(Integer status,Integer id) throws Exception;

	//更新想法的评论数
	public void updateCommentCount(Integer thoughtId) throws Exception;

	//获得最后更新记录
	public ThoughtCustom getLastUpdateThought() throws Exception;
}
