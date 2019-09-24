package com.attack.dao;

import java.util.List;

import com.attack.entity.questioninfo.SBJKnowledgePoint;
import com.attack.entity.questioninfo.Subject;

public interface SubjectDao {

	public Subject queryById(int id);
	public void deleteById(int id);
	public void update(Subject subject);

	public void save(Subject subject);

	public List<Subject> queryAll();
	public List<SBJKnowledgePoint> querySBJKonwledgePoint(int subject_id);
	public SBJKnowledgePoint qureyPointById(int point_id);
}
