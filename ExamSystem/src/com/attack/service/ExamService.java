package com.attack.service;

import java.util.List;
import java.util.Map;

import com.attack.entity.questioninfo.QuestionBank;

public interface ExamService {

	

	public void saveExam(List<QuestionBank> questionList);

	public List<QuestionBank> getQuestionsBySubject(int subject_id);

	public Map<String, Object> getExamInfo(int limit, int offset, String searchType, String searchText);

	public Map<String, Object> queryAllExamGroup();

	public void updateExamById(int exam_id, String ab_select, int examGroup_id);

	public void deleteExamById(int id);

	

}
