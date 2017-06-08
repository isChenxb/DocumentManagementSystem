package org.cxb.oa.service;


public interface ApproveManager {
	//生成审核
	public String ApproveGenerator(String approve_user , String doc_id);
	//生成下一个审核
	public String nextApproveGenerator(String lastApprove_id , String approve_user , String doc_id);
	//提交审核结果
	public boolean submitApprove(String doc_id , String username , boolean approve_result , String approve_message) throws Exception;
}
