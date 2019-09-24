package com.attack.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.attack.dao.StudentExamDao;
import com.attack.entity.exam.ExamGroup;
import com.attack.entity.exam.StudentExam;
import com.mysql.jdbc.StringUtils;

@Repository
public class StudentExamDaoImpl implements StudentExamDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	
		//根据id查询
			public StudentExam queryById(int id) {
				Session session = this.getSession();
				StudentExam studentExam = session.get(StudentExam.class, id);
				return studentExam;
			}
			
			//根据id删除
			public void deleteById(int id) {
				Session session = this.getSession();
				StudentExam studentExam = this.queryById(id);
				session.delete(studentExam);
			}
			
			
			//修改
			public void update(StudentExam studentExam) {
				Session session = this.getSession();
				session.update(studentExam);	
			}
			
			//新增
			public void save(StudentExam studentExam) {
				Session session = this.getSession();
				session.save(studentExam);
			}


			@Override
			public Map<String, Object> getExamGroupForTeacherInfo(int limit, int offset, int stu_id,
					String searchText) {
				// TODO Auto-generated method stub
				Session session = this.getSession();
				Map<String, Object> map = new HashMap<String, Object>();
				if(!StringUtils.isNullOrEmpty(searchText)) {
					
						String sql = "from StudentExam where examGroup.name like ?0 and student.stu_id = ?1";
						Query query = session.createQuery(sql);
						query.setParameter(0,"%"+searchText+"%");
						query.setParameter(1, stu_id);
						query.setMaxResults(limit);
						query.setFirstResult(offset);
						List<StudentExam> studentExamList = query.getResultList();
						
						String sql2 = "select count(*) from StudentExam where examGroup.name like ?0 and student.stu_id = ?1";
						int totalRecord = Integer.parseInt(session.createQuery(sql2).setParameter(0,"%"+searchText+"%")
								.setParameter(1, stu_id)
								.uniqueResult().toString());
						
						map.put("total", totalRecord);
						map.put("data", studentExamList);					
					
				}else {
					String sql = "from StudentExam where student.stu_id = ?0";
					Query query = session.createQuery(sql);
					query.setParameter(0, stu_id);
					query.setFirstResult(offset);
					query.setMaxResults(limit);
					List<StudentExam> studentExamList = query.list();
					
					String sql2 = "select count(*) from StudentExam where student.stu_id = ?0";
					int totalRecord = Integer.parseInt(session.createQuery(sql2)
							.setParameter(0, stu_id).uniqueResult().toString());
					
					map.put("total", totalRecord);
					map.put("data", studentExamList);			
				}
				return map;
			}


			@Override
			public Map<String, Object> getStudentExamForTeacherInfo(int limit, int offset, String teacher_name,
					String searchText) {
				// TODO Auto-generated method stub
				Session session = this.getSession();
				Map<String, Object> map = new HashMap<String, Object>();
				if(!StringUtils.isNullOrEmpty(searchText)) {
					
						String sql = "from StudentExam where examGroup.name like ?0 and examGroup.teacher_name = ?1";
						Query query = session.createQuery(sql);
						query.setParameter(0,"%"+searchText+"%");
						query.setParameter(1, teacher_name);
						query.setMaxResults(limit);
						query.setFirstResult(offset);
						List<StudentExam> studentExamList = query.getResultList();
						
						String sql2 = "select count(*) from StudentExam where examGroup.name like ?0 and  examGroup.teacher_name = ?1";
						int totalRecord = Integer.parseInt(session.createQuery(sql2).setParameter(0,"%"+searchText+"%")
								.setParameter(1, teacher_name)
								.uniqueResult().toString());
						
						map.put("total", totalRecord);
						map.put("data", studentExamList);					
					
				}else {
					String sql = "from StudentExam where  examGroup.teacher_name = ?0";
					Query query = session.createQuery(sql);
					query.setParameter(0, teacher_name);
					query.setFirstResult(offset);
					query.setMaxResults(limit);
					List<StudentExam> studentExamList = query.list();
					
					String sql2 = "select count(*) from StudentExam where  examGroup.teacher_name = ?0";
					int totalRecord = Integer.parseInt(session.createQuery(sql2)
							.setParameter(0, teacher_name).uniqueResult().toString());
					
					map.put("total", totalRecord);
					map.put("data", studentExamList);			
				}
				return map;
			}


			@Override
			public Map<String, Object> getStudentExamForAdminInfo(int limit, int offset, String searchText) {
				// TODO Auto-generated method stub
				Session session = this.getSession();
				Map<String, Object> map = new HashMap<String, Object>();
				if(!StringUtils.isNullOrEmpty(searchText)) {
					
						String sql = "from StudentExam where examGroup.name like ?0 ";
						Query query = session.createQuery(sql);
						query.setParameter(0,"%"+searchText+"%");
						query.setMaxResults(limit);
						query.setFirstResult(offset);
						List<StudentExam> studentExamList = query.getResultList();
						
						String sql2 = "select count(*) from StudentExam where examGroup.name like ?0 ";
						int totalRecord = Integer.parseInt(session.createQuery(sql2).setParameter(0,"%"+searchText+"%")
								.uniqueResult().toString());
						
						map.put("total", totalRecord);
						map.put("data", studentExamList);					
					
				}else {
					String sql = "from StudentExam ";
					Query query = session.createQuery(sql);
					query.setFirstResult(offset);
					query.setMaxResults(limit);
					List<StudentExam> studentExamList = query.list();
					
					String sql2 = "select count(*) from StudentExam ";
					int totalRecord = Integer.parseInt(session.createQuery(sql2)
							.uniqueResult().toString());
					
					map.put("total", totalRecord);
					map.put("data", studentExamList);			
				}
				return map;
			}


			
}
