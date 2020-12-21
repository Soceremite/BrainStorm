package com.liuyadong.brainstorm.controller.Home;

import com.liuyadong.brainstorm.entity.Thought;
import com.liuyadong.brainstorm.entity.Comment;
import com.liuyadong.brainstorm.service.ThoughtService;
import com.liuyadong.brainstorm.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


@Controller
public class CommentController {
	@Autowired
	private CommentService commentService;

	@Autowired
	private ThoughtService thoughtService;
	
	//添加评论
	@RequestMapping(value = "/comment/insert",method = {RequestMethod.POST})
	@ResponseBody
	public void insertComment(HttpServletRequest request, Comment comment) throws Exception {
		//添加评论
		comment.setCommentCreateTime(new Date());
		commentService.insertComment(request,comment);
		//更新想法的评论数
		Thought thought = thoughtService.getThoughtById(null,comment.getCommentThoughtId());
		thoughtService.updateCommentCount(thought.getThoughtId());
	}


}
