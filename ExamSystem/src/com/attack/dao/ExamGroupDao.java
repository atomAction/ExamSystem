package com.attack.dao;

import java.util.Map;

import org.hibernate.Session;

import com.attack.entity.exam.ExamGroup;

public interface ExamGroupDao {

	public Map<String, Object> queryPageInfo(int limit, int offset, String searchType, String searchText);

	public ExamGroup queryById(int id);
	public void deleteById(int id) ;
	public void update(ExamGroup examGroup);
	public void save(ExamGroup examGroup) ;

	public Map<String, Object> getExamGroupForTeacherInfo(int limit, int offset, String searchType, String searchText);

	public Map<String, Object> queryAllExamGroup();

	public Map<String, Object> getExamGroupForAdminInfo(int limit, int offset, String searchText);


}
