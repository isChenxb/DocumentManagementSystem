package org.cxb.oa.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.cxb.oa.service.ApproveManager;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class InnerApproveAction extends ActionSupport {
	private String doc_id;
	private Boolean approve_result;
	private String approve_message;
	private ApproveManager approveManager;
	private InputStream result;
	
	
	
	public InputStream getResult() {
		return result;
	}



	public void setResult(InputStream result) {
		this.result = result;
	}



	public String getDoc_id() {
		return doc_id;
	}



	public void setDoc_id(String doc_id) {
		this.doc_id = doc_id;
	}



	public Boolean getApprove_result() {
		return approve_result;
	}



	public void setApprove_result(Boolean approve_result) {
		this.approve_result = approve_result;
	}



	public String getApprove_message() {
		return approve_message;
	}



	public void setApprove_message(String approve_message) {
		this.approve_message = approve_message;
	}



	public ApproveManager getApproveManager() {
		return approveManager;
	}



	public void setApproveManager(ApproveManager approveManager) {
		this.approveManager = approveManager;
	}



	@Override
	public String execute() throws Exception {
		String user = (String)ActionContext.getContext().getSession().get("user");
		boolean approveResult = approveManager
				.submitApprove(doc_id, user, approve_result, approve_message);
		if (approveResult)
			result = new ByteArrayInputStream("审批提交成功".getBytes("UTF-8"));
		else
			result = new ByteArrayInputStream("审批提交失败".getBytes("UTF-8")); 
		return SUCCESS;
	}
}
