package com.attack.service;

import java.util.Map;

import com.attack.entity.user.Administrator;
import com.attack.entity.user.Student;
import com.attack.entity.user.Teacher;
import com.attack.entity.user.User;

public interface UserInfoService {

	public Map<String, Object> queryStudentInfo();

	public void updateStudentInfon(Student student1, int class_id);

	public void updateStudentInfon(Teacher teacher1);

	public void updateAdminInfon(Administrator admin1);

	public Map<String, Object> queryStudentPageInfo(int limit, int offset, String searchType, String searchText);

	public Map<String, Object> getTeacherPageInfo(int limit, int offset, String searchType, String searchText);

	public void deleteStudentById(int student_id);

	public Map<String, Object> queryStudentById(int student_id);

	public void updateStudentById(int classid, User user, Student student);

	public void saveStudent(int classid, User user, Student student);

	public void deleteTeacherById(int teacher_id);

	public Map<String, Object> queryTeacherById(int teacher_id);

	public void updateTeacherById(User user, Teacher teacher);

	public void saveTeacher(User user, Teacher teacher);

}
