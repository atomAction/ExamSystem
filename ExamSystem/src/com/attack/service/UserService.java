package com.attack.service;

import com.attack.entity.user.Student;
import com.attack.entity.user.User;

public interface UserService {

	public User login(User user);

	public Student queryStudentbyUserId(int user_id);
}
