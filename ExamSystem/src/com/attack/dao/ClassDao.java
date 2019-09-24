package com.attack.dao;

import java.util.List;
import java.util.Map;

import com.attack.entity.basicinfo.Class;

public interface ClassDao {

	public Class queryById(int id) ;
	
	public void deleteById(int id) ;
	
	public void update(Class classs);
	
	public void save(Class classs);
	
	public Map<String, Object> queryPageInfo(int limit, int offset,String searchType,String searchText);

	public List<Class> queryAll();

}
