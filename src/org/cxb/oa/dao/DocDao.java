package org.cxb.oa.dao;


import java.util.Date;
import java.util.List;

import org.cxb.oa.bean.Doc;

public interface DocDao extends BaseDao<Doc>{
	public String getIdNumber(String date);
	//��ҳ��ѯ�û������Ĺ���
	public List<Doc> findDocByPage(String username , int pageNo , int pageSize);
	//��ҳ��ѯ�û�ָ��ʱ��η����Ĺ���
	public List<Doc> findDocByPageInTime(String username , int pageNo , int pageSize , Date from , Date to);
	//�����û���������
	public long findCountByUser(String username);
	//�����û�ָ��ʱ��η�������
	public long findCountByUserInTime(String username , Date from , Date to);
	//��ҳ��ѯ���û���������
	public List<Doc> findApproveDocByPage(String username , int pageNo , int pageSize);
	//��ѯ���û�������������
	public long findCountNeedApprove(String username);
	//��ҳ��ѯ���û�������
	public List<Doc> findProcessDocByPage(String username , int pageNo , int apgeSize);
	//��ѯ���û�����������
	public long findCountNeedProcess(String username);
}
