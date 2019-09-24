package com.attack.dao;

import java.util.Map;

import org.hibernate.Session;

import com.attack.entity.user.Administrator;
import com.attack.entity.user.Student;
import com.attack.entity.user.Teacher;

public interface UserInfoDao {


	public Student queryStudentById(int stu_id);
	public void deleteStudentById(int id) ;
	public void updateStudent(Student Student);
	public void saveStudent(Student Student) ;
	public Teacher queryTeacherById(int id);
	public void deleteTeacherById(int id) ;
	public void updateTeachaer(Teacher Teacher) ;
	public void saveTeachaer(Teacher Teacher) ;
	public Administrator queryAdminById(int id);
	public void deleteAdminById(int id) ;
	public void updateAdmin(Administrator admin);
	public void saveAdmin(Administrator Admin);
	public Map<String, Object> queryStudentPageInfo(int limit, int offset, String searchType, String searchText);
	public Map<String, Object> queryTeacherPageInfo(int limit, int offset, String searchType, String searchText);

}
