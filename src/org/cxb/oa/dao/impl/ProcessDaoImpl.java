package org.cxb.oa.dao.impl;

import java.util.Date;
import java.util.List;

import org.cxb.oa.bean.ProcessInfo;
import org.cxb.oa.dao.ProcessDao;

public class ProcessDaoImpl extends BaseDaoImpl<ProcessInfo> implements ProcessDao{

	@Override
	public String getIdNumber(String date) {
		String sql = "select max(right(process_id,4)) from process where left(process_id,8)='"+date+"'";
		List list = getSessionFactory().getCurrentSession()
				.createSQLQuery(sql)
				.list();
		String result = (String)list.get(0);
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProcessInfo> findProcessedByPage(String username, int pageNo, int pageSize, Date from, Date to) {
		List<ProcessInfo> result = null;
		if (from != null && to != null) {
			String hql = "from ProcessInfo where process_date != null "
					+ "and process_user = ?0 and process_date >=:date1 and process_date <=:date2";
			result = (List<ProcessInfo>) getSessionFactory()
					.getCurrentSession()
					.createQuery(hql)
					.setParameter(0 + "", username)
					.setTimestamp("date1", from)
					.setTimestamp("date2", to)
					.setFirstResult((pageNo - 1) * pageSize)
					.setMaxResults(pageSize)
					.list();
		} else {
			String hql = "from ProcessInfo where process_date != null and process_user = ?0";
			result = (List<ProcessInfo>) getSessionFactory()
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
	public long findCountProcessed(String username, Date from, Date to) {
		long resultCount = 0;
		if (from != null && to != null) {
			String hql = "select count(*) from ProcessInfo where process_date != null "
					+ "and process_user = ?0 and process_date >=:date1 and process_date <=:date2";
			List<Long> result = (List<Long>) getSessionFactory().getCurrentSession()
					.createQuery(hql)
					.setParameter(0 + "", username)
					.setTimestamp("date1", from)
					.setTimestamp("date2", to)
					.list();
			if (result != null && result.size() == 1)
				resultCount = result.get(0);
		} else {
			String hql = "select count(*) from ProcessInfo where process_date != null and process_user = ?0";
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
