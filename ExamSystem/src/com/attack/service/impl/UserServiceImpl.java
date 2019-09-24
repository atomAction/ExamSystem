package com.attack.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.attack.dao.UserDao;
import com.attack.entity.user.Student;
import com.attack.entity.user.User;
import com.attack.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	
	public User login(User user) {
		
		return userDao.login(user);
		
	}


	@Override
	public Student queryStudentbyUserId(int user_id) {
		
		return userDao.queryStudentbyUserId(user_id);
	}
	
	
}
