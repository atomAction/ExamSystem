package com.attack.dao;

import java.util.List;
import java.util.Map;

import com.attack.entity.questioninfo.QuestionBank;

public interface QuestionDao {

	public Map<String, Object> queryQuestionPageInfo(int limit, int offset, String searchType, String searchText);

	public QuestionBank queryById(int id);
	public void deleteById(int id) ;
	public void update(QuestionBank questionBank);
	public void save(QuestionBank questionBank);
	
	public List<QuestionBank> getQuestionsBySubject(int subject_id);
}
