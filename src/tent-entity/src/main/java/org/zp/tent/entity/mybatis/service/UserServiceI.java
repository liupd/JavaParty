package org.zp.tent.entity.mybatis.service;

import java.util.List;

import org.zp.tent.entity.mybatis.domain.User;

public interface UserServiceI {
	void addUser(User user);

	User getUserById(String userId);

	List<User> getAllUser();
}