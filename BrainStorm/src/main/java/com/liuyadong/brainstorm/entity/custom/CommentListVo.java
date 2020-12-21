package com.liuyadong.brainstorm.entity.custom;


import com.liuyadong.brainstorm.util.others.Page;

/**
 *  用于封装评论信息，包括评论内容，作者信息，想法信息
  */
public class CommentListVo {
    //评论信息
    private CommentCustom commentCustom;

    //想法信息
    private ThoughtCustom thoughtCustom;

    //分页信息
    private Page page;

    public CommentCustom getCommentCustom() {
        return commentCustom;
    }

    public void setCommentCustom(CommentCustom commentCustom) {
        this.commentCustom = commentCustom;
    }

    public ThoughtCustom getThoughtCustom() {
        return thoughtCustom;
    }

    public void setThoughtCustom(ThoughtCustom thoughtCustom) {
        this.thoughtCustom = thoughtCustom;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
