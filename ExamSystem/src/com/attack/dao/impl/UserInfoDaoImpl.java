package com.attack.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.attack.dao.UserInfoDao;
import com.attack.entity.basicinfo.College;
import com.attack.entity.basicinfo.School;
import com.attack.entity.user.Administrator;
import com.attack.entity.user.Student;
import com.attack.entity.user.Teacher;
import com.mysql.jdbc.StringUtils;
@Repository
public class UserInfoDaoImpl implements UserInfoDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	

	@Override
	public Student queryStudentById(int id) {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Student Student = session.get(Student.class, id);
		return Student;
	}
	
	//根据id删除
	public void deleteStudentById(int id) {
		Session session = this.getSession();
		Student Student = this.queryStudentById(id);
		session.delete(Student);
	}
	
	
	//修改
	public void updateStudent(Student Student) {
		Session session = this.getSession();
		session.update(Student);	
	}
	
	//新增
	public void saveStudent(Student Student) {
		Session session = this.getSession();
		session.save(Student);
	}
	
	@Override
	public Teacher queryTeacherById(int id) {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Teacher Teacher = session.get(Teacher.class, id);
		return Teacher;
	}
	
	//根据id删除
	public void deleteTeacherById(int id) {
		Session session = this.getSession();
		Teacher Teachaer = this.queryTeacherById(id);
		session.delete(Teachaer);
	}
	
	
	//修改
	public void updateTeachaer(Teacher Teacher) {
		Session session = this.getSession();
		session.update(Teacher);	
	}
	
	//新增
	public void saveTeachaer(Teacher Teacher) {
		Session session = this.getSession();
		session.save(Teacher);
	}
	@Override
	public Administrator queryAdminById(int id) {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Administrator Admin = session.get(Administrator.class, id);
		return Admin;
	}
	
	//根据id删除
	public void deleteAdminById(int id) {
		Session session = this.getSession();
		Administrator Admin = this.queryAdminById(id);
		session.delete(Admin);
	}
	
	
	//修改
	public void updateAdmin(Administrator admin) {
		Session session = this.getSession();
		session.update(admin);	
	}
	
	//新增
	public void saveAdmin(Administrator Admin) {
		Session session = this.getSession();
		session.save(Admin);
	}


	@Override
	public Map<String, Object> queryStudentPageInfo(int limit, int offset, String searchType, String searchText) {
		Session session = this.getSession();
		Map<String, Object> map = new HashMap<String, Object>();
		if(!StringUtils.isNullOrEmpty(searchText)) {
			if("id".equals(searchType)) {
				String sql = "from Student where stu_id = ?0";
				int id = Integer.parseInt(searchText);
				List<Student> studentList = session.createQuery(sql).setParameter(0, id).getResultList();
				
				String sql2 = "select count(stu_id) from Student where stu_id = ?0";
				int totalRecord = Integer.parseInt(session.createQuery(sql2).setParameter(0,id).uniqueResult().toString());
				
				map.put("total", totalRecord);
				map.put("data", studentList);
			}else if("name".equals(searchType)) {
				String sql = "from Student where name like ?0";
				Query query = session.createQuery(sql);
				query.setParameter(0,"%"+searchText+"%");
				query.setMaxResults(limit);
				query.setFirstResult(offset);
				List<Student> studentList = query.getResultList();
				
				String sql2 = "select count(*) from Student where name like ?0";
				int totalRecord = Integer.parseInt(session.createQuery(sql2).setParameter(0,"%"+searchText+"%").uniqueResult().toString());
				
				map.put("total", totalRecord);
				map.put("data", studentList);				
				
			}
		}else {
			String sql = "from Student";
			Query query = session.createQuery(sql);
			query.setFirstResult(offset);
			query.setMaxResults(limit);
			List<Student> studentList = query.list();
			
			String sql2 = "select count(*) from Student";
			int totalRecord = Integer.parseInt(session.createQuery(sql2).uniqueResult().toString());
			
			map.put("total", totalRecord);
			map.put("data", studentList);			
		}
		return map;
	}


	@Override
	public Map<String, Object> queryTeacherPageInfo(int limit, int offset, String searchType, String searchText) {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Map<String, Object> map = new HashMap<String, Object>();
		if(!StringUtils.isNullOrEmpty(searchText)) {
			if("id".equals(searchType)) {
				String sql = "from Teacher where teacher_id = ?0";
				int id = Integer.parseInt(searchText);
				List<Teacher> teacherList = session.createQuery(sql).setParameter(0, id).getResultList();
				
				String sql2 = "select count(teacher_id) from Teacher where teacher_id = ?0";
				int totalRecord = Integer.parseInt(session.createQuery(sql2).setParameter(0,id).uniqueResult().toString());
				
				map.put("total", totalRecord);
				map.put("data", teacherList);
			}else if("name".equals(searchType)) {
				String sql = "from Teacher where name like ?0";
				Query query = session.createQuery(sql);
				query.setParameter(0,"%"+searchText+"%");
				query.setMaxResults(limit);
				query.setFirstResult(offset);
				List<Teacher> teacherList = query.getResultList();
				
				String sql2 = "select count(*) from Teacher where name like ?0";
				int totalRecord = Integer.parseInt(session.createQuery(sql2).setParameter(0,"%"+searchText+"%").uniqueResult().toString());
				
				map.put("total", totalRecord);
				map.put("data", teacherList);				
				
			}
		}else {
			String sql = "from Teacher";
			Query query = session.createQuery(sql);
			query.setFirstResult(offset);
			query.setMaxResults(limit);
			List<Teacher> teacherList = query.list();
			
			String sql2 = "select count(*) from Teacher";
			int totalRecord = Integer.parseInt(session.createQuery(sql2).uniqueResult().toString());
			
			map.put("total", totalRecord);
			map.put("data", teacherList);			
		}
		return map;
	}
}
