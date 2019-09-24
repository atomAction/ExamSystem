package com.attack.controller.user;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.attack.entity.exam.Exam;
import com.attack.entity.user.Administrator;
import com.attack.entity.user.Student;
import com.attack.entity.user.Teacher;
import com.attack.entity.user.User;
import com.attack.service.UserInfoService;

@Controller
public class UserInfoController {

	@Autowired
	private UserInfoService userInfoServive;
	
	@RequestMapping("/views/toStudentManager")
	public String gotoStudentManager() {
		return "redirect:/views/studentManager.jsp";
	}
	
	@RequestMapping("/toTeacherManager")
	public String gotoTeacherManager() {
		return "teacherManager";
	}
	@RequestMapping("/toindex")
	public String gotoindex() {
		return "redirect:jsp/myindex.jsp";
	}
	@RequestMapping("/views/toindex")
	public String gotoindex2() {
		return "redirect:/jsp/myindex.jsp";
	}
	@RequestMapping("/toAdminManager")
	public String gotoAdminManager() {
		return "adminManager";
	}
	@RequestMapping("/toAdminStudent")
	public String gotoAdminStudent() {
		return "studentList";
	}
	@RequestMapping("/toAdminTeacher")
	public String gotoAdminTeacher() {
		return "teacherList";
	}
	@RequestMapping("/views/queryStudentInfo")
	public @ResponseBody Map<String,Object> queryStudentInfo(HttpServletRequest request){
		
		HttpSession session = request.getSession();
		 Student student = (Student) session.getAttribute("user");
		 
		Map<String,Object> map = new HashMap<String, Object>();
		map = userInfoServive.queryStudentInfo();
		map.put("student", student);
		return map;
	}
	@RequestMapping("/queryTeacherInfo")
	public @ResponseBody Map<String,Object> queryTeacherInfo(HttpServletRequest request){
		HttpSession session = request.getSession();
		 Teacher teacher = (Teacher) session.getAttribute("user"); 
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("teacher", teacher);
		return map;
	}
	@RequestMapping("/queryAdminInfo")
	public @ResponseBody Map<String,Object> queryAdminInfo(HttpServletRequest request){
		
		HttpSession session = request.getSession();
		 Administrator admin = (Administrator) session.getAttribute("user");
		 
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("admin", admin);
		return map;
	}
	@RequestMapping("/views/updateStudentInfo")
	public @ResponseBody Map<String,Object> updateStudentInfon(HttpServletRequest request,
			Student student ,User user,int classid){
		HttpSession session = request.getSession();
		 Student student1 = (Student) session.getAttribute("user");
		 user.setUser_type("student");
		 student1.setName(student.getName());
		 student1.setNumber(student.getNumber());
		 student1.setUser_id(user);
		 userInfoServive.updateStudentInfon(student1,classid);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("success", "statu");
		return map;
	}
	
	@RequestMapping("/updateTeacherInfo")
	public @ResponseBody Map<String,Object> updateTeacherInfo(HttpServletRequest request,
			Teacher teacher ,User user){
		HttpSession session = request.getSession();
		Teacher teacher1= (Teacher) session.getAttribute("user");
		 user.setUser_type("teacher");
		 teacher1.setName(teacher.getName());
		 teacher1.setNumber(teacher.getNumber());
		 teacher1.setUser_id(user);
		 userInfoServive.updateStudentInfon(teacher1);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("success", "statu");
		return map;
	}
	
	@RequestMapping("/updateAdminInfo")
	public @ResponseBody Map<String,Object> updateTeacherInfo(HttpServletRequest request,
			Administrator admin ,User user){
		HttpSession session = request.getSession();
		Administrator admin1 = (Administrator) session.getAttribute("user");
		 user.setUser_type("admin");
		 admin1.setName(admin.getName());
		 admin1.setNumber(admin.getNumber());
		 admin1.setUser_id(user);
		 userInfoServive.updateAdminInfon(admin1);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("success", "statu");
		return map;
	}
	
	/**
	 * 学生table显示
	 * @param limit
	 * @param offset
	 * @return
	 */
	@RequestMapping("/getStudentInfo")
	public @ResponseBody Map<String,Object> getStudentPageInfo(int limit,int offset,String searchType,String searchText) throws UnsupportedEncodingException {	
		System.out.println("limit is:"+limit);
		System.out.println("offset is:"+offset);
		System.out.println("searchType is"+searchType);
		System.out.println("searchText is"+searchText);
		Map<String,Object> map = userInfoServive.queryStudentPageInfo(limit, offset,searchType,searchText);
		return map;
	}
	
	/**
	 * 教师table显示
	 * @param limit
	 * @param offset
	 * @return
	 */
	@RequestMapping("/getTeacherInfo")
	public @ResponseBody Map<String,Object> getTeacherPageInfo(int limit,int offset,String searchType,String searchText) throws UnsupportedEncodingException {	
		System.out.println("limit is:"+limit);
		System.out.println("offset is:"+offset);
		System.out.println("searchType is"+searchType);
		System.out.println("searchText is"+searchText);
		Map<String,Object> map = userInfoServive.getTeacherPageInfo(limit, offset,searchType,searchText);

		return map;
	}
	@RequestMapping("/deleteStudentById")
	public @ResponseBody Map<String,Object> deleteStudentById(int student_id)  {		
		Map<String,Object> map = new HashMap<String,Object>();
		userInfoServive.deleteStudentById(student_id);
		map.put("statu", "success");
		return map;
	}
	@RequestMapping("/deleteTeacherById")
	public @ResponseBody Map<String,Object> deleteTeacherById(int teacher_id)  {		
		Map<String,Object> map = new HashMap<String,Object>();
		userInfoServive.deleteTeacherById(teacher_id);
		map.put("statu", "success");
		return map;
	}
	@RequestMapping("/queryStudentById")
	public @ResponseBody Map<String,Object> queryStudentById(int student_id)  {		
		Map<String,Object> map = new HashMap<String,Object>();
		map = userInfoServive.queryStudentById(student_id);
		return map;
	}
	@RequestMapping("/queryTeacherById")
	public @ResponseBody Map<String,Object> queryTeacherById(int teacher_id)  {		
		Map<String,Object> map = new HashMap<String,Object>();
		map = userInfoServive.queryTeacherById(teacher_id);
		return map;
	}

	@RequestMapping("/updateStudentById")
	public @ResponseBody Map<String,Object> updateStudentById(int classid,User user,Student student)  {		
		Map<String,Object> map = new HashMap<String,Object>();
		userInfoServive.updateStudentById(classid ,user, student);
		map.put("statu", "success");
		return map;
	}
	@RequestMapping("/updateTeacherById")
	public @ResponseBody Map<String,Object> updateTeacherById(User user,Teacher teacher)  {		
		Map<String,Object> map = new HashMap<String,Object>();
		userInfoServive.updateTeacherById(user, teacher);
		map.put("statu", "success");
		return map;
	}
	@RequestMapping("/saveStudent")
	public @ResponseBody Map<String,Object> saveStudent(int classid,User user,Student student)  {		
		Map<String,Object> map = new HashMap<String,Object>();
		userInfoServive.saveStudent(classid ,user, student);
		map.put("statu", "success");
		return map;
	}
	@RequestMapping("/saveTeacher")
	public @ResponseBody Map<String,Object> saveTeacher(User user,Teacher teacher)  {		
		Map<String,Object> map = new HashMap<String,Object>();
		userInfoServive.saveTeacher(user, teacher);
		map.put("statu", "success");
		return map;
	}
}
