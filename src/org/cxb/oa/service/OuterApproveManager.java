package org.cxb.oa.service;

public interface OuterApproveManager {
	// 生成审核
	public String ApproveGenerator(String approve_user, String doc_id);

	// 生成下一个审核
	public String nextApproveGenerator(String lastApprove_id, String approve_user, String doc_id);
}
