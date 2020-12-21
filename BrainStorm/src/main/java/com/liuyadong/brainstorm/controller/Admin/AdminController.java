package com.liuyadong.brainstorm.controller.Admin;

import com.liuyadong.brainstorm.entity.User;
import com.liuyadong.brainstorm.entity.custom.ThoughtListVo;
import com.liuyadong.brainstorm.entity.custom.CommentListVo;
import com.liuyadong.brainstorm.service.ThoughtService;
import com.liuyadong.brainstorm.service.CommentService;
import com.liuyadong.brainstorm.service.UserService;
import com.liuyadong.brainstorm.util.Functions;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private ThoughtService thoughtService;

    @Autowired
    private CommentService commentService;


    @ModelAttribute
    public void init(Model model) throws Exception {

    }

    //创作者中心首页
    @RequestMapping("/admin")
    public ModelAndView index() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        //想法列表
        List<ThoughtListVo> thoughtCustomList = thoughtService.listThought(null);
        modelAndView.addObject("thoughtCustomList",thoughtCustomList);
        //评论列表
        List<CommentListVo> commentListVoList = commentService.listCommentVo(null);
        modelAndView.addObject("commentListVoList",commentListVoList);
        //评论数
        Integer allCommentCount = commentService.countComment(null);
        Integer approvedCommentCount = commentService.countComment(1);
        Integer hiddenCommentCount = commentService.countComment(0);
        modelAndView.addObject("allCommentCount",allCommentCount);
        modelAndView.addObject("approvedCommentCount",approvedCommentCount);
        modelAndView.addObject("hiddenCommentCount",hiddenCommentCount);

        modelAndView.setViewName("/Admin/index");
        return modelAndView;
    }

    //登录页面显示
    @RequestMapping("/login")
    public ModelAndView loginView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/Admin/login");
        return modelAndView;
    }

    //登录验证
    @RequestMapping(value = "/loginVerify",method = RequestMethod.POST)
    @ResponseBody
    public String loginVerify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberme = request.getParameter("rememberme");
        User user = userService.getUserByNameOrEmail(username);
        if(user==null) {
            map.put("code",0);
            map.put("msg","用户名无效！");
        } else if(!user.getUserPass().equals(password)) {
            map.put("code",0);
            map.put("msg","密码错误！");
        } else {
            //登录成功
            map.put("code",1);
            map.put("msg","");
            //添加session
            request.getSession().setAttribute("user", user);
            //添加cookie
            if(rememberme!=null) {
                //创建两个Cookie对象
                Cookie nameCookie = new Cookie("username", username);
                //设置Cookie的有效期为3天
                nameCookie.setMaxAge(60 * 60 * 24 * 3);
                Cookie pwdCookie = new Cookie("password", password);
                pwdCookie.setMaxAge(60 * 60 * 24 * 3);
                response.addCookie(nameCookie);
                response.addCookie(pwdCookie);
            }
            user.setUserLastLoginTime(new Date());
            user.setUserLastLoginIp(Functions.getIpAddr(request));
            userService.updateUser(user);

        }
        String result = new JSONObject(map).toString();
        return result;
    }

    //退出登录
    @RequestMapping(value = "/admin/logout")
    public String logout(HttpSession session) throws Exception {
        session.removeAttribute("user");
        session.invalidate();
        return "redirect:/login";
    }


}
