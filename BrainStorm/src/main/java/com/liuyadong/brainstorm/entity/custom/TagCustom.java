package com.liuyadong.brainstorm.entity.custom;

import com.liuyadong.brainstorm.entity.Tag;


public class TagCustom extends Tag {
	//标签对应的文章数目
	private Integer articleCount;

	public Integer getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(Integer articleCount) {
		this.articleCount = articleCount;
	}
}
