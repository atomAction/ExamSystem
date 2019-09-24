package com.attack.entity.questioninfo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import com.attack.entity.questioninfo.QuestionBank.WithoutAnswer;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * 科目知识点实体类
 * @author Administrator
 *
 */
@Entity
@Table(name = "knowledgepoint")
@JsonView(WithoutAnswer.class)
public class SBJKnowledgePoint {

	private int id ;
	private String pointName;
	

	private Subject subject;
	
	@OneToMany(mappedBy="sBJKnowledgePoint")
	private Set<QuestionBank> questions=new HashSet<QuestionBank>();

	
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
	public String getPointName() {
		return pointName;
	}

	public void setPointName(String pointName) {
		this.pointName = pointName;
	}

	@ManyToOne(targetEntity = Subject.class)
	@Cascade(value={CascadeType.SAVE_UPDATE})
	@JoinColumn(name="subject")
	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
	
}
