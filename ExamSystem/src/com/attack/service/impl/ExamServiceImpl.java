package com.attack.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.attack.dao.ExamDao;
import com.attack.dao.ExamGroupDao;
import com.attack.dao.QuestionDao;
import com.attack.entity.exam.Exam;
import com.attack.entity.exam.ExamGroup;
import com.attack.entity.questioninfo.QuestionBank;
import com.attack.service.ExamService;
@Service
@Transactional
public class ExamServiceImpl implements ExamService {

	@Autowired
	private ExamDao examDao;
	@Autowired
	private QuestionDao questionDao;
	@Autowired
	private ExamGroupDao examGroupDao;

	@Override
	public void saveExam(List<QuestionBank> questionList) {
		Exam exam = new Exam();
		String examName = questionList.get(1).getsBJKnowledgePoint().getSubject().getName()+"考试试卷";
		exam.setExamName(examName);
		exam.setJoinDate(new Date());
		exam.setQuestions(questionList);
		examDao.save(exam);
	}

	@Override
	public List<QuestionBank> getQuestionsBySubject(int subject_id) {
		return questionDao.getQuestionsBySubject(subject_id);
	}



	@Override
	public Map<String, Object> getExamInfo(int limit, int offset, String searchType, String searchText) {
		// TODO Auto-generated method stub
		return examDao.getExamInfo(limit,offset,searchType,searchText);
	}



	@Override
	public Map<String, Object> queryAllExamGroup() {
		// TODO Auto-generated method stub
		
		return examGroupDao.queryAllExamGroup();
	}



	@Override
	public void updateExamById(int exam_id, String ab_select, int examGroup_id) {
		// TODO Auto-generated method stub
		Exam exam = examDao.queryById(exam_id);
		ExamGroup examGroup = examGroupDao.queryById(examGroup_id);
		if(ab_select.equals("A")) {
			examGroup.setExam_A_id(exam.getId());
		}else if(ab_select.equals("B")){
			examGroup.setExam_B_id(exam.getId());
		}
		examGroupDao.update(examGroup);
	}

	@Override
	public void deleteExamById(int id) {
		// TODO Auto-generated method stub
		examDao.deleteById(id);
	}

}
