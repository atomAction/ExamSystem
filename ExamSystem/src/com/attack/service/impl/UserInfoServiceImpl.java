package com.attack.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.attack.dao.ClassDao;
import com.attack.dao.UserInfoDao;
import com.attack.entity.basicinfo.Class;
import com.attack.entity.user.Administrator;
import com.attack.entity.user.Student;
import com.attack.entity.user.Teacher;
import com.attack.entity.user.User;
import com.attack.service.UserInfoService;

@Service
@Transactional
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoDao userInfoDao;

	@Autowired
	private ClassDao classDao;

	@Override
	public Map<String, Object> queryStudentInfo() {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String, Object>();
		List<Class> classList = classDao.queryAll();
		map.put("classList", classList);
		return map;
	}

	@Override
	public void updateStudentInfon(Student student1, int class_id) {
		// TODO Auto-generated method stub
		Class classs = classDao.queryById(class_id);
		student1.setClass_id(classs);
		userInfoDao.updateStudent(student1);
	}

	@Override
	public void updateStudentInfon(Teacher teacher1) {
		// TODO Auto-generated method stub
		userInfoDao.updateTeachaer(teacher1);
	}

	@Override
	public void updateAdminInfon(Administrator admin1) {
		// TODO Auto-generated method stub
		userInfoDao.updateAdmin(admin1);
	}

	@Override
	public Map<String, Object> queryStudentPageInfo(int limit, int offset, String searchType, String searchText) {
		// TODO Auto-generated method stub
		return userInfoDao.queryStudentPageInfo(limit,offset, searchType,searchText);
	}

	@Override
	public Map<String, Object> getTeacherPageInfo(int limit, int offset, String searchType, String searchText) {
		// TODO Auto-generated method stub
		return userInfoDao.queryTeacherPageInfo(limit,offset, searchType,searchText);
	}

	@Override
	public void deleteStudentById(int student_id) {
		// TODO Auto-generated method stub
		userInfoDao.deleteStudentById(student_id);
	}

	@Override
	public Map<String, Object> queryStudentById(int student_id) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		List<Class> classList = classDao.queryAll();
		Student student = userInfoDao.queryStudentById(student_id);
		map.put("student", student);
		map.put("classList", classList);
		return map;
	}

	@Override
	public void updateStudentById(int classid, User user, Student student) {
		// TODO Auto-generated method stub
		Class calss = classDao.queryById(classid);
		user.setUser_type("student");
		student.setUser_id(user);
		student.setClass_id(calss);
		userInfoDao.updateStudent(student);
	}

	@Override
	public void saveStudent(int classid, User user, Student student) {
		// TODO Auto-generated method stub
		Class calss = classDao.queryById(classid);
		user.setUser_type("student");
		student.setUser_id(user);
		student.setClass_id(calss);
		userInfoDao.saveStudent(student);
	}

	@Override
	public void deleteTeacherById(int teacher_id) {
		// TODO Auto-generated method stub
		userInfoDao.deleteTeacherById(teacher_id);
	}

	@Override
	public Map<String, Object> queryTeacherById(int teacher_id) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		Teacher teacher = userInfoDao.queryTeacherById(teacher_id);
		map.put("teacher", teacher);
		return map;
	}

	@Override
	public void updateTeacherById(User user, Teacher teacher) {
		// TODO Auto-generated method stub
		user.setUser_type("teacher");
		teacher.setUser_id(user);
		userInfoDao.updateTeachaer(teacher);
	}

	@Override
	public void saveTeacher(User user, Teacher teacher) {
		// TODO Auto-generated method stub
		user.setUser_type("teacher");
		teacher.setUser_id(user);
		userInfoDao.saveTeachaer(teacher);
	}
	
}
