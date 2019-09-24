package com.attack.service;

import java.util.List;
import java.util.Map;

import com.attack.entity.exam.Exam;
import com.attack.entity.exam.StudentExam;
import com.attack.entity.user.Student;

public interface StudentExamService {



	public List<Exam> queryAllExam();

	public Exam getStudentExam(int examId);

	public Map<String, Object> saveStudentExam(Student student, Map<String,String> map1);

	public Map<String, Object> getStudentExamForStudentInfo(int limit, int offset, int stu_id, String searchText);

	public Map<String, Object> getStudentExamForTeacherInfo(int limit, int offset, String teacher_name,
			String searchText);

	public void updateStudentExamScore(int studentExam_id, int getscore);

	public Map<String, Object> getStudentExamForAdminInfo(int limit, int offset, String searchText);

	

}
