package com.attack.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.attack.dao.SchoolDao;
import com.attack.entity.basicinfo.College;
import com.attack.entity.basicinfo.School;
import com.mysql.jdbc.StringUtils;

@Repository
public class SchoolDaoImpl implements SchoolDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	
	//根据id查询
	public School queryById(int id) {
		Session session = this.getSession();
		School school = session.get(School.class, id);
		return school;
	}
	
	//根据id删除
	public void deleteById(int id) {
		Session session = this.getSession();
		School school = this.queryById(id);
		session.delete(school);
	}
	
	
	//修改
	public void update(School school) {
		Session session = this.getSession();
		session.update(school);	
	}
	
	//新增
	public void save(School school) {
		Session session = this.getSession();
		session.save(school);
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
				String sql = "from School where school_id = ?0";
				int id = Integer.parseInt(searchText);
				List<School> schoolList = session.createQuery(sql).setParameter(0, id).getResultList();
				
				String sql2 = "select count(school_id) from School where school_id = ?0";
				int totalRecord = Integer.parseInt(session.createQuery(sql2).setParameter(0,id).uniqueResult().toString());
				
				map.put("total", totalRecord);
				map.put("data", schoolList);
			}else if("name".equals(searchType)) {
				String sql = "from School where school_name like ?0";
				Query query = session.createQuery(sql);
				query.setParameter(0,"%"+searchText+"%");
				query.setMaxResults(limit);
				query.setFirstResult(offset);
				List<School> schoolList = query.getResultList();
				
				String sql2 = "select count(*) from School where school_name like ?0";
				int totalRecord = Integer.parseInt(session.createQuery(sql2).setParameter(0,"%"+searchText+"%").uniqueResult().toString());
				
				map.put("total", totalRecord);
				map.put("data", schoolList);				
				
			}
		}else {
			String sql = "from School";
			Query query = session.createQuery(sql);
			query.setFirstResult(offset);
			query.setMaxResults(limit);
			List<School> schoolList = query.list();
			
			String sql2 = "select count(*) from School";
			int totalRecord = Integer.parseInt(session.createQuery(sql2).uniqueResult().toString());
			
			map.put("total", totalRecord);
			map.put("data", schoolList);			
		}
		return map;
	}


	@Override
	public List<School> queryAll() {
		String hql = "from School";
		List<School> schoolList = this.getSession().createQuery(hql).list();
		return schoolList;
	}
}
