package com.liuyadong.brainstorm.entity.custom;

import com.liuyadong.brainstorm.entity.Category;


/**
 * 想法分类目录的信息的扩展
 */
public class CategoryCustom extends Category {
    //分类对应的想法数
    private Integer thoughtCount;

    //分类的父分类名称
    private String categoryPname;

    public Integer getThoughtCount() {
        return thoughtCount;
    }

    public void setThoughtCount(Integer thoughtCount) {
        this.thoughtCount = thoughtCount;
    }

    public String getCategoryPname() {
        return categoryPname;
    }

    public void setCategoryPname(String categoryPname) {
        this.categoryPname = categoryPname;
    }
}
