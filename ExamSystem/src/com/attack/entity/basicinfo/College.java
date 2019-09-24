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

/**
 * 学院实体类
 * 
 * @author Administrator
 *
 */

@Entity
@Table(name = "college")
public class College {

	private int college_id;
	private String college_name;
	private School school_id;

	@OneToMany(mappedBy = "college_id")
	private Set<Class> classes = new HashSet<Class>();

	@Id
	@GeneratedValue(generator = "_native")
	@GenericGenerator(name = "_native", strategy = "native")
	public int getCollege_id() {
		return college_id;
	}

	public void setCollege_id(int college_id) {
		this.college_id = college_id;
	}

	@Column
	public String getCollege_name() {
		return college_name;
	}

	public void setCollege_name(String college_name) {
		this.college_name = college_name;
	}

	@ManyToOne(targetEntity = School.class)
	@Cascade(value = { CascadeType.SAVE_UPDATE })
	@Basic(fetch=FetchType.EAGER)
	@JoinColumn(name = "schoolId")
	public School getSchool_id() {
		return school_id;
	}

	public void setSchool_id(School school_id) {
		this.school_id = school_id;
	}

	public College() {
		super();
	}

	


}
