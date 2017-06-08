package org.cxb.oa.dao;

import java.util.List;

import org.cxb.oa.bean.User;

public interface UserDao extends BaseDao<User>{
	List<User> findByPageImpl(int pageNo , int pageSize);
	public List<User> findByNameAndPass(User user);
	public List<User> getUserByDepId(String dep_id);
}
