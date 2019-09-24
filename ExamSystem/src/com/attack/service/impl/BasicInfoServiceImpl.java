package com.attack.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.attack.dao.ClassDao;
import com.attack.dao.CollegeDao;
import com.attack.dao.SchoolDao;
import com.attack.service.BasicInfoService;
import com.attack.entity.basicinfo.Class;
import com.attack.entity.basicinfo.College;
import com.attack.entity.basicinfo.School;

@Service
@Transactional
public class BasicInfoServiceImpl implements BasicInfoService{

	@Autowired
	private ClassDao classDao;
	@Autowired
	private SchoolDao schoolDao;
	@Autowired
	private CollegeDao collegeDao;
	
	@Override
	public Map<String,Object> queryClassPageInfo(int limit,int offset,String searchType,String searchText){
		return classDao.queryPageInfo(limit,offset,searchType,searchText);
	}
	@Override
	public Map<String,Object> queryCollegePageInfo(int limit,int offset,String searchType,String searchText){
		return collegeDao.queryPageInfo(limit,offset,searchType,searchText);
	}
	@Override
	public Map<String,Object> querySchoolPageInfo(int limit,int offset,String searchType,String searchText){
		return schoolDao.queryPageInfo(limit,offset,searchType,searchText);
	}

	@Override
	public Map<String, Object> queryClassById(int class_id) {
		Map<String,Object> map = new HashMap<String,Object>();
		Class classs = classDao.queryById(class_id);
		List<College> collegeList = collegeDao.queryAll();
		map.put("classs", classs);
		map.put("collegeList", collegeList);
		return map;
	}
	@Override
	public void updateClassById(int class_id, String class_name, int college_id) {
		Class classs = classDao.queryById(class_id);
		classs.setClass_name(class_name);
		classs.setCollege_id(collegeDao.queryById(college_id));
		classDao.update(classs);
		
	}
	@Override
	public void saveClass(String class_name, int college_id) {
		Class classs = new Class(class_name, collegeDao.queryById(college_id));
		classDao.save(classs);
	}
	@Override
	public void deleteClassById(int class_id) {
		classDao.deleteById(class_id);		
	}
	@Override
	public void deleteCollegeById(int college_id) {
		// TODO Auto-generated method stub
		collegeDao.deleteById(college_id);
		
	}
	@Override
	public Map<String, Object> queryCollegeById(int college_id) {
		Map<String,Object> map = new HashMap<String,Object>();
		College collegeList = collegeDao.queryById(college_id);
		List<School> schoolList = schoolDao.queryAll();
		map.put("collegeList", collegeList);
		map.put("schoolList", schoolList);
		return map;
	}
	@Override
	public void updateCollegeById(College college, int school_id) {
		college.setSchool_id(schoolDao.queryById(school_id));
		collegeDao.update(college);
		
	}
	@Override
	public void saveCollege(College college, int school_id) {
		// TODO Auto-generated method stub
		college.setSchool_id(schoolDao.queryById(school_id));
		collegeDao.save(college);
	}
	@Override
	public void deleteschoolById(int school_id) {
		// TODO Auto-generated method stub
		schoolDao.deleteById(school_id);
	}
	@Override
	public void updateSchoolById(School school) {
		// TODO Auto-generated method stub
		schoolDao.update(school);
	}
	@Override
	public void saveSchool(School school) {
		// TODO Auto-generated method stub
		schoolDao.save(school);
	}
	
	
}
