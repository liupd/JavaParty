package com.zp.tent.entity.service;

import java.util.List;

import com.zp.tent.entity.domain.User;

public interface UserServiceI {
	void addUser(User user);

	User getUserById(String userId);

	List<User> getAllUser();
}