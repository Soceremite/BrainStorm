package com.liuyadong.brainstorm.entity.custom;

import com.liuyadong.brainstorm.entity.User;

public class UserCustom extends User {
    //用户的文章数
    private Integer articleCount;

    public Integer getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(Integer articleCount) {
        this.articleCount = articleCount;
    }
}
