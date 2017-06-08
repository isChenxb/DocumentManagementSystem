package org.cxb.oa.dao;

import org.cxb.oa.bean.OuterApprove;

public interface OuterApproveDao extends BaseDao<OuterApprove> {
	public String getIdNumber(String date);
}
