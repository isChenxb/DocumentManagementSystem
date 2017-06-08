package org.cxb.oa.dao.impl;

import java.util.Date;
import java.util.List;

import org.cxb.oa.bean.Doc;
import org.cxb.oa.dao.DocDao;

public class DocDaoImpl extends BaseDaoImpl<Doc> implements DocDao {

	/**
	 * ��ȡָ���������һ������ID�ĺ���λ
	 * 
	 * @param date
	 *            ��ʽ�����ʱ���ַ���
	 * @return ���һ������ID�ĺ���λ
	 */
	@Override
	public String getIdNumber(String date) {
		String sql = "select max(right(doc_id,4)) from doc_info where left(doc_id,8)='" + date + "'";
		List list = getSessionFactory().getCurrentSession().createSQLQuery(sql).list();
		String result = (String) list.get(0);
		return result;
	}

	/**
	 * ��ҳ��ѯ�û������Ĺ���
	 * 
	 * @param username
	 *            �����û����û���
	 * @param pageNo
	 *            ��ǰҳ��
	 * @param pageSize
	 *            ÿҳ��¼����
	 * @return ����������Doc����ļ���
	 */
	public List<Doc> findDocByPage(String username, int pageNo, int pageSize) {
		String hql = "from Doc where log_user = ?0";
		return findByPage(hql, pageNo, pageSize, username);
	}

	/**
	 * ��ҳ��ѯ�û�ָ��ʱ��η����Ĺ���
	 * 
	 * @param username
	 *            �û���
	 * @param pageNo
	 *            ��ǰҳ��
	 * @param pageSize
	 *            ҳ���С
	 * @param from
	 *            ��ʼʱ��
	 * @param to
	 *            ����ʱ��
	 * @return ������Ϣ
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
	 * �����û�ָ��ʱ�η�������
	 * 
	 * @param username
	 *            �û���
	 * @param from 
	 *            ��ʼʱ��
	 * @param to
	 *          ����ʱ��
	 * @return �û���������
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
	 * ��ҳ��ѯ���û������Ĺ���
	 * 
	 * @param username
	 *            �����û����û���
	 * @param pageNo
	 *            ��ǰҳ��
	 * @param pageSize
	 *            ÿҳ��¼����
	 * @return ����������Doc����ļ���
	 */
	@Override
	public List<Doc> findApproveDocByPage(String username, int pageNo, int pageSize) {
		String hql = "from Doc where inprocess= ?0 and approve_status= false";
		return findByPage(hql, pageNo, pageSize, username);
	}

	/**
	 * �����û���������
	 * 
	 * @param username
	 *            �û���
	 * @return �û���������
	 */
	@SuppressWarnings("unchecked")
	@Override
	public long findCountByUser(String username) {
		String hql = "select count(*) from Doc where log_user = ?0";
		List<Long> l = (List<Long>) getSessionFactory().getCurrentSession().createQuery(hql)
				.setParameter(0 + "", username).list();
		// ���ز�ѯ�õ���ʵ������
		if (l != null && l.size() == 1) {
			return l.get(0);
		}
		return 0;
	}

	/**
	 * ��ѯ����ǰ�û�������������
	 * 
	 * @param username
	 *            �û���
	 * @return ����ǰ�û�������������
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
	 * ��ҳ��ѯ���û�����Ĺ���
	 * 
	 * @param username
	 *            �����û����û���
	 * @param pageNo
	 *            ��ǰҳ��
	 * @param pageSize
	 *            ÿҳ��¼����
	 * @return ����������Doc����ļ���
	 */
	@Override
	public List<Doc> findProcessDocByPage(String username, int pageNo, int pageSize) {
		String hql = "from Doc where inprocess= ?0 and approve_status= true and process_status = false";
		return findByPage(hql, pageNo, pageSize, username);
	}

	/**
	 * ��ѯ����ǰ�û�����������
	 * 
	 * @param username
	 *            �û���
	 * @return ����ǰ�û�����������
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
