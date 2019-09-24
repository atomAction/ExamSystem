package com.attack.entity.exam;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import com.attack.entity.questioninfo.QuestionType;
import com.attack.entity.user.Student;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 学生试卷实体类,考试对象
 * 具体某个学生的试卷
 * @author Administrator
 *
 */

@Entity
@Table(name = "studentexam")
public class StudentExam {

	private int id;  // 考试编号
	private Student  student; // 学生
	private Exam exam; // 试卷
	private int singleScore; // 单选题得分
	private int moreScore; // 多选题得分
	private int score;  // 总得分
	private Date examDate; // 考试时间

	private Map<String,String> answerMap =new HashMap<String,String>();
	
	private ExamGroup examGroup;
	@Id
	@GeneratedValue(generator="_native")
	@GenericGenerator(name="_native",strategy="native")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(targetEntity = Student.class)
	@JoinColumn(name="studentId")
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	
	@ManyToOne(targetEntity = Exam.class)
	@JoinColumn(name="examId")
	public Exam getExam() {
		return exam;
	}
	public void setExam(Exam exam) {
		this.exam = exam;
	}
	@Column
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone="GMT+8")
	@Column
	public Date getExamDate() {
		return examDate;
	}
	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}
	@Column
	public int getSingleScore() {
		return singleScore;
	}
	public void setSingleScore(int singleScore) {
		this.singleScore = singleScore;
	}
	@Column
	public int getMoreScore() {
		return moreScore;
	}
	public void setMoreScore(int moreScore) {
		this.moreScore = moreScore;
	}
	
	@ManyToOne(targetEntity = ExamGroup.class)
//	@Cascade(value={CascadeType.SAVE_UPDATE})
	@JoinColumn(name="examGroup")
	public ExamGroup getExamGroup() {
		return examGroup;
	}
	public void setExamGroup(ExamGroup examGroup) {
		this.examGroup = examGroup;
	}
	public StudentExam() {
		super();
	}
	
	@ElementCollection(fetch=FetchType.EAGER, //加载策略
			targetClass=String.class) //指定集合中元素的类型
 	@CollectionTable(name="ANSWERS_INFO") //指定集合生成的表
	@MapKeyColumn(name="M_KEY") //指定map的key生成的列
	public Map<String, String> getAnswerMap() {
		return answerMap;
	}
	public void setAnswerMap(Map<String, String> answerMap) {
		this.answerMap = answerMap;
	}
	
	
}
