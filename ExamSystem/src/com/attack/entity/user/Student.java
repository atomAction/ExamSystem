package com.attack.entity.user;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import com.attack.entity.basicinfo.Class;
import com.attack.entity.exam.StudentExam;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 学生实体类
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "student")
public class Student {

	private int stu_id; // 主键
	private String number; // 学号
	private String name; // 学生名字
	private User user_id; // 登录账号
	private Class class_id; // 对应的班级
	
	@OneToMany(mappedBy="student")
	private Set<StudentExam> StudentExams=new HashSet<StudentExam>();

	@Id
	@GeneratedValue(generator = "_native")
	@GenericGenerator(name = "_native", strategy = "native")
	public int getStu_id() {
		return stu_id;
	}

	public void setStu_id(int stu_id) {
		this.stu_id = stu_id;
	}

	
	@Column
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	@Column
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@OneToOne(cascade = javax.persistence.CascadeType.ALL,targetEntity = User.class)
	@JoinColumn(name = "userId",nullable = true,unique = true)
	public User getUser_id() {
		return user_id;
	}

	public void setUser_id(User user_id) {
		this.user_id = user_id;
	}

	@ManyToOne(targetEntity = Class.class)
//	@Cascade(value={CascadeType.SAVE_UPDATE})
	@JoinColumn(name="classId")
	public Class getClass_id() {
		return class_id;
	}

	public void setClass_id(Class class_id) {
		this.class_id = class_id;
	}

	@Override
	public String toString() {
		return "Student [stu_id=" + stu_id + ", number=" + number + ", name=" + name + ", user_id="
				+ user_id + ", class_id=" + class_id + ", StudentExams=" + StudentExams + "]";
	}

	public Student() {
		super();
	}






	
	

}
