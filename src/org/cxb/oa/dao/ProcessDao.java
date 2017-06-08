package org.cxb.oa.dao;

import java.util.Date;
import java.util.List;

import org.cxb.oa.bean.ProcessInfo;

public interface ProcessDao extends BaseDao<ProcessInfo> {
	public String getIdNumber(String date);

	// ��ҳ��ѯָ��ʱ���ڵ��û��������İ���
	public List<ProcessInfo> findProcessedByPage(String username, int pageNo, int pageSize, Date from, Date to);

	// ��ѯָ��ʱ�����û����İ�������
	public long findCountProcessed(String username, Date from, Date to);
}
