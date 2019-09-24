package com.attack.controller.question;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.attack.entity.questioninfo.QuestionBank;
import com.attack.entity.questioninfo.QuestionBank.WithAnswer;
import com.attack.entity.questioninfo.QuestionBank.WithoutAnswer;
import com.attack.service.QuestionService;
import com.fasterxml.jackson.annotation.JsonView;

@Controller
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	

	@RequestMapping("/toQuestionBank")
	@JsonView(WithAnswer.class)
	public String gototoQuestionBank() {
		return "questionList";
	}
	
	
//	@RequestMapping("/toQustionCreate")
//	public String gotoQustionCreate() {
//		return "questionCreate";
//	}
	
	/**
	 * 题库table显示
	 * @param limit
	 * @param offset
	 * @return
	 */
	@RequestMapping("/getQuestionBankInfo")
	@JsonView(WithAnswer.class)
	public @ResponseBody Map<String,Object> getQuestionPageInfo(int limit,int offset,String searchType,String searchText) throws UnsupportedEncodingException {	
		System.out.println("limit is:"+limit);
		System.out.println("offset is:"+offset);
		System.out.println("searchType is"+searchType);
		System.out.println("searchText is"+searchText);
		Map<String,Object> map = questionService.queryQuestionPageInfo(limit, offset,searchType,searchText);
		return map;
	}
	
	@RequestMapping("/saveQuestion")
	@JsonView(WithAnswer.class)
	public @ResponseBody Map<String,Object> saveQuestionBank(QuestionBank questionBank,int questionType_id,int point_id){
		Map<String,Object> map = new HashMap<String, Object>();
		System.out.println(questionBank);
		System.out.println(questionType_id);
		questionService.saveQuestionBank(questionBank,questionType_id,point_id);
		return map;
	} 

	
	@RequestMapping("/updateQuestion")
	@JsonView(WithAnswer.class)
	public @ResponseBody Map<String,Object> updateQuestion(QuestionBank questionBank,int questionType_id,int point_id){
		Map<String,Object> map = new HashMap<String, Object>();
		System.out.println(questionBank);
		System.out.println(questionType_id);
		questionService.updateQuestionBank(questionBank,questionType_id,point_id);
		return map;
	} 
}
