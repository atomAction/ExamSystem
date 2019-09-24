package com.attack.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.attack.dao.ExamGroupDao;
import com.attack.entity.exam.ExamGroup;
import com.mysql.jdbc.StringUtils;


@Repository
public class ExamGroupDaoImpl implements ExamGroupDao {

	@Autowired
	private SessionFactory sessionFactory;

	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	//根据id查询
			public ExamGroup queryById(int id) {
				Session session = this.getSession();
				ExamGroup examGroup = session.get(ExamGroup.class, id);
				return examGroup;
			}
			
			//根据id删除
			public void deleteById(int id) {
				Session session = this.getSession();
				ExamGroup examGroup = this.queryById(id);
				session.delete(examGroup);
			}
			
			
			//修改
			public void update(ExamGroup examGroup) {
				Session session = this.getSession();
				session.update(examGroup);	
			}
			
			//新增
			public void save(ExamGroup examGroup) {
				Session session = this.getSession();
				session.save(examGroup);
			}
			
			
			/**
			 * 分页查询
			 * @param limit
			 * @param offset
			 * @param searchType
			 * @param searchText
			 * @return
			 */
			public Map<String, Object> queryPageInfo(int limit, int offset,String searchType,String searchText) {
				Session session = this.getSession();
				Map<String, Object> map = new HashMap<String, Object>();
				if(!StringUtils.isNullOrEmpty(searchText)) {
					if("id".equals(searchType)) {
						String sql = "from ExamGroup where id = ?0";
						int id = Integer.parseInt(searchText);
						List<ExamGroup> examGroupList = session.createQuery(sql).setParameter(0, id).getResultList();
						
						String sql2 = "select count(id) from Class where id = ?0";
						int totalRecord = Integer.parseInt(session.createQuery(sql2).setParameter(0,id).uniqueResult().toString());
						
						map.put("total", totalRecord);
						map.put("data", examGroupList);
					}else if("name".equals(searchType)) {
						String sql = "from ExamGroup where name like ?0";
						Query query = session.createQuery(sql);
						query.setParameter(0,"%"+searchText+"%");
						query.setMaxResults(limit);
						query.setFirstResult(offset);
						List<ExamGroup> examGroupList = query.getResultList();
						
						String sql2 = "select count(*) from ExamGroup where name like ?0";
						int totalRecord = Integer.parseInt(session.createQuery(sql2).setParameter(0,"%"+searchText+"%").uniqueResult().toString());
						
						map.put("total", totalRecord);
						map.put("data", examGroupList);				
						
					}else if("subject".equals(searchType)) {
						String sql = "from ExamGroup where subject_name like ?0";
						Query query = session.createQuery(sql);
						query.setParameter(0,"%"+searchText+"%");
						query.setMaxResults(limit);
						query.setFirstResult(offset);
						List<ExamGroup> examGroupList = query.getResultList();
						
						String sql2 = "select count(*) from ExamGroup where subject_name like ?0";
						int totalRecord = Integer.parseInt(session.createQuery(sql2).setParameter(0,"%"+searchText+"%").uniqueResult().toString());
						
						map.put("total", totalRecord);
						map.put("data", examGroupList);				
						
					}
				}else {
					String sql = "from ExamGroup";
					Query query = session.createQuery(sql);
					query.setFirstResult(offset);
					query.setMaxResults(limit);
					List<ExamGroup> examGroupList = query.list();
					
					String sql2 = "select count(*) from ExamGroup";
					int totalRecord = Integer.parseInt(session.createQuery(sql2).uniqueResult().toString());
					
					map.put("total", totalRecord);
					map.put("data", examGroupList);			
				}
				return map;
			}

			@Override
			public Map<String, Object> getExamGroupForTeacherInfo(int limit, int offset, String teacher_name,
					String searchText) {
				Session session = this.getSession();
				Map<String, Object> map = new HashMap<String, Object>();
				if(!StringUtils.isNullOrEmpty(searchText)) {
					
						String sql = "from ExamGroup where name like ?0 and teacher_name = ?1";
						Query query = session.createQuery(sql);
						query.setParameter(0,"%"+searchText+"%");
						query.setParameter(1, teacher_name);
						query.setMaxResults(limit);
						query.setFirstResult(offset);
						List<ExamGroup> examGroupList = query.getResultList();
						
						String sql2 = "select count(*) from ExamGroup where name like ?0 and teacher_name = ?1";
						int totalRecord = Integer.parseInt(session.createQuery(sql2).setParameter(0,"%"+searchText+"%")
								.setParameter(1, teacher_name)
								.uniqueResult().toString());
						
						map.put("total", totalRecord);
						map.put("data", examGroupList);					
					
				}else {
					String sql = "from ExamGroup where teacher_name = ?0";
					Query query = session.createQuery(sql);
					query.setParameter(0, teacher_name);
					query.setFirstResult(offset);
					query.setMaxResults(limit);
					List<ExamGroup> examGroupList = query.list();
					
					String sql2 = "select count(*) from ExamGroup where teacher_name = ?0";
					int totalRecord = Integer.parseInt(session.createQuery(sql2)
							.setParameter(0, teacher_name).uniqueResult().toString());
					
					map.put("total", totalRecord);
					map.put("data", examGroupList);			
				}
				return map;
			}

			@Override
			public Map<String, Object> queryAllExamGroup() {
				Map<String, Object> map = new HashMap<String,Object>();
				String hql = "from ExamGroup";
				List<ExamGroup> examGroupList = this.getSession().createQuery(hql).list();
				map.put("examGroupList", examGroupList);
				return map;
			}

			@Override
			public Map<String, Object> getExamGroupForAdminInfo(int limit, int offset, String searchText) {
				// TODO Auto-generated method stub
				Session session = this.getSession();
				Map<String, Object> map = new HashMap<String, Object>();
				if(!StringUtils.isNullOrEmpty(searchText)) {
					
						String sql = "from ExamGroup where name like ?0 ";
						Query query = session.createQuery(sql);
						query.setParameter(0,"%"+searchText+"%");
						query.setMaxResults(limit);
						query.setFirstResult(offset);
						List<ExamGroup> examGroupList = query.getResultList();
						
						String sql2 = "select count(*) from ExamGroup where name like ?0";
						int totalRecord = Integer.parseInt(session.createQuery(sql2).setParameter(0,"%"+searchText+"%")
								.uniqueResult().toString());
						
						map.put("total", totalRecord);
						map.put("data", examGroupList);					
					
				}else {
					String sql = "from ExamGroup";
					Query query = session.createQuery(sql);
					query.setFirstResult(offset);
					query.setMaxResults(limit);
					List<ExamGroup> examGroupList = query.list();
					
					String sql2 = "select count(*) from ExamGroup ";
					int totalRecord = Integer.parseInt(session.createQuery(sql2)
							.uniqueResult().toString());
					
					map.put("total", totalRecord);
					map.put("data", examGroupList);			
				}
				return map;
			}

		
}
