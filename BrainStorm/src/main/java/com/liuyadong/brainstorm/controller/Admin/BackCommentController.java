package com.liuyadong.brainstorm.controller.Admin;


import com.liuyadong.brainstorm.entity.Thought;
import com.liuyadong.brainstorm.entity.Comment;
import com.liuyadong.brainstorm.entity.custom.CommentCustom;
import com.liuyadong.brainstorm.entity.custom.CommentListVo;
import com.liuyadong.brainstorm.service.ThoughtService;
import com.liuyadong.brainstorm.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/admin/comment")
public class BackCommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ThoughtService thoughtService;

    //创作者中心评论列表显示
    @RequestMapping(value = "")
    public ModelAndView commentListView() throws Exception {
        ModelAndView modelandview = new ModelAndView();
        //正常评论显示
        Integer pageSize = 20;
        List<CommentListVo> commentListVoList = commentService.listCommentByPage(null,null,pageSize);
        modelandview.addObject("commentListVoList", commentListVoList);

        //待审核评论不分页显示
        List<CommentListVo> hiddenCommentListVoList = commentService.listCommentVo(0);
        modelandview.addObject("hiddenCommentListVoList", hiddenCommentListVoList);

        modelandview.setViewName("Admin/Comment/index");
        return modelandview;

    }

    //创作者中心评论分页显示
    @RequestMapping("p/{pageNow}")
    //适合RESTful
    public @ResponseBody
    ModelAndView commentListByPageView(@PathVariable("pageNow") Integer pageNow) throws Exception{
        ModelAndView modelandview = new ModelAndView();
        //全部评论分页显示
        Integer pageSize = 20;
        List<CommentListVo> commentListVoList = commentService.listCommentByPage(null,pageNow,pageSize);
        modelandview.addObject("commentListVoList", commentListVoList);

        //待审核评论不分页显示
        List<CommentListVo> hiddenCommentListVoList = commentService.listCommentVo(0);
        modelandview.addObject("hiddenCommentListVoList", hiddenCommentListVoList);

        modelandview.setViewName("Admin/Comment/index");
        return modelandview;

    }

    //添加评论
    @RequestMapping(value = "/insert",method = {RequestMethod.POST})
    @ResponseBody
    public void insertComment(HttpServletRequest request, Comment comment) throws Exception {
        //添加评论
        comment.setCommentCreateTime(new Date());
        commentService.insertComment(request,comment);
        //更新想法的评论数
        Thought thought = thoughtService.getThoughtById(null,comment.getCommentThoughtId());
        thoughtService.updateCommentCount(thought.getThoughtId());
    }

    //删除评论
    @RequestMapping(value = "/delete/{id}")
    public void deleteComment(@PathVariable("id") Integer id) throws Exception {
        Comment comment = commentService.getCommentById(id);
        //删除评论
        commentService.deleteComment(id);
        //删除其子评论
        List<Comment> childCommentList = commentService.listChildComment(id);
        for(int i=0;i<childCommentList.size();i++) {
            commentService.deleteComment(childCommentList.get(i).getCommentId());
        }
        //更新想法的评论数
        Thought thought = thoughtService.getThoughtById(null,comment.getCommentThoughtId());
        thoughtService.updateCommentCount(thought.getThoughtId());
    }

    //编辑评论页面显示
    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editCommentView(@PathVariable("id") Integer id) throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        CommentCustom commentCustom =  commentService.getCommentById(id);
        modelAndView.addObject("commentCustom",commentCustom);

        modelAndView.setViewName("Admin/Comment/edit");
        return modelAndView;
    }


    //编辑评论提交
    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String editCommentSubmit(Comment comment) throws Exception {
        commentService.updateComment(comment);
        Integer id = comment.getCommentId();
        //如果是屏蔽评论
        if(comment.getCommentStatus()==0) {
            //屏蔽其子评论
            List<Comment> childCommentList = commentService.listChildComment(id);
            for(int i=0;i<childCommentList.size();i++) {
                Comment childComment = childCommentList.get(i);
                childComment.setCommentStatus(0);
                commentService.updateComment(childComment);
            }
        }
        //如果是批准评论
        else {
            //批准其子评论
            List<Comment> childCommentList = commentService.listChildComment(id);
            for(int i=0;i<childCommentList.size();i++) {
                Comment childComment = childCommentList.get(i);
                childComment.setCommentStatus(1);
                commentService.updateComment(childComment);
            }
        }
        return "redirect:/admin/comment";
    }

    //批准评论
    @RequestMapping(value = "/approve/{id}")
    public void approveComment(@PathVariable("id") Integer id) throws Exception {
        Comment comment = commentService.getCommentById(id);
        //批准评论
        comment.setCommentStatus(1);
        commentService.updateComment(comment);
        //批准其子评论
        List<Comment> childCommentList = commentService.listChildComment(id);
        for(int i=0;i<childCommentList.size();i++) {
            Comment childComment = childCommentList.get(i);
            childComment.setCommentStatus(1);
            commentService.updateComment(childComment);
        }

        //更新想法的评论数
        Thought thought = thoughtService.getThoughtById(null,comment.getCommentThoughtId());
        thoughtService.updateCommentCount(thought.getThoughtId());
    }
    //屏蔽评论
    @RequestMapping(value = "/hide/{id}")
    public void hideComment(@PathVariable("id") Integer id) throws Exception {
        Comment comment = commentService.getCommentById(id);
        //屏蔽评论
        comment.setCommentStatus(0);
        commentService.updateComment(comment);
        //屏蔽其子评论
        List<Comment> childCommentList = commentService.listChildComment(id);
        for(int i=0;i<childCommentList.size();i++) {
            Comment childComment = childCommentList.get(i);
            childComment.setCommentStatus(0);
            commentService.updateComment(childComment);
        }
        //更新想法的评论数
        Thought thought = thoughtService.getThoughtById(null,comment.getCommentThoughtId());
        thoughtService.updateCommentCount(thought.getThoughtId());
    }

    //回复评论页面显示
    @RequestMapping(value = "/reply/{id}")
    public ModelAndView replyCommentView(@PathVariable("id") Integer id) throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        CommentCustom commentCustom =  commentService.getCommentById(id);
        modelAndView.addObject("commentCustom",commentCustom);

        modelAndView.setViewName("Admin/Comment/reply");
        return modelAndView;
    }

    //回复评论提交
    @RequestMapping(value = "/replySubmit",method = RequestMethod.POST)
    public String replyCommentSubmit(HttpServletRequest request,Comment comment) throws Exception {
        //想法评论数+1
        Thought thought = thoughtService.getThoughtById(null,comment.getCommentThoughtId());
        thought.setThoughtCommentCount(thought.getThoughtCommentCount()+1);
        thoughtService.updateThought(thought.getThoughtId(),thought);
        //添加评论
        comment.setCommentCreateTime(new Date());
        comment.setCommentStatus(1);
        commentService.insertComment(request,comment);
        return "redirect:/admin/comment";
    }

}
