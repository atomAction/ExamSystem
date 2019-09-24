package com.attack.service;

import java.util.Map;

public interface ClassService {

	public Map<String,Object> queryPageInfo(int limit,int offset,String searchType,String searchText);
}
