package org.cxb.oa.dao;


import java.util.Date;
import java.util.List;

import org.cxb.oa.bean.Doc;

public interface DocDao extends BaseDao<Doc>{
	public String getIdNumber(String date);
	//分页查询用户发出的公文
	public List<Doc> findDocByPage(String username , int pageNo , int pageSize);
	//分页查询用户指定时间段发出的公文
	public List<Doc> findDocByPageInTime(String username , int pageNo , int pageSize , Date from , Date to);
	//查找用户发文总数
	public long findCountByUser(String username);
	//查找用户指定时间段发文总数
	public long findCountByUserInTime(String username , Date from , Date to);
	//分页查询待用户审批公文
	public List<Doc> findApproveDocByPage(String username , int pageNo , int pageSize);
	//查询待用户审批公文总数
	public long findCountNeedApprove(String username);
	//分页查询待用户办理公文
	public List<Doc> findProcessDocByPage(String username , int pageNo , int apgeSize);
	//查询待用户办理公文总数
	public long findCountNeedProcess(String username);
}
