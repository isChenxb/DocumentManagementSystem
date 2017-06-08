package org.cxb.oa.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.cxb.oa.dao.BaseDao;
import org.hibernate.Query;
import org.hibernate.SessionFactory;


public class BaseDaoImpl<T> implements BaseDao<T> {
	//DAO������г־û������ײ�������SessionFactory���
	private SessionFactory sessionFactory;
	//����ע��SessionFactory�����setter����
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	public SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	//����ID����ʵ��
	@SuppressWarnings("unchecked")
	@Override
	public T get(Class<T> entityClazz, Serializable id) {
		// TODO Auto-generated method stub
		return (T)getSessionFactory().getCurrentSession()
				.get(entityClazz, id);
	}

	//����ʵ��
	@Override
	public Serializable save(T entity) {
		// TODO Auto-generated method stub
		return getSessionFactory().getCurrentSession()
				.save(entity);
	}
    //����ʵ��
	@Override
	public void update(T entity) {
		// TODO Auto-generated method stub
		getSessionFactory().getCurrentSession().saveOrUpdate(entity);
	}

	//ɾ��ʵ��
	@Override
	public void delete(T entity) {
		// TODO Auto-generated method stub
		getSessionFactory().getCurrentSession().delete(entity);
	}

	//����IDɾ��ʵ��
	@Override
	public void delete(Class<T> entityClazz, Serializable id) {
		// TODO Auto-generated method stub
		getSessionFactory().getCurrentSession()
		.createQuery("delete " + entityClazz.getSimpleName()
		+ "en where en.id = ?0").setParameter("0", id)
		.executeUpdate();
	}

	//��ȡ����ʵ��
	@Override
	public List<T> findAll(Class<T> entityClazz) {
		return find("from "
				+ entityClazz.getSimpleName());
	}

	@SuppressWarnings("unchecked")
	@Override
	public long findCount(Class<T> entityClazz) {
		// TODO Auto-generated method stub
		List<Long> l = (List<Long>) find("select count(*) from "
				+ entityClazz.getSimpleName());
		//���ز�ѯ�õ���ʵ������
		if (l != null && l.size() == 1){
			return l.get(0);
		}
		return 0;
	}
	//����HQL����ѯʵ��
	@SuppressWarnings("unchecked")
	protected List<T> find(String hql){
		return (List<T>)getSessionFactory().getCurrentSession()
				.createQuery(hql)
				.list();
	}
	
	//���ݴ�ռλ��������HQL����ѯʵ��
	protected List<T> find(String hql , Object... params){
		//������ѯ
		Query query = getSessionFactory().getCurrentSession()
				.createQuery(hql);
		//Ϊ����ռλ����HQL������ò���
		for (int i = 0 , len = params.length ; i<len ; i++){
			query.setParameter(i + "", params[i]);
		}
		return (List<T>)query.list();
	}
	
	/**
	 * ʹ��HQL�����з�ҳ��ѯ����
	 * @param hql ��Ҫ��ѯ��HQL���
	 * @param pageNo ��ѯ��pageNoҳ�ļ�¼
	 * @param pageSize ÿҳ��Ҫ��ʾ�ļ�¼��
	 * @return ��ǰҳ�����м�¼
	 */
	@SuppressWarnings("unchecked")
	protected List<T> findByPage(String hql 
			, int pageNo , int pageSize){
		//������ѯ
		return getSessionFactory().getCurrentSession()
				.createQuery(hql)
				//ִ�з�ҳ
				.setFirstResult((pageNo - 1) * pageSize)
				.setMaxResults(pageSize)
				.list();
	}
	
	/**
	 * ʹ��HQL�����з�ҳ��ѯ����
	 * @param hql ��Ҫ��ѯ��HQL���
	 * @param params ���hql��ռλ��������param���ڴ���ռλ������
	 * @param pageNo ��ѯ��pageNoҳ�ļ�¼
	 * @param pageSize ÿҳ��Ҫ��ʾ�ļ�¼��
	 * @return ��ǰҳ�����м�¼
	 */
	@SuppressWarnings("unchecked")
	protected List<T> findByPage(String hql 
			, int pageNo , int pageSize , Object...params){
		//������ѯ
		Query query = getSessionFactory().getCurrentSession()
				.createQuery(hql);
		//Ϊ����ռλ����HQL������ò���
		for (int i = 0 , len = params.length ; i < len ; i++){
			query.setParameter(i+"", params[i]);
		}
		//ִ�з�ҳ�������ز�ѯ���
		return query.setFirstResult((pageNo - 1) * pageSize)
				.setMaxResults(pageSize)
				.list();
	}
}
