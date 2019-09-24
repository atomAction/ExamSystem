package com.attack.service;

import java.util.Map;

import com.attack.entity.questioninfo.QuestionBank;

public interface QuestionService {


	public Map<String, Object> queryQuestionPageInfo(int limit, int offset, String searchType, String searchText);

	public void saveQuestionBank(QuestionBank questionBank, int questionType_id, int point_id);

	public void updateQuestionBank(QuestionBank questionBank, int questionType_id, int point_id);

}
