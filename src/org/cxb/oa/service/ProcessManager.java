package org.cxb.oa.service;

public interface ProcessManager {
	//���ɴ���
	public String processGenerator(String doc_id , String process_user);
	//���ĳа�
	public boolean doProcess(String doc_id , String process_message , String process_type , String username);
	//�����˰�
	public boolean doNotProcess(String doc_id , String process_message , String process_type , String username);
	//����ί��
	public boolean entrustProcess(String doc_id , String process_message 
			, String process_type , String username , String entrust_user);
}
