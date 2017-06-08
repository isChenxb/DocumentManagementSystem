package org.cxb.oa.dao;

import java.util.Date;
import java.util.List;

import org.cxb.oa.bean.ProcessInfo;

public interface ProcessDao extends BaseDao<ProcessInfo> {
	public String getIdNumber(String date);

	// 分页查询指定时间内的用户公文收文办理
	public List<ProcessInfo> findProcessedByPage(String username, int pageNo, int pageSize, Date from, Date to);

	// 查询指定时间内用户收文办理总数
	public long findCountProcessed(String username, Date from, Date to);
}
