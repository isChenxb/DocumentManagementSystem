package org.cxb.oa.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.User;
import org.cxb.oa.bean.Dep;
import org.cxb.oa.dao.DepDao;
import org.cxb.oa.service.DepManager;


public class DepManagerImpl implements DepManager{
	private DepDao depDao;
	
	public DepDao getDepDao() {
		return depDao;
	}

	public void setDepDao(DepDao depDao) {
		this.depDao = depDao;
	}

	@Override
	public List<Map<String , String>> getAllDepNames() {
		List<Dep> list = depDao.findAll(Dep.class);
		List<Map<String , String>> mapList = new ArrayList<Map<String , String>>();
		for (Dep dep : list) {
			Map<String , String> map1 = new HashMap<String , String>();
			map1.put("dep_name", dep.getDep_name());
			map1.put("dep_id", dep.getDep_id());
			mapList.add(map1);
		}
		return mapList;
	}
	
	
}
