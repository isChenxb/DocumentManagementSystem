package org.cxb.oa.service;

public interface OuterApproveManager {
	// �������
	public String ApproveGenerator(String approve_user, String doc_id);

	// ������һ�����
	public String nextApproveGenerator(String lastApprove_id, String approve_user, String doc_id);
}
