package com.attack.controller.exam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.attack.entity.exam.Exam;
import com.attack.entity.exam.StudentExam;
import com.attack.entity.user.Student;
import com.attack.entity.user.Teacher;
import com.attack.service.StudentExamService;
import com.fasterxml.jackson.databind.ObjectMapper;

import aj.org.objectweb.asm.TypeReference;



@Controller
public class StudentExamController {
	
	@Autowired
	private StudentExamService studentExamService;
	
	@RequestMapping("/views/toStudentExam")
	public String gotoStudentExam() {
		return "redirect:/views/examination.jsp";
	}
	@RequestMapping("/views/toStudentScore")
	public String gotoStudentScore() {
		return "redirect:/views/score.jsp";
	}
	
	@RequestMapping("/tostudentExamListForTeacher")
	public String gotostudentExamListForTeacher() {
		return "studentExamList";
	}
	@RequestMapping("/tostudentExamListForAdmin")
	public String gotostudentExamListForAdmin() {
		return "studentExamListForAdmin";
	}
	@RequestMapping("/views/queryAllExam")
	public @ResponseBody Map<String,Object> queryAllExam(){
		Map<String,Object> map = new HashMap<String, Object>();
		List<Exam> examList = studentExamService.queryAllExam();
		map.put("examList", examList);
		return map;
	}
	
	@RequestMapping("/views/getStudentExam")
	public @ResponseBody Map<String,Object> getStudentExam(int examId){
		System.out.println(examId);
		Map<String,Object> map = new HashMap<String, Object>();
		Exam exam = studentExamService.getStudentExam(examId);
		map.put("exam", exam);
		return map;
	}
	
	@RequestMapping(value = "/views/submitAnswers",method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> submitAnswers(@RequestBody JSONObject answerMapAnd,HttpServletRequest request){

		 HttpSession session = request.getSession();
		 Student student = (Student) session.getAttribute("user");

		 Map<String,String> map1 = (Map)answerMapAnd.get("answerMapAnd");

		 Map<String,Object> map = new HashMap<String, Object>();
		 map = studentExamService.saveStudentExam(student,map1);
		 return map;
	}
	
	@RequestMapping(value = "/views/getStudentExamForStudentInfo")
	public @ResponseBody Map<String,Object> getStudentExamForStudentInfo(int limit,int offset,HttpServletRequest request,String searchText){

		HttpSession session = request.getSession();
		 Student student = (Student) session.getAttribute("user");
		 int stu_id = student.getStu_id();
		Map<String,Object> map = studentExamService.getStudentExamForStudentInfo(limit, offset,stu_id,searchText);
		return map;
	}
	
	@RequestMapping(value = "/getStudentExamForTeacherInfo")
	public @ResponseBody Map<String,Object> getStudentExamForTeacherInfo(int limit,int offset,HttpServletRequest request,String searchText){

		HttpSession session = request.getSession();
		 Teacher teacher = (Teacher) session.getAttribute("user");
		 String teacher_name = teacher.getName();
		Map<String,Object> map = studentExamService.getStudentExamForTeacherInfo(limit, offset,teacher_name,searchText);
		return map;
	}
	@RequestMapping(value = "/getStudentExamForAdminInfo")
	public @ResponseBody Map<String,Object> getStudentExamForAdminInfo(int limit,int offset,String searchText){
		Map<String,Object> map = studentExamService.getStudentExamForAdminInfo(limit, offset,searchText);
		return map;
	}
	
	@RequestMapping("/updateStudentExamScore")
	public @ResponseBody Map<String,Object> updateStudentExamScore(int studentExam_id,int getscore){
		System.out.println(studentExam_id);
		System.out.println(getscore);
		Map<String,Object> map = new HashMap<String, Object>();
		studentExamService.updateStudentExamScore(studentExam_id,getscore);
		map.put("success", "statu");
		return map;
	}
}