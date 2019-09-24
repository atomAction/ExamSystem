package com.attack.controller.question;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.attack.service.SubjectService;

@Controller
public class SubjectController {

	@Autowired
	private SubjectService subjectService;
	
	@RequestMapping("/queryAllSubject")
	public @ResponseBody Map<String,Object> getAllSubject() {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("subjectList", subjectService.queryAllSubject());
		return map;
	}
	
	@RequestMapping("/querySBJKonwledgePoint")
	public @ResponseBody Map<String,Object> querySBJKonwledgePoint(int subject_id) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("pointList", subjectService.querySBJKonwledgePoint(subject_id));
		return map;
	}
	
}
