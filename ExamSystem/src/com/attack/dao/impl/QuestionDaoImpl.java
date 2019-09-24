package com.attack.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.attack.dao.QuestionDao;
import com.attack.entity.basicinfo.College;
import com.attack.entity.questioninfo.QuestionBank;
import com.attack.entity.questioninfo.QuestionBank;
import com.mysql.jdbc.StringUtils;

@Repository
public class QuestionDaoImpl implements QuestionDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	
	@Override
	public Map<String, Object> queryQuestionPageInfo(int limit, int offset, String searchBank, String searchText) {
		Session session = this.getSession();
		Map<String, Object> map = new HashMap<String, Object>();
		if(!StringUtils.isNullOrEmpty(searchText)) {
			if("id".equals(searchBank)) {
				String sql = "from QuestionBank where id = ?0";
				int id = Integer.parseInt(searchText);
				List<QuestionBank> questionList = session.createQuery(sql).setParameter(0, id).getResultList();
				
				String sql2 = "select count(id) from QuestionBank where id = ?0";
				int totalRecord = Integer.parseInt(session.createQuery(sql2).setParameter(0,id).uniqueResult().toString());
				
				map.put("total", totalRecord);
				map.put("data", questionList);
			}else if("type".equals(searchBank)) {
				String sql = "from QuestionBank where questionType.questionType_name like ?0";
				Query query = session.createQuery(sql);
				query.setParameter(0,"%"+searchText+"%");
				query.setMaxResults(limit);
				query.setFirstResult(offset);
				List<QuestionBank> questionList = query.getResultList();
				
				String sql2 = "select count(*) from uestionType.questionType_name like ?0";
				int totalRecord = Integer.parseInt(session.createQuery(sql2).setParameter(0,"%"+searchText+"%").uniqueResult().toString());
				
				map.put("total", totalRecord);
				map.put("data", questionList);				
				
			}
		}else {
			String sql = "from QuestionBank";
			Query query = session.createQuery(sql);
			query.setFirstResult(offset);
			query.setMaxResults(limit);
			List<College> questionList = query.list();
			
			String sql2 = "select count(*) from QuestionBank";
			int totalRecord = Integer.parseInt(session.createQuery(sql2).uniqueResult().toString());
			
			map.put("total", totalRecord);
			map.put("data", questionList);			
		}
		return map;
	}

	
	//根据id查询
		public QuestionBank queryById(int id) {
			Session session = this.getSession();
			QuestionBank questionBank = session.get(QuestionBank.class, id);
			return questionBank;
		}
		
		//根据id删除
		public void deleteById(int id) {
			Session session = this.getSession();
			QuestionBank questionBank = this.queryById(id);
			session.delete(questionBank);
		}
		
		
		//修改
		public void update(QuestionBank questionBank) {
			Session session = this.getSession();
			session.update(questionBank);	
		}
		
		//新增
		public void save(QuestionBank questionBank) {
			Session session = this.getSession();
			session.save(questionBank);
		}
		
		@Override
		public List<QuestionBank> getQuestionsBySubject(int subject_id) {
			String hql = " from QuestionBank where sBJKnowledgePoint.subject.id = ?0 and questionType.questionType_id = 1 ORDER BY RAND()  " ;
			List<QuestionBank> questionList = this.getSession().createQuery(hql).setParameter(0, subject_id).setMaxResults(10).getResultList();
			String hql2 = " from QuestionBank where sBJKnowledgePoint.subject.id = ?0 and questionType.questionType_id = 2 ORDER BY RAND()  " ;
			List<QuestionBank> questionList2 = this.getSession().createQuery(hql2).setParameter(0, subject_id).setMaxResults(10).getResultList();
			String hql3 = " from QuestionBank where sBJKnowledgePoint.subject.id = ?0 and questionType.questionType_id = 5 ORDER BY RAND()  " ;
			List<QuestionBank> questionList3 = this.getSession().createQuery(hql3).setParameter(0, subject_id).setMaxResults(2).getResultList();
			questionList.addAll(questionList2);
			questionList.addAll(questionList3);		
			return questionList;
		}
		
		
}
