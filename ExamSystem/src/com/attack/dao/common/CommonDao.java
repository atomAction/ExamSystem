package com.attack.dao.common;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class CommonDao {


	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	//根据id查询
	public Object queryById(int id,Object object) {
		Session session = this.getSession();
		Object object1 = session.get(Object.class, id);
		return object1;
	}
	
	//根据id删除
	public void deleteById(int id,Object object) {
		Session session = this.getSession();
		Object object1 = this.queryById(id, object);
		session.delete(object1);
	}
	
	
	//修改
	public void update(Object object) {
		Session session = this.getSession();
		session.update(object);	
	}
	
	//新增
	public void save(Object object) {
		Session session = this.getSession();
		session.save(object);
	}
}
