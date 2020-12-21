package com.liuyadong.brainstorm.service;


import com.liuyadong.brainstorm.entity.Category;
import com.liuyadong.brainstorm.entity.custom.ThoughtListVo;
import com.liuyadong.brainstorm.entity.custom.CategoryCustom;


import java.util.List;


public interface CategoryService {
	//获得分类总数
	public Integer countCategory(Integer status) throws Exception;
	
	//获得分类列表
	public List<CategoryCustom> listCategory(Integer status) throws Exception;

	//获得带有该分类的想法列表
	public  List<ThoughtListVo> listThoughtWithCategoryByPage(Integer status,Integer pageNow,Integer pageSize,Integer cateId) throws Exception;

	//获得某个分类信息
	public CategoryCustom getCategory(Integer status,Integer id) throws Exception;

	//删除分类
	public void deleteCategory(Integer id) throws Exception;

	//根据id查询分类信息
	public CategoryCustom getCategoryById(Integer status,Integer id) throws Exception;

	//添加分类
	public void insertCategory(Category category) throws Exception;

	//更新分类
	public void updateCategory(Category category) throws Exception;

	//根据分类名获取分类
	public Category getCategoryByName(String name) throws Exception;


}
