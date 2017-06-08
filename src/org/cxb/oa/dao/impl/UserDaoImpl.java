package org.cxb.oa.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.cxb.oa.bean.User;
import org.cxb.oa.dao.UserDao;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public List<User> findByPageImpl(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findByNameAndPass(User user) {
		return find("select p from User p where p.username=?0 and p.pass=?1"
				, user.getUsername() , user.getPass());
	}

	@Override
	public List<User> getUserByDepId(String dep_id) {
		return find("select p from User p where p.user_dep=?0" , dep_id);
	}


}
