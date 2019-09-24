package com.attack.controller.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.attack.entity.user.Student;
import com.attack.entity.user.User;
import com.attack.service.BasicInfoService;
import com.attack.service.UserService;
import com.attack.service.impl.BasicInfoServiceImpl;

@Controller
@SessionAttributes("user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/views/tologin")
	public String gotologin() {	
		System.out.println("gotologin");
		return "redirect:/login.jsp";
	}
	
	@RequestMapping("/tologin")
	public String tologin() {	
		System.out.println("tologin");
		return "redirect:/login.jsp";
	}
	
	@RequestMapping("/login")
	public @ResponseBody Map<String,Object> doLogin(@RequestBody User user ,Model model) {
		System.out.println(user);
		Map<String,Object> map = new HashMap<String,Object>();
		User user1 = userService.login(user);
		 if(user1==null){
			 map.put("statu", "error");
			 map.put("msg", "用户名或者密码错误");
				return map;
			}else {
				if(user1.getUser_type(). equals("student")) {
					model.addAttribute("user", user1.getStudent());
				}else if(user1.getUser_type(). equals("teacher")){
					model.addAttribute("user", user1.getTeacher());
				}else {
					model.addAttribute("user", user1.getAdmin());
				}
					
				map.put("statu", "succes");
				map.put("msg", "登录成功");
				map.put("logintype", user1.getUser_type());
				System.out.println("--------------------");
				return map;
			}
	
	}
	
	
	@RequestMapping("/gotoindex")
	public String gotoindex() {	
		System.out.println("gotoindex");
		return "index";
	}
	
	@RequestMapping("/gotoStudentindex")
	public String gotoStudentindex() {	
		System.out.println("gotoStudentindex");
		return "redirect:/views/studentindex.jsp";
	}
	@RequestMapping("/gotoAdminIndex")
	public String gotoAdmintindex() {	
		System.out.println("gotoAdminIndex");
		return "adminIndex";
	}
	
}
