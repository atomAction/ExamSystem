package com.attack.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.attack.dao.SubjectDao;
import com.attack.entity.questioninfo.SBJKnowledgePoint;
import com.attack.entity.questioninfo.Subject;
import com.attack.service.SubjectService;

@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	private SubjectDao subjectDao;
	
	public List<Subject> queryAllSubject(){
		return subjectDao.queryAll();
	}


	@Override
	public List<SBJKnowledgePoint> querySBJKonwledgePoint(int subject_id) {
		// TODO Auto-generated method stub
		return subjectDao.querySBJKonwledgePoint(subject_id);
	}
	
}
