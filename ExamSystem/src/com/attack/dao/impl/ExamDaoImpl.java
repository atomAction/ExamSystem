package com.attack.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.attack.dao.ExamDao;
import com.attack.entity.basicinfo.College;
import com.attack.entity.exam.Exam;
import com.attack.entity.questioninfo.QuestionBank;
import com.mysql.jdbc.StringUtils;

@Repository
public class ExamDaoImpl implements ExamDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	

	//根据id查询
		public Exam queryById(int id) {
			Session session = this.getSession();
			Exam exam = session.get(Exam.class, id);
		//	session.evict(exam);
		//	session.close();
			return exam;
		}
		
		//根据id删除
		public void deleteById(int id) {
			Session session = this.getSession();
			Exam exam = this.queryById(id);
			session.delete(exam);
		}
		
		
		//修改
		public void update(Exam exam) {
			Session session = this.getSession();
			session.update(exam);	
		}
		
		//新增
		public void save(Exam exam) {
			Session session = this.getSession();
			session.save(exam);
		}
	


		@Override
		public List<Exam> queryAllExam() {
			// TODO Auto-generated method stub
			String hql = " from Exam " ;
			List<Exam> examList = this.getSession().createQuery(hql).getResultList();
			return examList;
		}


		@Override
		public Exam querySQLById(int examId) {
			String sql = "select * from exam where id = ?0";
			return  (Exam) this.getSession().createSQLQuery(sql).setParameter(0, examId).addEntity(Exam.class);
		}


		@Override
		public Map<String, Object> getExamInfo(int limit, int offset, String searchType, String searchText) {
			Session session = this.getSession();
			Map<String, Object> map = new HashMap<String, Object>();
			if(!StringUtils.isNullOrEmpty(searchText)) {
				if("id".equals(searchType)) {
					String sql = "from Exam where id = ?0";
					int id = Integer.parseInt(searchText);
					List<Exam> examList = session.createQuery(sql).setParameter(0, id).getResultList();
					
					String sql2 = "select count(id) from Exam where id = ?0";
					int totalRecord = Integer.parseInt(session.createQuery(sql2).setParameter(0,id).uniqueResult().toString());
					
					map.put("total", totalRecord);
					map.put("data", examList);
				}else if("name".equals(searchType)) {
					String sql = "from Exam where examName like ?0";
					Query query = session.createQuery(sql);
					query.setParameter(0,"%"+searchText+"%");
					query.setMaxResults(limit);
					query.setFirstResult(offset);
					List<Exam> examList = query.getResultList();
					
					String sql2 = "select count(*) from Exam where examName like ?0";
					int totalRecord = Integer.parseInt(session.createQuery(sql2).setParameter(0,"%"+searchText+"%").uniqueResult().toString());
					
					map.put("total", totalRecord);
					map.put("data", examList);				
					
				}
			}else {
				String sql = "from Exam";
				Query query = session.createQuery(sql);
				query.setFirstResult(offset);
				query.setMaxResults(limit);
				List<Exam> examList = query.list();
				
				String sql2 = "select count(*) from Exam";
				int totalRecord = Integer.parseInt(session.createQuery(sql2).uniqueResult().toString());
				
				map.put("total", totalRecord);
				map.put("data", examList);			
			}
			return map;
		}

}
