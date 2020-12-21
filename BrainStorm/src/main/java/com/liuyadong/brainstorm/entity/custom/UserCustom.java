package com.liuyadong.brainstorm.entity.custom;

import com.liuyadong.brainstorm.entity.User;

public class UserCustom extends User {
    //用户的想法数
    private Integer thoughtCount;

    public Integer getThoughtCount() {
        return thoughtCount;
    }

    public void setThoughtCount(Integer thoughtCount) {
        this.thoughtCount = thoughtCount;
    }
}
