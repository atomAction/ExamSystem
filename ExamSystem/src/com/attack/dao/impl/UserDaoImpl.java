package com.attack.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.attack.dao.UserDao;
import com.attack.entity.user.Student;
import com.attack.entity.user.User;


@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	
	//根据id查询
	public User queryById(int id) {
		Session session = this.getSession();
		User user = session.get(User.class, id);
		return user;
	}
	
	//根据id删除
	public void deleteById(int id) {
		Session session = this.getSession();
		User user = this.queryById(id);
		session.delete(user);
	}
	
	
	//修改
	public void update(User user) {
		Session session = this.getSession();
		session.update(user);	
	}
	
	//新增
	public void save(User user) {
		Session session = this.getSession();
		session.save(user);
	}
	
	//登录
	public User login(User user) {
	Session session = this.getSession();
	String hql = "from User where user_name = ?0 and user_pwd = ?1 and user_type = ?2";
	User user1 = (User) session.createQuery(hql).setParameter(0, user.getUser_name())
			.setParameter(1, user.getUser_pwd())
			.setParameter(2, user.getUser_type()).uniqueResult();
	return user1;
}


	@Override
	public Student queryStudentbyUserId(int user_id) {
		String hql = "from Student where user_id.user_id = ?0 ";	
		return (Student) this.getSession().createQuery(hql).setParameter(0, user_id).uniqueResult();
	}
	
//	//根据name查询
//	public User queryByName(String user_name) {
//		Session session = this.getSession();
//		String hql = "select User u where u.user_name = ?0";
//		User user = (User) session.createQuery(hql).setParameter(0, user_name).uniqueResult();
//		return user;
//	}

	
	
}
