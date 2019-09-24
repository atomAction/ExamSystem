package com.attack.entity.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 教师实体类
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "teacher")
public class Teacher {

	private int teacher_id;
	private String name;
	private String number;
	private User user_id;

	@Id
	@GeneratedValue(generator = "_native")
	@GenericGenerator(name = "_native", strategy = "native")
	public int getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(int teacher_id) {
		this.teacher_id = teacher_id;
	}

	@Column
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@OneToOne(cascade = javax.persistence.CascadeType.ALL,targetEntity = User.class)
	@JoinColumn(name = "userId", nullable = true, unique = true)
	public User getUser_id() {
		return user_id;
	}

	public void setUser_id(User user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "Teacher [teacher_id=" + teacher_id + ", name=" + name + ", number="
				+ number + ", user_id=" + user_id + "]";
	}



	
}
