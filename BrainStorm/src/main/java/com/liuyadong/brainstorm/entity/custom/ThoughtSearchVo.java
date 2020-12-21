package com.liuyadong.brainstorm.entity.custom;


import com.liuyadong.brainstorm.util.others.Page;

import java.util.List;

/**
 * 用于封装搜索的想法列表，包括想法信息，作者信息，分类信息，标签信息，搜索信息
 */
public class ThoughtSearchVo {
	//想法信息
	private ThoughtCustom thoughtCustom;
	
	//想法对应的分类
	private List<CategoryCustom> categoryCustomList;
	
	//想法对应的标签
	private List<TagCustom> tagCustomList;

	//作者信息
	private UserCustom userCustom;

	//想法分页信息
	private Page page;

	//搜索关键词
	private String query;

	public String getQuery() {
		return query;
	}

	public UserCustom getUserCustom() {
		return userCustom;
	}

	public void setUserCustom(UserCustom userCustom) {
		this.userCustom = userCustom;
	}

	public void setQuery(String query) {
		this.query = query;
	}


	public ThoughtCustom getThoughtCustom() {
		return thoughtCustom;
	}
	
	public void setThoughtCustom(ThoughtCustom thoughtCustom) {
		this.thoughtCustom = thoughtCustom;
	}

	public List<CategoryCustom> getCategoryCustomList() {
		return categoryCustomList;
	}

	public void setCategoryCustomList(List<CategoryCustom> categoryCustomList) {
		this.categoryCustomList = categoryCustomList;
	}

	public List<TagCustom> getTagCustomList() {
		return tagCustomList;
	}

	public void setTagCustomList(List<TagCustom> tagCustomList) {
		this.tagCustomList = tagCustomList;
	}

	public Page getPage() {
		return page;
	}
	
	public void setPage(Page page) {
		this.page = page;
	}
}
