package com.attack.entity.basicinfo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import com.attack.entity.user.Student;

/**
 * 班级实体类
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "class")
public class Class {

	private int class_id;
	private String class_name;

	private College college_id;

	@OneToMany(mappedBy = "class_id")
	private Set<Student> students = new HashSet<Student>();

	@Id
	@GeneratedValue(generator = "_native")
	@GenericGenerator(name = "_native", strategy = "native")
	public int getClass_id() {
		return class_id;
	}

	public void setClass_id(int class_id) {
		this.class_id = class_id;
	}

	@Column
	public String getClass_name() {
		return class_name;
	}

	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}

	@ManyToOne(targetEntity = College.class)
	@Cascade(value = { CascadeType.SAVE_UPDATE })
	@Basic(fetch=FetchType.EAGER)
	@JoinColumn(name = "collegeId")
	public College getCollege_id() {
		return college_id;
	}

	public void setCollege_id(College college_id) {
		this.college_id = college_id;
	}

	public Class() {
		super();
	}

	public Class(String class_name, College college_id) {
		super();
		this.class_name = class_name;
		this.college_id = college_id;
	}

	
}
