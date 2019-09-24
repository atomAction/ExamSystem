package com.attack.dao;

import java.util.List;
import java.util.Map;

import com.attack.entity.basicinfo.School;


public interface SchoolDao {

	public School queryById(int id) ;
	
	public void deleteById(int id) ;
	
	public void update(School school);
	
	public void save(School school);
	public Map<String, Object> queryPageInfo(int limit, int offset,String searchType,String searchText);

	public List<School> queryAll();
}
