package com.attack.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.attack.dao.QuestionTypeDao;
import com.attack.entity.questioninfo.QuestionType;
@Repository
public class QuestionTypeDaoImpl implements QuestionTypeDao {


	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	//根据id查询
	public QuestionType queryById(int id) {
		Session session = this.getSession();
		QuestionType questionType = session.get(QuestionType.class, id);
		return questionType;
	}
	
	//根据id删除
	public void deleteById(int id) {
		Session session = this.getSession();
		QuestionType questionType = this.queryById(id);
		session.delete(questionType);
	}
	
	
	//修改
	public void update(QuestionType questionType) {
		Session session = this.getSession();
		session.update(questionType);	
	}
	
	//新增
	public void save(QuestionType questionType) {
		Session session = this.getSession();
		session.save(questionType);
	}
}
