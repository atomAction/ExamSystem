package com.attack.entity.questioninfo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.attack.entity.questioninfo.QuestionBank.WithoutAnswer;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * 题型实体类
 * @author Administrator
 *
 */
@Entity
@Table(name = "questiontype")
@JsonView(WithoutAnswer.class)
public class QuestionType {

	private int questionType_id;
	
	private String questionType_name;
	
	private int grade;
	@OneToMany(mappedBy="questionType")
	private Set<QuestionBank> questions=new HashSet<QuestionBank>();


	@Id
	@GeneratedValue(generator="_native")
	@GenericGenerator(name="_native",strategy="native")
	public int getQuestionType_id() {
		return questionType_id;
	}

	public void setQuestionType_id(int questionType_id) {
		this.questionType_id = questionType_id;
	}

	@Column
	public String getQuestionType_name() {
		return questionType_name;
	}

	public void setQuestionType_name(String questionType_name) {
		this.questionType_name = questionType_name;
	}
	@Column
	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	

	
	
}
