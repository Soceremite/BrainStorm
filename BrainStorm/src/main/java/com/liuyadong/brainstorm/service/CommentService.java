package com.liuyadong.brainstorm.service;

import com.liuyadong.brainstorm.entity.Comment;
import com.liuyadong.brainstorm.entity.custom.CommentCustom;
import com.liuyadong.brainstorm.entity.custom.CommentListVo;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public interface CommentService {
	//添加评论
	public void insertComment(HttpServletRequest request, Comment comment) throws Exception;
	
	//根据想法id获取评论列表
	public List<CommentCustom> listCommentByThoughtId(Integer status,Integer thoughtId);

	//根据id获取评论
	public CommentCustom getCommentById(Integer id) throws Exception;


	//获取所有评论列表
	public List<CommentListVo> listCommentByPage(Integer status, Integer pageNow, Integer pageSize) throws Exception;

	//获得评论列表
	public List<CommentListVo> listCommentVo(Integer status) throws Exception;

	//获得评论列表
	public List<CommentCustom> listComment(Integer status) throws Exception;

	//删除评论
	public void deleteComment(Integer id) throws Exception;

	//修改评论
	public void updateComment(Comment comment) throws Exception;

	//统计评论数
	public Integer countComment(Integer status) throws Exception;

	//获得最近评论
	public List<CommentListVo> listRecentComment(Integer limit) throws Exception;

	//获得评论的子评论
	public List<Comment> listChildComment(Integer id) throws Exception;


}
