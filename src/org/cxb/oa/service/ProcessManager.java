package org.cxb.oa.service;

public interface ProcessManager {
	//生成处理
	public String processGenerator(String doc_id , String process_user);
	//公文承办
	public boolean doProcess(String doc_id , String process_message , String process_type , String username);
	//公文退办
	public boolean doNotProcess(String doc_id , String process_message , String process_type , String username);
	//公文委办
	public boolean entrustProcess(String doc_id , String process_message 
			, String process_type , String username , String entrust_user);
}
