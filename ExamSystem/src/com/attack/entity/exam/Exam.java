package com.attack.entity.exam;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.attack.entity.questioninfo.QuestionBank;
import com.fasterxml.jackson.annotation.JsonFormat;




/**
 * 试卷实体
 * @author Administrator
 *
 */
@Entity
@Table(name = "exam")
public class Exam {
	
	private int id;
	private String examName;  //试卷名称
	private Date joinDate;    //加入时间
	
	
	private List<QuestionBank> questions=new ArrayList<QuestionBank>();   //试卷里面的试题
	
	@Id
	@GeneratedValue(generator="_native")
	@GenericGenerator(name="_native",strategy="native")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column
	public String getExamName() {
		return examName;
	}
	public void setExamName(String examName) {
		this.examName = examName;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone="GMT+8")
	@Column	
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	@ManyToMany(targetEntity = QuestionBank.class, fetch = FetchType.EAGER)
    @JoinTable(  name="exam_question_Info",                    //中间表的名字
            	joinColumns= {@JoinColumn(name="exam_id")},        //外键的字段
            	inverseJoinColumns= {@JoinColumn(name="question_id")})    //反转控制字段的名字
	public List<QuestionBank> getQuestions() {
		return questions;
	}
	public void setQuestions(List<QuestionBank> questions) {
		this.questions = questions;
	}
	public Exam() {
		super();
	}
	

	

}
