package com.attack.controller.basicinfo;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.attack.entity.basicinfo.College;
import com.attack.entity.basicinfo.School;
import com.attack.service.BasicInfoService;

@Controller
public class BasicInfoContoller {

	@Autowired
	private BasicInfoService basicInfoService;


	
	@RequestMapping("/toClass")
	public String gotoClass() {	
		return "classList";
	}
	
	@RequestMapping("/toSchool")
	public String gotoSchool() {
		
		return "schoolList";
	}
	
	@RequestMapping("/toCollege")
	public String gotoCollege() {
		
		return "collegeList";
	}
	
	
	/**
	 * 班级table显示
	 * @param limit
	 * @param offset
	 * @return
	 */
	@RequestMapping("/getClassInfo")
	public @ResponseBody Map<String,Object> getClassPageInfo(int limit,int offset,String searchType,String searchText) throws UnsupportedEncodingException {	
		System.out.println("limit is:"+limit);
		System.out.println("offset is:"+offset);
		System.out.println("searchType is"+searchType);
		System.out.println("searchText is"+searchText);
		Map<String,Object> map = basicInfoService.queryClassPageInfo(limit, offset,searchType,searchText);
		return map;
	}
	
	/**
	 * 学院table显示
	 * @param limit
	 * @param offset
	 * @return
	 */
	@RequestMapping("/getCollegeInfo")
	public @ResponseBody Map<String,Object> getCollegePageInfo(int limit,int offset,String searchType,String searchText) throws UnsupportedEncodingException {	
		System.out.println("limit is:"+limit);
		System.out.println("offset is:"+offset);
		System.out.println("searchType is"+searchType);
		System.out.println("searchText is"+searchText);
		Map<String,Object> map = basicInfoService.queryCollegePageInfo(limit, offset,searchType,searchText);
		return map;
	}
	
	
	/**
	 * 学校table显示
	 * @param limit
	 * @param offset
	 * @return
	 */
	@RequestMapping("/getSchoolInfo")
	public @ResponseBody Map<String,Object> getSchoolPageInfo(int limit,int offset,String searchType,String searchText) throws UnsupportedEncodingException {	
		System.out.println("limit is:"+limit);
		System.out.println("offset is:"+offset);
		System.out.println("searchType is"+searchType);
		System.out.println("searchText is"+searchText);
		Map<String,Object> map = basicInfoService.querySchoolPageInfo(limit, offset,searchType,searchText);
		return map;
	}
	
	@RequestMapping("/queryClassById")
	public @ResponseBody Map<String,Object> queryClassById(int class_id){
		Map<String,Object> map = new HashMap<String,Object>();
		map = basicInfoService.queryClassById(class_id);
		return map;
	}
	
	@RequestMapping("/updateClassById")
	public @ResponseBody Map<String,Object> updateClassById(int class_id,String class_name,int college_id){
		basicInfoService.updateClassById(class_id,class_name,college_id);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("statu", "success");
		return map;
	}
	
	
	@RequestMapping("/saveClass")
	public @ResponseBody Map<String,Object> saveClass(String class_name,int college_id){
		basicInfoService.saveClass(class_name,college_id);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("statu", "success");
		return map;
	}
	
	@RequestMapping("/deleteClassById")
	public @ResponseBody Map<String,Object> deleteClassById(int class_id){
		basicInfoService.deleteClassById(class_id);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("statu", "success");
		return map;
	}
	
	@RequestMapping("/deleteCollegeById")
	public @ResponseBody Map<String,Object> deleteCollegeById(int college_id){
		basicInfoService.deleteCollegeById(college_id);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("statu", "success");
		return map;
	}
	
	@RequestMapping("/queryCollegeById")
	public @ResponseBody Map<String,Object> queryCollegeById(int college_id){
		Map<String,Object> map = new HashMap<String,Object>();
		map = basicInfoService.queryCollegeById(college_id);
		return map;
	}
	
	@RequestMapping("/updateCollegeById")
	public @ResponseBody Map<String,Object> updateCollegeById(College college,int schoolid){
		basicInfoService.updateCollegeById(college,schoolid);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("statu", "success");
		return map;
	}
	
	
	@RequestMapping("/saveCollege")
	public @ResponseBody Map<String,Object> saveCollege(College college,int schoolid){
		basicInfoService.saveCollege(college,schoolid);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("statu", "success");
		return map;
	}
	
	@RequestMapping("/deleteschoolById")
	public @ResponseBody Map<String,Object> deleteschoolById(int school_id){
		basicInfoService.deleteschoolById(school_id);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("statu", "success");
		return map;
	}
	
	@RequestMapping("/updateSchoolById")
	public @ResponseBody Map<String,Object> updateSchoolById(School school){
		basicInfoService.updateSchoolById(school);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("statu", "success");
		return map;
	}
	
	
	@RequestMapping("/saveSchool")
	public @ResponseBody Map<String,Object> saveSchool(School school){
		basicInfoService.saveSchool(school);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("statu", "success");
		return map;
	}
}
