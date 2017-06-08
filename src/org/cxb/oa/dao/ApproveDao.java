package org.cxb.oa.dao;

import java.util.Date;
import java.util.List;

import org.cxb.oa.bean.Approve;

public interface ApproveDao extends BaseDao<Approve>{
	public String getIdNumber(String date);
	//分页查询指定时间内的用户公文审批
	public List<Approve> findApprovedByPage(String username , int pageNo , int pageSize , Date from , Date to);
	//查询指定时间内用户公文审批总数
	public long findCountApproved(String username , Date from , Date to);
}
