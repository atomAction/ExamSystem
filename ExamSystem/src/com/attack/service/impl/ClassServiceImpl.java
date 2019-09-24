package com.attack.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.attack.dao.ClassDao;
import com.attack.service.ClassService;

@Service
@Transactional
public class ClassServiceImpl implements ClassService {

	@Autowired
	private ClassDao classDao;
	
	
	public Map<String,Object> queryPageInfo(int limit,int offset,String searchType,String searchText){
		return classDao.queryPageInfo(limit,offset,searchType,searchText);
	}
}
