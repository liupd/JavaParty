package com.zp.tent.entity;

import java.util.Date;
import java.util.UUID;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zp.tent.entity.domain.User;
import com.zp.tent.entity.service.UserServiceI;

public class App {
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				new String[] { "tent-entity-spring.xml", "tent-entity-mybatis.xml" });
		UserServiceI userService = (UserServiceI) ac.getBean("userService");
		User user = new User();
		user.setUser_id(UUID.randomUUID().toString().replaceAll("-", ""));
		user.setUser_name("lisi");
		user.setUser_birthday(new Date());
		user.setUser_salary(10000D);
		userService.addUser(user);
		System.out.println("插入成功");
	}

}
