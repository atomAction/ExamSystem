package com.attack.dao;

import java.util.List;
import java.util.Map;

import com.attack.entity.basicinfo.College;
import com.attack.entity.basicinfo.School;

public interface CollegeDao {

	public College queryById(int id) ;
	
	public void deleteById(int id) ;
	
	public void update(College college);
	
	public void save(College college);
	public Map<String, Object> queryPageInfo(int limit, int offset,String searchType,String searchText);

	public List<College> queryAll();
}
