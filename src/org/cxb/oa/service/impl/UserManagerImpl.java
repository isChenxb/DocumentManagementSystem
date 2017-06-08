package org.cxb.oa.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cxb.oa.bean.User;
import org.cxb.oa.dao.UserDao;
import org.cxb.oa.service.UserManager;

public class UserManagerImpl implements UserManager {
	private UserDao dao;

	public UserDao getDao() {
		return dao;
	}

	public void setDao(UserDao dao) {
		this.dao = dao;
	}

	@Override
	public boolean login(String username, String password) {
		List<User> list = dao.findByNameAndPass(new User(username, password));
		if (list.isEmpty())
			return false;
		else
			return true;
	}

	@Override
	public List<Map<String, String>> getDepUserNames(String dep_id) {
		List<User> userList = dao.getUserByDepId(dep_id);
		List<Map<String , String>> mapList = new ArrayList<Map<String , String>>();
		for(User user : userList) {
			Map<String , String> map1 = new HashMap<String , String>();
			map1.put("username", user.getUsername());
			map1.put("name", user.getName());
			mapList.add(map1);
		}
		return mapList;
	}

	@Override
	public String getName(String username) {
		User user = dao.get(User.class, username);
		return user.getName();
	}

	@Override
	public boolean checkOldPass(String username, String oldPass) {
		boolean result = false;
		User user = dao.get(User.class, username);
		if (user.getPass().equals(oldPass))
			result = true;
		return result;
	}

	@Override
	public boolean changePass(String username, String newPass) {
		User user = dao.get(User.class, username);
		user.setPass(newPass);
		dao.update(user);
		return true;
	}

}
