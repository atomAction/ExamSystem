package com.attack.dao;

import java.util.Map;

import com.attack.entity.exam.StudentExam;

public interface StudentExamDao {


	public StudentExam queryById(int id);
	public void deleteById(int id);
	public void update(StudentExam studentExam);
	public void save(StudentExam studentExam) ;
	public Map<String, Object> getExamGroupForTeacherInfo(int limit, int offset, int stu_id, String searchText);
	public Map<String, Object> getStudentExamForTeacherInfo(int limit, int offset, String teacher_name,
			String searchText);
	public Map<String, Object> getStudentExamForAdminInfo(int limit, int offset, String searchText);

}
