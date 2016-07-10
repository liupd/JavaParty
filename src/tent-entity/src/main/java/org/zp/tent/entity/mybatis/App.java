package org.zp.tent.entity.mybatis;

import java.util.Date;
import java.util.UUID;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.zp.tent.entity.mybatis.domain.User;
import org.zp.tent.entity.mybatis.service.UserServiceI;

public class App {
	public static void main(String[] args) {
		/**
		 * 在非Web应用中，手工加载Spring
		 * IoC容器，不能用ApplicationContext，要用AbstractApplicationContext。
		 * 用完以后要记得调用close()关闭容器。如果不记得关闭容器，最典型的问题就是数据库连接不能释放。
		 */
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext(
				new String[] { "tent-entity-spring.xml", "tent-entity-mybatis.xml" });
		UserServiceI userService = (UserServiceI) ac.getBean("userService");
		User user = new User();
		user.setUser_id(UUID.randomUUID().toString().replaceAll("-", ""));
		user.setUser_name("lisi");
		user.setUser_birthday(new Date());
		user.setUser_salary(10000D);
		userService.addUser(user);
		System.out.println("插入成功");
		ac.close();
	}
}
