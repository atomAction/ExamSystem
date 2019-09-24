package com.attack.controller.exam;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.attack.entity.exam.ExamGroup;
import com.attack.entity.user.Student;
import com.attack.entity.user.Teacher;
import com.attack.service.ExamGroupService;

@Controller
public class ExamGroupController {

	@Autowired
	private ExamGroupService examGroupService;

	@RequestMapping("/views/toExamGroup")
	public String gotoExamGroup() {
		return "redirect:/views/examGroup.jsp";
	}
	@RequestMapping("/views/examination")
	public String gotoexamination() {
		return "redirect:/views/examination.jsp";
	}
	@RequestMapping("/toTeacherExamGroup")
	public String toTeacherExamGroup() {
		return "examGroup";
	}
	@RequestMapping("/toAdminExamGroup")
	public String toAdminExamGroup() {
		return "examGroupForAdmin";
	}
	
	/**
	 * 考试table显示
	 * @param limit
	 * @param offset
	 * @return
	 */
	@RequestMapping("/views/getExamGroupInfo")
	public @ResponseBody Map<String,Object> getExamGroupInfo(int limit,int offset,String searchType,String searchText,HttpServletRequest request) throws UnsupportedEncodingException {	
		System.out.println("limit is:"+limit);
		System.out.println("offset is:"+offset);
		System.out.println("searchType is"+searchType);
		System.out.println("searchText is"+searchText);
		 HttpSession session = request.getSession();
		 Student student = (Student) session.getAttribute("user");
		Map<String,Object> map = examGroupService.getExamGroupInfo(limit, offset,searchType,searchText,student);
		return map;
	}
	
	@RequestMapping("/getExamGroupForTeacherInfo")
	public @ResponseBody Map<String,Object> getExamGroupForTeacherInfo(int limit,int offset,HttpServletRequest request,String searchText) throws UnsupportedEncodingException {	
		System.out.println("limit is:"+limit);
		System.out.println("offset is:"+offset);
		System.out.println("searchText is"+searchText);
		
		HttpSession session = request.getSession();
		 Teacher teacher = (Teacher) session.getAttribute("user");
		 String teacher_name = teacher.getName();
		Map<String,Object> map = examGroupService.getExamGroupForTeacherInfo(limit, offset,teacher_name,searchText);
		return map;
	}
	
	@RequestMapping("/getExamGroupForAdminInfo")
	public @ResponseBody Map<String,Object> getExamGroupForAdminInfo(int limit,int offset,String searchText) throws UnsupportedEncodingException {	
		System.out.println("limit is:"+limit);
		System.out.println("offset is:"+offset);
		System.out.println("searchText is"+searchText);
	
		Map<String,Object> map = examGroupService.getExamGroupForAdminInfo(limit, offset,searchText);
		return map;
	}
	
	@RequestMapping("/queryAllSubjectAndExam")
	public @ResponseBody Map<String,Object> queryAllSubjectAndExam(){	
		Map<String,Object> map = new HashMap<String, Object>();
		return examGroupService.queryAllSubjectAndExam();
	}
	
	
	@RequestMapping("/saveExamGroup")
	public @ResponseBody Map<String,Object> saveExamGroup(ExamGroup examGroup,HttpServletRequest request){	
		Map<String,Object> map = new HashMap<String, Object>();
		 HttpSession session = request.getSession();
		 Teacher teacher = (Teacher) session.getAttribute("user");
		 examGroup.setTeacher_name(teacher.getName());
		 examGroupService.saveExamGroup( examGroup);
		 return map;
	}
	
	@RequestMapping("/updateExamGroupById")
	public @ResponseBody Map<String,Object> updateExamGroupById(ExamGroup examGroup,HttpServletRequest request){	
		Map<String,Object> map = new HashMap<String, Object>();
		 HttpSession session = request.getSession();
		 Teacher teacher = (Teacher) session.getAttribute("user");
		 examGroup.setTeacher_name(teacher.getName());
		 examGroupService.updateExamGroupById( examGroup);
		 return map;
	}
	
	@RequestMapping("/deleteExamGroupById")
	public @ResponseBody Map<String,Object> deleteExamGroupById(int examGroup_id){	
		Map<String,Object> map = new HashMap<String, Object>();
		 examGroupService.deleteExamGroupById( examGroup_id);
		 return map;
	}
}
