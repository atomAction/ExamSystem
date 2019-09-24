package com.attack.entity.exam;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * 科目试卷组
 * @author Administrator
 *
 */
@Entity
@Table(name = "examgroup")
public class ExamGroup {

	private int id;  //id
	private String name;  //考试名称
	private String teacher_name;  //出题老师 
	private int exam_A_id;   //a卷ID
	private int exam_B_id;  // B卷ID
	private String subject_name; //科目
	private int examDate;  //考试时间
	
	private StudentExam studentExam;
	
	@OneToMany(mappedBy="examGroup")
	private Set<StudentExam> studentExams = new HashSet<StudentExam>();
	@Id
	@GeneratedValue(generator = "_native")
	@GenericGenerator(name = "_native", strategy = "native")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Column
	public int getExam_A_id() {
		return exam_A_id;
	}
	public void setExam_A_id(int exam_A_id) {
		this.exam_A_id = exam_A_id;
	}
	@Column
	public int getExam_B_id() {
		return exam_B_id;
	}
	public void setExam_B_id(int exam_B_id) {
		this.exam_B_id = exam_B_id;
	}
	
	@Column
	public String getTeacher_name() {
		return teacher_name;
	}
	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}

	@Column
	public int getExamDate() {
		return examDate;
	}
	public void setExamDate(int examDate) {
		this.examDate = examDate;
	}

	
	@Column
	public String getSubject_name() {
		return subject_name;
	}
	public void setSubject_name(String subject_name) {
		this.subject_name = subject_name;
	}
	public ExamGroup() {
		super();
	}
	
	@Transient
	public Set<StudentExam> getStudentExams() {
		return studentExams;
	}
	public void setStudentExams(Set<StudentExam> studentExams) {
		this.studentExams = studentExams;
	}
	
	
	
	
}
