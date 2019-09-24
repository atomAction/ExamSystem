package com.attack.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.attack.dao.SubjectDao;
import com.attack.entity.questioninfo.SBJKnowledgePoint;
import com.attack.entity.questioninfo.Subject;

@Repository
public class SubjectDaoImpl implements SubjectDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	//根据id查询
		public Subject queryById(int id) {
			Session session = this.getSession();
			Subject subject = session.get(Subject.class, id);
			return subject;
		}
		
		//根据id删除
		public void deleteById(int id) {
			Session session = this.getSession();
			Subject subject = this.queryById(id);
			session.delete(subject);
		}
		
		
		//修改
		public void update(Subject subject) {
			Session session = this.getSession();
			session.update(subject);	
		}
		
		//新增
		public void save(Subject subject) {
			Session session = this.getSession();
			session.save(subject);
		}
		
		//获取所有
		public List<Subject> queryAll(){
			String hql = "from Subject";
			List<Subject> subjectList = this.getSession().createQuery(hql).list();
			return subjectList;
		}

		@Override
		public List<SBJKnowledgePoint> querySBJKonwledgePoint(int subject_id) {
			String hql = "from SBJKnowledgePoint where subject.id = ?0";
			List<SBJKnowledgePoint> pointList = this.getSession().createQuery(hql).setParameter(0, subject_id).list();
			return pointList;
		}

		@Override
		public SBJKnowledgePoint qureyPointById(int point_id) {
			Session session = this.getSession();
			SBJKnowledgePoint sBJKnowledgePoint = session.get(SBJKnowledgePoint.class, point_id);
			return sBJKnowledgePoint;
		}
		
}
