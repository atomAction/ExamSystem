package com.attack.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.attack.dao.ClassDao;
import com.mysql.jdbc.StringUtils;
import com.attack.entity.basicinfo.Class;
import com.attack.entity.basicinfo.College;

@Repository
public class ClassDaoImpl implements ClassDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	//根据id查询
	public Class queryById(int id) {
		Session session = this.getSession();
		Class classs = session.get(Class.class, id);
		return classs;
	}
	
	//根据id删除
	public void deleteById(int id) {
		Session session = this.getSession();
		Class classs = this.queryById(id);
		session.delete(classs);
	}
	
	
	//修改
	public void update(Class classs) {
		Session session = this.getSession();
		session.update(classs);	
	}
	
	//新增
	public void save(Class classs) {
		Session session = this.getSession();
		session.save(classs);
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
				String sql = "from Class where class_id = ?0";
				int id = Integer.parseInt(searchText);
				List<Class> classList = session.createQuery(sql).setParameter(0, id).getResultList();
				
				String sql2 = "select count(class_id) from Class where class_id = ?0";
				int totalRecord = Integer.parseInt(session.createQuery(sql2).setParameter(0,id).uniqueResult().toString());
				
				map.put("total", totalRecord);
				map.put("data", classList);
			}else if("name".equals(searchType)) {
				String sql = "from Class where class_name like ?0";
				Query query = session.createQuery(sql);
				query.setParameter(0,"%"+searchText+"%");
				query.setMaxResults(limit);
				query.setFirstResult(offset);
				List<Class> classList = query.getResultList();
				
				String sql2 = "select count(*) from Class where class_name like ?0";
				int totalRecord = Integer.parseInt(session.createQuery(sql2).setParameter(0,"%"+searchText+"%").uniqueResult().toString());
				
				map.put("total", totalRecord);
				map.put("data", classList);				
				
			}
		}else {
			String sql = "from Class";
			Query query = session.createQuery(sql);
			query.setFirstResult(offset);
			query.setMaxResults(limit);
			List<Class> classList = query.list();
			
			String sql2 = "select count(*) from Class";
			int totalRecord = Integer.parseInt(session.createQuery(sql2).uniqueResult().toString());
			
			map.put("total", totalRecord);
			map.put("data", classList);			
		}
		return map;
	}

	@Override
	public List<Class> queryAll() {
		// TODO Auto-generated method stub
		String hql = "from Class";
		List<Class> classList = this.getSession().createQuery(hql).list();
		return classList;
	}
	

	
	
}
