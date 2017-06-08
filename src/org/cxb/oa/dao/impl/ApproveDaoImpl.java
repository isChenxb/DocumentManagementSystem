package org.cxb.oa.dao.impl;

import java.util.Date;
import java.util.List;

import org.cxb.oa.bean.Approve;
import org.cxb.oa.bean.Doc;
import org.cxb.oa.dao.ApproveDao;
import org.hibernate.type.StandardBasicTypes;

public class ApproveDaoImpl extends BaseDaoImpl<Approve> implements ApproveDao{

	@Override
	public String getIdNumber(String date) {
		String sql = "select max(right(approve_id,4)) from approve_info where left(approve_id,8)='"+date+"'";
		List list = getSessionFactory().getCurrentSession()
				.createSQLQuery(sql)
				.list();
		String result = (String)list.get(0);
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Approve> findApprovedByPage(String username, int pageNo, int pageSize, Date from, Date to) {
		List<Approve> result = null;
		if (from != null && to != null) {
			String hql = "from Approve where approve_date != null "
					+ "and approve_user = ?0 and approve_date >=:date1 and approve_date <=:date2";
			result = (List<Approve>) getSessionFactory()
					.getCurrentSession()
					.createQuery(hql)
					.setParameter(0 + "", username)
					.setTimestamp("date1", from)
					.setTimestamp("date2", to)
					.setFirstResult((pageNo - 1) * pageSize)
					.setMaxResults(pageSize)
					.list();
		} else {
			String hql = "from Approve where approve_date != null and approve_user = ?0";
			result = (List<Approve>) getSessionFactory()
					.getCurrentSession()
					.createQuery(hql)
					.setParameter(0 + "", username)
					.setFirstResult((pageNo - 1) * pageSize)
					.setMaxResults(pageSize)
					.list();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public long findCountApproved(String username, Date from, Date to) {
		long resultCount = 0;
		if (from != null && to != null) {
			String hql = "select count(*) from Approve where approve_date != null "
					+ "and approve_user = ?0 and approve_date >=:date1 and approve_date <=:date2";
			List<Long> result = (List<Long>) getSessionFactory().getCurrentSession()
					.createQuery(hql)
					.setParameter(0 + "", username)
					.setTimestamp("date1", from)
					.setTimestamp("date2", to)
					.list();
			if (result != null && result.size() == 1)
				resultCount = result.get(0);
		} else {
			String hql = "select count(*) from Approve where approve_date != null and approve_user = ?0";
			List<Long> result = (List<Long>) getSessionFactory().getCurrentSession()
					.createQuery(hql)
					.setParameter(0 + "", username)
					.list();
			if (result != null && result.size() == 1)
				resultCount = result.get(0);
		}
		return resultCount;
	}
	
}
