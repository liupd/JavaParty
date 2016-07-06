package org.zp.tent.entity.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.zp.tent.entity.domain.User;
import org.zp.tent.entity.service.UserServiceI;

@Controller
@RequestMapping("/entity/user")
public class UserController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserServiceI userService;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public void getAllUsers(HttpServletRequest request, HttpServletResponse response) {
		// 获取所有的用户信息
		List<User> lstUsers = userService.getAllUser();
		request.setAttribute("lstUsers", lstUsers);
		try {
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} catch (ServletException e) {
			log.error(e.getMessage());
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}
}