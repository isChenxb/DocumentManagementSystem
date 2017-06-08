package org.cxb.oa.dao;

import java.util.Date;
import java.util.List;

import org.cxb.oa.bean.Approve;

public interface ApproveDao extends BaseDao<Approve>{
	public String getIdNumber(String date);
	//��ҳ��ѯָ��ʱ���ڵ��û���������
	public List<Approve> findApprovedByPage(String username , int pageNo , int pageSize , Date from , Date to);
	//��ѯָ��ʱ�����û�������������
	public long findCountApproved(String username , Date from , Date to);
}
