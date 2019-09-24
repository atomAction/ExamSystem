package com.attack.entity.user;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 用户实体类
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "user")
public class User {

	private int user_id; //用户ID
	private String user_name;
	private String user_pwd;
	private String user_type;
	private Administrator admin;
	private Teacher teacher;
	private Student student;

	@Id
	@GeneratedValue(generator = "_native")
	@GenericGenerator(name = "_native", strategy = "native")
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	@Column
	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	@Column
	public String getUser_pwd() {
		return user_pwd;
	}

	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}

	@JsonIgnore
	@OneToOne(mappedBy = "user_id",cascade = CascadeType.ALL)
	public Administrator getAdmin() {
		return admin;
	}

	public void setAdmin(Administrator admin) {
		this.admin = admin;
	}
	@JsonIgnore
	@OneToOne(mappedBy = "user_id",cascade = CascadeType.ALL)
	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	@JsonIgnore
	@OneToOne(mappedBy = "user_id",cascade = CascadeType.ALL)
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public User() {
		super();
	}

	@Column
	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	
	

}
