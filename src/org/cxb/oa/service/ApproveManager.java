package org.cxb.oa.service;


public interface ApproveManager {
	//�������
	public String ApproveGenerator(String approve_user , String doc_id);
	//������һ�����
	public String nextApproveGenerator(String lastApprove_id , String approve_user , String doc_id);
	//�ύ��˽��
	public boolean submitApprove(String doc_id , String username , boolean approve_result , String approve_message) throws Exception;
}
