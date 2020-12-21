package com.liuyadong.brainstorm.controller.Home;

import com.liuyadong.brainstorm.service.UserService;
import com.liuyadong.brainstorm.util.Functions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;


@Controller
public class UserController {

	@Autowired
	private  HttpServletRequest request;

	@Autowired
	private UserService userService;

	@Autowired
	private Functions functions;








}
