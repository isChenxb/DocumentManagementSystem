package org.cxb.oa.dao.impl;

import java.util.List;

import org.cxb.oa.bean.OuterApprove;
import org.cxb.oa.dao.OuterApproveDao;

public class OuterApproveDaoImpl extends BaseDaoImpl<OuterApprove> implements OuterApproveDao {
	@Override
	public String getIdNumber(String date) {
		String sql = "select max(right(approve_id,4)) from outerapprove_info where left(approve_id,8)='"+date+"'";
		List list = getSessionFactory().getCurrentSession()
				.createSQLQuery(sql)
				.list();
		String result = (String)list.get(0);
		return result;
	}
}
