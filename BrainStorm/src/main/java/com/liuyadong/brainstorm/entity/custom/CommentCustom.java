package com.liuyadong.brainstorm.entity.custom;

import com.liuyadong.brainstorm.entity.Comment;


public class CommentCustom extends Comment {
    //评论者的头像
    private String commentAuthorAvatar;

    public String getCommentAuthorAvatar() {
        return commentAuthorAvatar;
    }

    public void setCommentAuthorAvatar(String commentAuthorAvatar) {
        this.commentAuthorAvatar = commentAuthorAvatar;
    }
}
