package com.attack.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.attack.dao.ExamDao;
import com.attack.dao.ExamGroupDao;
import com.attack.dao.StudentExamDao;
import com.attack.entity.exam.Exam;
import com.attack.entity.exam.ExamGroup;
import com.attack.entity.exam.StudentExam;
import com.attack.entity.questioninfo.QuestionBank;
import com.attack.entity.user.Student;
import com.attack.service.StudentExamService;
import com.mysql.fabric.xmlrpc.base.Array;


@Service
@Transactional
public class StudentExamServiceImpl implements StudentExamService {

	@Autowired
	private StudentExamDao studentExamDao;
	
	@Autowired
	private ExamDao examDao;
	@Autowired
	private ExamGroupDao examGroupDao;

	@Override
	public List<Exam> queryAllExam() {
		// TODO Auto-generated method stub
		return examDao.queryAllExam();
	}



	@Override
	public Exam getStudentExam(int examId) {
		// TODO Auto-generated method stub
	//	StudentExam studentExam = new StudentExam();
		Exam exam = examDao.queryById(examId);

//		
//		for(QuestionBank q : lis) {
//			q.setAnswer(null);
//		}

		return exam;
	}



	@Override
	public Map<String, Object> saveStudentExam(Student student, Map<String,String> map1) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		//
		Map<Integer,String> map2 = new HashMap<Integer,String>();
		 for (Entry<String, String> entry : map1.entrySet()) {
			    //Map.entry<Integer,String> 映射项（键-值对）  有几个方法：用上面的名字entry
			    //entry.getKey() ;entry.getValue();   entry.setValue();
		           //map.entrySet()  返回此映射中包含的映射关系的 Set视图。
			 String nowkey = entry.getKey().replaceAll("\"","");
			 String nowvalue = entry.getValue().replaceAll("\"","");
			 int a = Integer.parseInt(nowkey);
			 map2.put(a, nowvalue);
		 }
		
		int examId = Integer.parseInt( map2.get(-1));
		int examGroupId = Integer.parseInt( map2.get(-2));
		int singleScore = 0;
		Exam exam  = examDao.queryById(examId);
		ExamGroup examGroup = examGroupDao.queryById(examGroupId);
		
		List<QuestionBank> questionsList = exam.getQuestions();
		for(int i=0;i<questionsList.size();i++) {
			String answer = questionsList.get(i).getAnswer();
			String answer1 = map2.get(i);
			int thisScore = questionsList.get(i).getQuestionType().getGrade();
			//String answer =  "\"" + ans + "\"" ;
			if(answer.equals(answer1)) {
				singleScore = singleScore + thisScore;
			}	
		}
		
		System.out.println(singleScore);
		
		StudentExam studentExam = new StudentExam();
		studentExam.setStudent(student);
		studentExam.setAnswerMap(map1);
		studentExam.setExam(exam);
		studentExam.setSingleScore(singleScore);
		studentExam.setExamGroup(examGroup);
		studentExam.setExamDate(new Date());
		
		studentExamDao.save(studentExam);
		
		map.put("singleScore", singleScore);
		return map;
	}



	@Override
	public Map<String, Object> getStudentExamForStudentInfo(int limit, int offset, int stu_id, String searchText) {
		// TODO Auto-generated method stub
		return studentExamDao.getExamGroupForTeacherInfo(limit,offset,stu_id,searchText);
	}



	@Override
	public Map<String, Object> getStudentExamForTeacherInfo(int limit, int offset, String teacher_name,
			String searchText) {
		// TODO Auto-generated method stub
		return studentExamDao.getStudentExamForTeacherInfo(limit, offset, teacher_name,searchText);
	}



	@Override
	public void updateStudentExamScore(int studentExam_id, int getscore) {
		// TODO Auto-generated method stub
		StudentExam studentExam = studentExamDao.queryById(studentExam_id);
		studentExam.setMoreScore(getscore);
		studentExam.setScore(getscore + studentExam.getSingleScore());
		studentExamDao.update(studentExam);
	}



	@Override
	public Map<String, Object> getStudentExamForAdminInfo(int limit, int offset, String searchText) {
		// TODO Auto-generated method stub
		return studentExamDao.getStudentExamForAdminInfo(limit, offset,searchText);
	}
	
	
	
}
