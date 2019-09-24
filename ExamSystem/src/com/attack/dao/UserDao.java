package com.attack.dao;

import org.hibernate.Session;

import com.attack.entity.user.Student;
import com.attack.entity.user.User;

public interface UserDao {


	public User queryById(int id) ;
	
	public void deleteById(int id) ;
	
	public void update(User user);
	
	public void save(User user);
	

	public User login(User user) ;

	public Student queryStudentbyUserId(int user_id);
}
