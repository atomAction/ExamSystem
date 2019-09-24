package com.attack.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.attack.entity.exam.Exam;
import com.attack.entity.questioninfo.QuestionBank;

public interface ExamDao {


	

			public Exam queryById(int id);
			public void deleteById(int id);
			public void update(Exam Exam);
			public void save(Exam Exam) ;


			
			
			public List<Exam> queryAllExam();
			public Exam querySQLById(int examId);
			public Map<String, Object> getExamInfo(int limit, int offset, String searchType, String searchText);

}
