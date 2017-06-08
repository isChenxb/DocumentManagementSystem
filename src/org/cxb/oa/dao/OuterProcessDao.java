package org.cxb.oa.dao;

import org.cxb.oa.bean.OuterProcessInfo;

public interface OuterProcessDao extends BaseDao<OuterProcessInfo> {
	public String getIdNumber(String date);
}
