package com.attack.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.attack.dao.CollegeDao;
import com.attack.entity.basicinfo.College;
import com.mysql.jdbc.StringUtils;

@Repository
public class CollegeDaoImpl implements CollegeDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	//根据id查询
	public College queryById(int id) {
		Session session = this.getSession();
		College College = session.get(College.class, id);
		return College;
	}
	
	//根据id删除
	public void deleteById(int id) {
		Session session = this.getSession();
		College College = this.queryById(id);
		session.delete(College);
	}
	
	
	//修改
	public void update(College College) {
		Session session = this.getSession();
		session.update(College);	
	}
	
	//新增
	public void save(College College) {
		Session session = this.getSession();
		session.save(College);
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
				String sql = "from College where college_id = ?0";
				int id = Integer.parseInt(searchText);
				List<College> collegeList = session.createQuery(sql).setParameter(0, id).getResultList();
				
				String sql2 = "select count(college_id) from College where college_id = ?0";
				int totalRecord = Integer.parseInt(session.createQuery(sql2).setParameter(0,id).uniqueResult().toString());
				
				map.put("total", totalRecord);
				map.put("data", collegeList);
			}else if("name".equals(searchType)) {
				String sql = "from College where college_name like ?0";
				Query query = session.createQuery(sql);
				query.setParameter(0,"%"+searchText+"%");
				query.setMaxResults(limit);
				query.setFirstResult(offset);
				List<College> collegeList = query.getResultList();
				
				String sql2 = "select count(*) from College where college_name like ?0";
				int totalRecord = Integer.parseInt(session.createQuery(sql2).setParameter(0,"%"+searchText+"%").uniqueResult().toString());
				
				map.put("total", totalRecord);
				map.put("data", collegeList);				
				
			}
		}else {
			String sql = "from College";
			Query query = session.createQuery(sql);
			query.setFirstResult(offset);
			query.setMaxResults(limit);
			List<College> collegeList = query.list();
			
			String sql2 = "select count(*) from College";
			int totalRecord = Integer.parseInt(session.createQuery(sql2).uniqueResult().toString());
			
			map.put("total", totalRecord);
			map.put("data", collegeList);			
		}
		return map;
	}
	
	@Override
	public List<College> queryAll() {
		String hql = "from College";
		List<College> collegeList = this.getSession().createQuery(hql).list();
		return collegeList;
	}
}
