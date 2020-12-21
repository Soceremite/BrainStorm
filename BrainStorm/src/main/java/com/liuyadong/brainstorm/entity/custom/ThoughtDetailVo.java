package com.liuyadong.brainstorm.entity.custom;

import java.util.List;


public class ThoughtDetailVo {
	//想法相关信息
	private ThoughtCustom thoughtCustom;
	
	//想法的作者相关信息
	private UserCustom userCustom;
	
	//想法的分类相关信息
	private List<CategoryCustom> categoryCustomList;
	
	//想法的标签相关信息
	private List<TagCustom> tagCustomList;
	
	//评论信息
	private List<CommentCustom> commentCustomList;

	public ThoughtCustom getThoughtCustom() {
		return thoughtCustom;
	}
	
	public void setThoughtCustom(ThoughtCustom thoughtCustom) {
		this.thoughtCustom = thoughtCustom;
	}
	
	public UserCustom getUserCustom() {
		return userCustom;
	}
	
	public void setUserCustom(UserCustom userCustom) {
		this.userCustom = userCustom;
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
	
	public List<CommentCustom> getCommentCustomList() {
		return commentCustomList;
	}
	
	public void setCommentCustomList(List<CommentCustom> commentCustomList) {
		this.commentCustomList = commentCustomList;
	}

}
