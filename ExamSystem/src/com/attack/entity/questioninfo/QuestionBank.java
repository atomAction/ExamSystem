package com.attack.entity.questioninfo;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import com.attack.entity.exam.Exam;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;



/**
 * 题库实体类
 * @author Administrator
 *
 */
@Entity
@Table(name = "questionbank")
public class QuestionBank {

	private int id;  // 问题编号
	private String subject; // 题目名称
	private Date joinTime; // 添加时间
	
	private QuestionType questionType;  //问题类型
	
	private String optionA; // 选项A
	private String optionB; // 选项B
	private String optionC; // 选项C
	private String optionD; // 选项D
	private String answer; // 答案
	
	private SBJKnowledgePoint sBJKnowledgePoint;
	
	private String userAnswer; // 用户回答
	
	//声明一般视图接口 只允许这个视图返回用户名属性
		public interface WithoutAnswer{};
		//声明完整视图接口 允许返回用户名密码属性 由于集成了一般视图接口  含义是拥有了一般视图所具有的返回属性
		public interface WithAnswer extends WithoutAnswer{};
	
	@Id
	@GeneratedValue(generator="_native")
	@GenericGenerator(name="_native",strategy="native")
	@JsonView(WithoutAnswer.class)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column
	@JsonView(WithoutAnswer.class)
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone="GMT+8")
	@Column
	@JsonView(WithoutAnswer.class)
	public Date getJoinTime() {
		return joinTime;
	}
	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}

	@Column
	@JsonView(WithoutAnswer.class)
	public String getOptionA() {
		return optionA;
	}
	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}
	@Column
	@JsonView(WithoutAnswer.class)
	public String getOptionB() {
		return optionB;
	}
	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}
	@Column
	@JsonView(WithoutAnswer.class)
	public String getOptionC() {
		return optionC;
	}
	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}
	@Column
	@JsonView(WithoutAnswer.class)
	public String getOptionD() {
		return optionD;
	}
	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}
	@Column
	@JsonView(WithAnswer.class)
	public String getAnswer() {
		return answer;
	}

	//@JsonProperty  //传进来不忽略
	public void setAnswer(String answer) {
		this.answer = answer.toUpperCase();
	}
	
	@Transient
	public String getUserAnswer() {
		return userAnswer;
	}
	public void setUserAnswer(String userAnswer) {
		this.userAnswer = userAnswer;
	}
	@JsonView(WithoutAnswer.class)
	@ManyToOne(targetEntity = QuestionType.class)
	@Cascade(value={CascadeType.SAVE_UPDATE})
	@JoinColumn(name="questiontype")
	public QuestionType getQuestionType() {
		return questionType;
	}
	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}
	@JsonView(WithoutAnswer.class)
	@ManyToOne(targetEntity = SBJKnowledgePoint.class)
	@Cascade(value={CascadeType.SAVE_UPDATE})
	@JoinColumn(name="SBJKnowledgePoint")
	public SBJKnowledgePoint getsBJKnowledgePoint() {
		return sBJKnowledgePoint;
	}
	public void setsBJKnowledgePoint(SBJKnowledgePoint sBJKnowledgePoint) {
		this.sBJKnowledgePoint = sBJKnowledgePoint;
	}
	
	@Override
	public String toString() {
		return "QuestionBank [id=" + id + ", subject=" + subject + ", joinTime=" + joinTime + ", questionType="
				+ questionType + ", optionA=" + optionA + ", optionB=" + optionB + ", optionC="
				+ optionC + ", optionD=" + optionD + ", answer=" + answer + ", userAnswer=" + userAnswer + "]";
	}

	
	
}
