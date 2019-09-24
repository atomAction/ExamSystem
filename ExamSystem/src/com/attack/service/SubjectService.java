package com.attack.service;

import java.util.List;

import com.attack.entity.questioninfo.SBJKnowledgePoint;
import com.attack.entity.questioninfo.Subject;

public interface SubjectService {

	
	public List<Subject> queryAllSubject();

	public List<SBJKnowledgePoint> querySBJKonwledgePoint(int subject_id);
}
