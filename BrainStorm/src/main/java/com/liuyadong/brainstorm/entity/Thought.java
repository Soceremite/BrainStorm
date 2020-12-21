package com.liuyadong.brainstorm.entity;

import java.util.Date;

public class Thought {
    private Integer thoughtId;

    private Integer thoughtUserId;

    private String thoughtTitle;

    private Integer thoughtParentCategoryId;

    private Integer thoughtChildCategoryId;

    private String thoughtTagIds;

    private Integer thoughtViewCount;

    private Integer thoughtCommentCount;

    private Integer thoughtLikeCount;

    private Date thoughtPostTime;

    private Date thoughtUpdateTime;

    private Integer thoughtIsComment;

    private Integer thoughtStatus;

    private Integer thoughtOrder;

    private String thoughtContent;

    public Integer getThoughtId() {
        return thoughtId;
    }

    public void setThoughtId(Integer thoughtId) {
        this.thoughtId = thoughtId;
    }

    public Integer getThoughtUserId() {
        return thoughtUserId;
    }

    public void setThoughtUserId(Integer thoughtUserId) {
        this.thoughtUserId = thoughtUserId;
    }

    public String getThoughtTitle() {
        return thoughtTitle;
    }

    public void setThoughtTitle(String thoughtTitle) {
        this.thoughtTitle = thoughtTitle == null ? null : thoughtTitle.trim();
    }

    public Integer getThoughtParentCategoryId() {
        return thoughtParentCategoryId;
    }

    public void setThoughtParentCategoryId(Integer thoughtParentCategoryId) {
        this.thoughtParentCategoryId = thoughtParentCategoryId;
    }

    public Integer getThoughtChildCategoryId() {
        return thoughtChildCategoryId;
    }

    public void setThoughtChildCategoryId(Integer thoughtChildCategoryId) {
        this.thoughtChildCategoryId = thoughtChildCategoryId;
    }

    public String getThoughtTagIds() {
        return thoughtTagIds;
    }

    public void setThoughtTagIds(String thoughtTagIds) {
        this.thoughtTagIds = thoughtTagIds == null ? null : thoughtTagIds.trim();
    }

    public Integer getThoughtViewCount() {
        return thoughtViewCount;
    }

    public void setThoughtViewCount(Integer thoughtViewCount) {
        this.thoughtViewCount = thoughtViewCount;
    }

    public Integer getThoughtCommentCount() {
        return thoughtCommentCount;
    }

    public void setThoughtCommentCount(Integer thoughtCommentCount) {
        this.thoughtCommentCount = thoughtCommentCount;
    }

    public Integer getThoughtLikeCount() {
        return thoughtLikeCount;
    }

    public void setThoughtLikeCount(Integer thoughtLikeCount) {
        this.thoughtLikeCount = thoughtLikeCount;
    }

    public Date getThoughtPostTime() {
        return thoughtPostTime;
    }

    public void setThoughtPostTime(Date thoughtPostTime) {
        this.thoughtPostTime = thoughtPostTime;
    }

    public Date getThoughtUpdateTime() {
        return thoughtUpdateTime;
    }

    public void setThoughtUpdateTime(Date thoughtUpdateTime) {
        this.thoughtUpdateTime = thoughtUpdateTime;
    }

    public Integer getThoughtIsComment() {
        return thoughtIsComment;
    }

    public void setThoughtIsComment(Integer thoughtIsComment) {
        this.thoughtIsComment = thoughtIsComment;
    }

    public Integer getThoughtStatus() {
        return thoughtStatus;
    }

    public void setThoughtStatus(Integer thoughtStatus) {
        this.thoughtStatus = thoughtStatus;
    }

    public Integer getThoughtOrder() {
        return thoughtOrder;
    }

    public void setThoughtOrder(Integer thoughtOrder) {
        this.thoughtOrder = thoughtOrder;
    }

    public String getThoughtContent() {
        return thoughtContent;
    }

    public void setThoughtContent(String thoughtContent) {
        this.thoughtContent = thoughtContent == null ? null : thoughtContent.trim();
    }
}