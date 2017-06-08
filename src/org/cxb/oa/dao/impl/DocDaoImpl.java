package org.cxb.oa.dao.impl;

import java.util.Date;
import java.util.List;

import org.cxb.oa.bean.Doc;
import org.cxb.oa.dao.DocDao;

public class DocDaoImpl extends BaseDaoImpl<Doc> implements DocDao {

	/**
	 * 获取指定日期最后一条公文ID的后四位
	 * 
	 * @param date
	 *            格式化后的时间字符串
	 * @return 最后一条公文ID的后四位
	 */
	@Override
	public String getIdNumber(String date) {
		String sql = "select max(right(doc_id,4)) from doc_info where left(doc_id,8)='" + date + "'";
		List list = getSessionFactory().getCurrentSession().createSQLQuery(sql).list();
		String result = (String) list.get(0);
		return result;
	}

	/**
	 * 分页查询用户发出的公文
	 * 
	 * @param username
	 *            发文用户的用户名
	 * @param pageNo
	 *            当前页号
	 * @param pageSize
	 *            每页记录条数
	 * @return 满足条件的Doc对象的集合
	 */
	public List<Doc> findDocByPage(String username, int pageNo, int pageSize) {
		String hql = "from Doc where log_user = ?0";
		return findByPage(hql, pageNo, pageSize, username);
	}

	/**
	 * 分页查询用户指定时间段发出的公文
	 * 
	 * @param username
	 *            用户名
	 * @param pageNo
	 *            当前页号
	 * @param pageSize
	 *            页面大小
	 * @param from
	 *            开始时间
	 * @param to
	 *            结束时间
	 * @return 公文信息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Doc> findDocByPageInTime(String username, int pageNo, int pageSize, Date from, Date to) {
		String hql = "from Doc where log_user = ?0 and log_time != null and log_time >=:date1 and log_time <=:date2";
		List<Doc> result = (List<Doc>) getSessionFactory()
				.getCurrentSession()
				.createQuery(hql)
				.setParameter(0 + "", username)
				.setTimestamp("date1", from)
				.setTimestamp("date2", to)
				.setFirstResult((pageNo - 1) * pageSize)
				.setMaxResults(pageSize)
				.list();
		return result;
	}

	/**
	 * 查找用户指定时段发文总数
	 * 
	 * @param username
	 *            用户名
	 * @param from 
	 *            开始时间
	 * @param to
	 *          结束时间
	 * @return 用户发文总数
	 */
	@SuppressWarnings("unchecked")
	@Override
	public long findCountByUserInTime(String username, Date from, Date to) {
		String hql = "select  count(*) from Doc where log_user = ?0 and log_time != null and log_time >=:date1 and log_time <=:date2";
		List<Long> result = (List<Long>) getSessionFactory().getCurrentSession()
				.createQuery(hql)
				.setParameter(0 + "", username)
				.setTimestamp("date1", from)
				.setTimestamp("date2", to)
				.list();
		if (result != null && result.size() == 1)
			return result.get(0);
		return 0;
	}

	/**
	 * 分页查询待用户审批的公文
	 * 
	 * @param username
	 *            发文用户的用户名
	 * @param pageNo
	 *            当前页号
	 * @param pageSize
	 *            每页记录条数
	 * @return 满足条件的Doc对象的集合
	 */
	@Override
	public List<Doc> findApproveDocByPage(String username, int pageNo, int pageSize) {
		String hql = "from Doc where inprocess= ?0 and approve_status= false";
		return findByPage(hql, pageNo, pageSize, username);
	}

	/**
	 * 查找用户发文总数
	 * 
	 * @param username
	 *            用户名
	 * @return 用户发文总数
	 */
	@SuppressWarnings("unchecked")
	@Override
	public long findCountByUser(String username) {
		String hql = "select count(*) from Doc where log_user = ?0";
		List<Long> l = (List<Long>) getSessionFactory().getCurrentSession().createQuery(hql)
				.setParameter(0 + "", username).list();
		// 返回查询得到的实体总数
		if (l != null && l.size() == 1) {
			return l.get(0);
		}
		return 0;
	}

	/**
	 * 查询待当前用户审批公文总数
	 * 
	 * @param username
	 *            用户名
	 * @return 待当前用户审批公文总数
	 */
	@SuppressWarnings("unchecked")
	@Override
	public long findCountNeedApprove(String username) {
		String hql = "select count(*) from Doc where inprocess = ?0 and approve_status= false";
		List<Long> l = (List<Long>) getSessionFactory().getCurrentSession().createQuery(hql)
				.setParameter(0 + "", username).list();
		if (l != null && l.size() == 1) {
			return l.get(0);
		}
		return 0;
	}

	/**
	 * 分页查询待用户办理的公文
	 * 
	 * @param username
	 *            发文用户的用户名
	 * @param pageNo
	 *            当前页号
	 * @param pageSize
	 *            每页记录条数
	 * @return 满足条件的Doc对象的集合
	 */
	@Override
	public List<Doc> findProcessDocByPage(String username, int pageNo, int pageSize) {
		String hql = "from Doc where inprocess= ?0 and approve_status= true and process_status = false";
		return findByPage(hql, pageNo, pageSize, username);
	}

	/**
	 * 查询待当前用户办理公文总数
	 * 
	 * @param username
	 *            用户名
	 * @return 待当前用户处理公文总数
	 */
	@SuppressWarnings("unchecked")
	@Override
	public long findCountNeedProcess(String username) {
		String hql = "select count(*) from Doc where inprocess = ?0 and approve_status= true and process_status = false";
		List<Long> l = (List<Long>) getSessionFactory().getCurrentSession().createQuery(hql)
				.setParameter(0 + "", username).list();
		if (l != null && l.size() == 1) {
			return l.get(0);
		}
		return 0;
	}

}
