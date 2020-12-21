package com.liuyadong.brainstorm.entity.custom;

import com.liuyadong.brainstorm.entity.Tag;


public class TagCustom extends Tag {
	//标签对应的想法数目
	private Integer thoughtCount;

	public Integer getThoughtCount() {
		return thoughtCount;
	}

	public void setThoughtCount(Integer thoughtCount) {
		this.thoughtCount = thoughtCount;
	}
}
