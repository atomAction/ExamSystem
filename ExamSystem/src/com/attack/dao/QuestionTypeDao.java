package com.attack.dao;

import com.attack.entity.questioninfo.QuestionType;

public interface QuestionTypeDao {


	public QuestionType queryById(int id);
	public void deleteById(int id) ;
	public void update(QuestionType questionType);
	public void save(QuestionType questionType);
}
