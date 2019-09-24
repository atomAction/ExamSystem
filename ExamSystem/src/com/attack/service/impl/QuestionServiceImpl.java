package com.attack.service.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.attack.dao.QuestionDao;
import com.attack.dao.QuestionTypeDao;
import com.attack.dao.SubjectDao;
import com.attack.entity.questioninfo.QuestionBank;
import com.attack.entity.questioninfo.QuestionType;
import com.attack.service.QuestionService;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {
	
	@Autowired
	private QuestionDao questionDao;
	@Autowired
	private QuestionTypeDao questionTypeDao;
	@Autowired
	private SubjectDao subjectDao;


	@Override
	public Map<String, Object> queryQuestionPageInfo(int limit, int offset, String searchType, String searchText) {
		// TODO Auto-generated method stub
		return questionDao.queryQuestionPageInfo(limit, offset, searchType, searchText);
	}



	@Override
	public void saveQuestionBank(QuestionBank questionBank, int questionType_id,int point_id) {
		// TODO Auto-generated method stub
		QuestionType questionType = questionTypeDao.queryById(questionType_id);
		Date date = new Date();
		questionBank.setQuestionType(questionType);
		questionBank.setJoinTime(date);
		questionBank.setsBJKnowledgePoint(subjectDao.qureyPointById(point_id));
		questionDao.save(questionBank);
	}



	@Override
	public void updateQuestionBank(QuestionBank questionBank, int questionType_id, int point_id) {
		QuestionType questionType = questionTypeDao.queryById(questionType_id);
		Date date = new Date();
		if(questionDao.queryById(questionBank.getId()).getJoinTime() == null)
		{
			questionBank.setJoinTime(date);
		}
		questionBank.setQuestionType(questionType);
		questionBank.setsBJKnowledgePoint(subjectDao.qureyPointById(point_id));
		questionDao.update(questionBank);
		
	}

	
}
