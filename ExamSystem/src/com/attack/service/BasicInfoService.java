package com.attack.service;

import java.util.Map;

import com.attack.entity.basicinfo.College;
import com.attack.entity.basicinfo.School;

public interface BasicInfoService {

	public Map<String,Object> queryClassPageInfo(int limit,int offset,String searchType,String searchText);
	
	public Map<String,Object> querySchoolPageInfo(int limit,int offset,String searchType,String searchText);
	
	public Map<String,Object> queryCollegePageInfo(int limit,int offset,String searchType,String searchText);

	public Map<String, Object> queryClassById(int class_id);

	public void updateClassById(int class_id, String class_name, int college_id);

	public void saveClass(String class_name, int college_id);

	public void deleteClassById(int class_id);


	public void deleteCollegeById(int college_id);

	public Map<String, Object> queryCollegeById(int college_id);

	public void updateCollegeById(College college, int school_id);

	public void saveCollege(College college, int school_id);

	public void deleteschoolById(int school_id);

	public void updateSchoolById(School school);

	public void saveSchool(School school);
}
