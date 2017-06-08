package org.cxb.oa.dao.impl;

import java.util.List;

import org.cxb.oa.bean.OuterProcessInfo;
import org.cxb.oa.dao.OuterProcessDao;

public class OuterProcessDaoImpl extends BaseDaoImpl<OuterProcessInfo> implements OuterProcessDao {
	@Override
	public String getIdNumber(String date) {
		String sql = "select max(right(process_id,4)) from outerprocess_info where left(process_id,8)='"+date+"'";
		List list = getSessionFactory().getCurrentSession()
				.createSQLQuery(sql)
				.list();
		String result = (String)list.get(0);
		return result;
	}
}
