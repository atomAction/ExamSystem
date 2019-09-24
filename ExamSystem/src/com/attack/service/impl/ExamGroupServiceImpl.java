package com.attack.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.attack.dao.ExamDao;
import com.attack.dao.ExamGroupDao;
import com.attack.dao.StudentExamDao;
import com.attack.dao.SubjectDao;
import com.attack.entity.exam.Exam;
import com.attack.entity.exam.ExamGroup;
import com.attack.entity.questioninfo.Subject;
import com.attack.entity.user.Student;
import com.attack.service.ExamGroupService;
@Service
@Transactional
public class ExamGroupServiceImpl implements ExamGroupService {

	@Autowired
	private ExamGroupDao examGroupDao;
	@Autowired
	private SubjectDao subjectDao;
	@Autowired
	private StudentExamDao studentExamDao;
	@Autowired 
	private ExamDao examDao;
	@Override
	public Map<String, Object> getExamGroupInfo(int limit, int offset, String searchType, String searchText, Student student) {
		int student_id = student.getStu_id();
		
		return examGroupDao.queryPageInfo(limit,offset,searchType,searchText);
	}

	@Override
	public Map<String, Object> getExamGroupForTeacherInfo(int limit, int offset, String searchType, String searchText) {
		// TODO Auto-generated method stub
		return examGroupDao.getExamGroupForTeacherInfo(limit,offset,searchType,searchText);
	}

	@Override
	public Map<String, Object> queryAllSubjectAndExam() {
		// TODO Auto-generated method stub
		List<Subject> subjectList = subjectDao.queryAll();
		List<Exam> examList = examDao.queryAllExam();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("subjectList", subjectList);
		map.put("examList",examList);
		return map;
	}

	@Override
	public void saveExamGroup(ExamGroup examGroup) {
		// TODO Auto-generated method stub
	//	examGroup.setSubject_name(subjectDao.queryById(subject_id).getName());
		examGroupDao.save(examGroup);
	}

	@Override
	public void updateExamGroupById(ExamGroup examGroup) {
		examGroupDao.update(examGroup);
		
	}

	@Override
	public void deleteExamGroupById(int examGroup_id) {
		// TODO Auto-generated method stub
		examGroupDao.deleteById(examGroup_id);
	}

	@Override
	public Map<String, Object> getExamGroupForAdminInfo(int limit, int offset, String searchText) {
		// TODO Auto-generated method stub
		return examGroupDao.getExamGroupForAdminInfo(limit,offset,searchText);
	}



	
	
	
}
