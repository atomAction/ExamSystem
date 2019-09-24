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
 * 科目实体类
 * @author Administrator
 *
 */
@Entity
@Table(name = "subject")
@JsonView(WithoutAnswer.class)
public class Subject {

	private int id;
	private String name;
	
	@OneToMany(mappedBy="subject")
	private Set<SBJKnowledgePoint> knowledgePoints=new HashSet<SBJKnowledgePoint>();
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
	
	
}
