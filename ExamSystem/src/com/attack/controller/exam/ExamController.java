package com.attack.controller.exam;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.attack.entity.questioninfo.QuestionBank;
import com.attack.service.ExamService;

import net.sf.json.JSONObject;

@Controller
public class ExamController {

	@Autowired
	private ExamService examService;
	
	@RequestMapping("/toExam")
	public String gotoExam() {
		return "exam";
	}
	
	@RequestMapping("/toExamList")
	public String gotoExamList() {
		return "examList";
	}
	
	@RequestMapping("/getQuestionsBySubject")
	public @ResponseBody Map<String,Object> getQuestionsBySubject(int subject_id){
		Map<String,Object> map = new HashMap<String, Object>();
		List<QuestionBank> questionList = examService.getQuestionsBySubject(subject_id);
		map.put("questionList", questionList);
		return map;
	}
	
	@RequestMapping("/saveExam")
	public @ResponseBody Map<String,Object> saveExam( @RequestBody  List<QuestionBank>  questionList){
		examService.saveExam(questionList);
		Map<String,Object> map = new HashMap<String, Object>();
		return null;
	}

	@RequestMapping("/getExamInfo")
	public @ResponseBody Map<String,Object> getExamGroupInfo(int limit,int offset,String searchType,String searchText) throws UnsupportedEncodingException {	
		System.out.println("limit is:"+limit);
		System.out.println("offset is:"+offset);
		System.out.println("searchType is"+searchType);
		System.out.println("searchText is"+searchText);
		Map<String,Object> map = examService.getExamInfo(limit, offset,searchType,searchText);
		return map;
	}
	
	@RequestMapping("/queryAllExamGroup")
	public @ResponseBody Map<String,Object> queryAllExamGroup(){	
		Map<String,Object> map = new HashMap<String, Object>();
		map = examService.queryAllExamGroup();
		return map;
	}
	
	@RequestMapping("/updateGroupExamById")
	public @ResponseBody Map<String,Object> updateGroupExamById(int exam_id ,String ab_select,int examGroup_id){	
		Map<String,Object> map = new HashMap<String, Object>();
		examService.updateExamById(exam_id ,ab_select,examGroup_id);
		return map;
	}
	
	@RequestMapping("/deleteExamById")
	public @ResponseBody Map<String,Object> deleteExamById(int id){	
		Map<String,Object> map = new HashMap<String, Object>();
		examService.deleteExamById(id);
		return map;
	}
	
}
