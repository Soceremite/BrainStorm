package com.liuyadong.brainstorm.service.impl;

import com.liuyadong.brainstorm.entity.Comment;
import com.liuyadong.brainstorm.entity.custom.ThoughtCustom;
import com.liuyadong.brainstorm.entity.custom.CommentListVo;
import com.liuyadong.brainstorm.mapper.ThoughtMapper;
import com.liuyadong.brainstorm.mapper.CommentMapper;
import com.liuyadong.brainstorm.mapper.custom.ThoughtMapperCustom;
import com.liuyadong.brainstorm.mapper.custom.CommentMapperCustom;
import com.liuyadong.brainstorm.entity.custom.CommentCustom;
import com.liuyadong.brainstorm.service.CommentService;
import com.liuyadong.brainstorm.util.others.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.liuyadong.brainstorm.util.Functions;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


public class CommentServiceImpl implements CommentService{
	@Autowired
	private CommentMapperCustom commentMapperCustom;

	@Autowired
	private CommentMapper commentMapper;

	@Autowired
	private ThoughtMapper thoughtMapper;

	@Autowired
	private ThoughtMapperCustom thoughtMapperCustom;

	@Override
	public void insertComment(HttpServletRequest request, Comment comment) throws Exception {
	    String ip = Functions.getIpAddr(request);
	    comment.setCommentIp(ip);
		commentMapper.insertSelective(comment);
	}
	
	@Override
	public List<CommentCustom> listCommentByThoughtId(Integer status,Integer thoughtId) {
		List<CommentCustom> commentCustomList = commentMapperCustom.listCommentByThoughtId(status,thoughtId);
		return commentCustomList;
	}

	@Override
	public CommentCustom getCommentById(Integer id) throws Exception {
		CommentCustom commentCustom = new CommentCustom();
		Comment comment = commentMapper.selectByPrimaryKey(id);
		BeanUtils.copyProperties(comment,commentCustom);
		return commentCustom;
	}

	@Override
	public List<CommentListVo> listCommentByPage(Integer status, Integer pageNow, Integer pageSize) throws Exception {
		List<CommentListVo> commentListVoList = new ArrayList<CommentListVo>();

		List<CommentCustom> commentCustomList = new ArrayList<CommentCustom>();


		Page page = null;
		int totalCount = commentMapperCustom.countComment(status);
		if (pageNow != null) {
			page = new Page(totalCount, pageNow,pageSize);
			commentCustomList = commentMapperCustom.listCommentByPage(status,page.getStartPos(),pageSize);
		} else {
			page = new Page(totalCount, 1,pageSize);
			commentCustomList = commentMapperCustom.listCommentByPage(status,page.getStartPos(), pageSize);
		}


		for(int i=0;i<commentCustomList.size();i++) {
			CommentListVo commentListVo = new CommentListVo();
			//获得想法信息
			Integer thoughtId = commentCustomList.get(i).getCommentThoughtId();
			ThoughtCustom thoughtCustom = thoughtMapperCustom.getThoughtById(status,thoughtId);
			commentListVo.setThoughtCustom(thoughtCustom);

			//评论信息
            CommentCustom commentCustom = commentCustomList.get(i);
			//评论者Gravatar头像
            String avatar = Functions.getGravatar(commentCustom.getCommentAuthorEmail());
            commentCustom.setCommentAuthorAvatar(avatar);
            commentListVo.setCommentCustom(commentCustomList.get(i));

			commentListVoList.add(commentListVo);
		}

		if(commentListVoList.size()>0) {
			//4、将Page信息存储在第一个元素中
			commentListVoList.get(0).setPage(page);
		}
		return commentListVoList;
	}

	@Override
	public List<CommentListVo> listCommentVo(Integer status) throws Exception {
		List<CommentListVo> commentListVoList = new ArrayList<CommentListVo>();

		List<CommentCustom> commentCustomList = commentMapperCustom.listComment(status);

		for(int i=0;i<commentCustomList.size();i++) {
			CommentListVo commentListVo = new CommentListVo();
			//获得想法信息
			Integer thoughtId = commentCustomList.get(i).getCommentThoughtId();
			ThoughtCustom thoughtCustom = thoughtMapperCustom.getThoughtById(status,thoughtId);
			commentListVo.setThoughtCustom(thoughtCustom);

			//评论信息
			CommentCustom commentCustom = commentCustomList.get(i);
			//评论者Gravatar头像
			String avatar = Functions.getGravatar(commentCustom.getCommentAuthorEmail());
			commentCustom.setCommentAuthorAvatar(avatar);
			commentListVo.setCommentCustom(commentCustomList.get(i));

			commentListVoList.add(commentListVo);
		}

		return commentListVoList;
	}

	@Override
	public List<CommentCustom> listComment(Integer status) throws Exception {
		List<CommentCustom> commentCustomList = commentMapperCustom.listComment(status);
		return commentCustomList;
	}

	@Override
	public void deleteComment(Integer id) throws Exception {
		commentMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void updateComment(Comment comment) throws Exception {
		commentMapper.updateByPrimaryKeySelective(comment);
	}

	@Override
	public Integer countComment(Integer status) throws Exception {
		Integer commentCount = commentMapperCustom.countComment(status);
		return commentCount;
	}

	@Override
	public List<CommentListVo> listRecentComment(Integer limit) throws Exception {
		List<CommentListVo> recentCommentList = new ArrayList<CommentListVo>();
		List<CommentCustom> commentCustomList = commentMapperCustom.listRecentComment(limit);
		for(int i=0;i<commentCustomList.size();i++) {
			CommentListVo commentListVo = new CommentListVo();
			//给每个评论用户添加头像
			String avatar = Functions.getGravatar(commentCustomList.get(i).getCommentAuthorEmail());
			CommentCustom commentCustom = commentCustomList.get(i);
			commentCustom.setCommentAuthorAvatar(avatar);
			commentListVo.setCommentCustom(commentCustom);
			//找到评论对应的想法信息
			ThoughtCustom thoughtCustom = thoughtMapperCustom.getThoughtById(1,commentCustom.getCommentThoughtId());
			commentListVo.setThoughtCustom(thoughtCustom);

			recentCommentList.add(commentListVo);
		}

		return recentCommentList;
	}

	@Override
	public List<Comment> listChildComment(Integer id) throws Exception {
		List<Comment> childCommentList = commentMapperCustom.listChildComment(id);
		return childCommentList;
	}

}
