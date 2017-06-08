package org.cxb.oa.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.cxb.oa.service.ProcessManager;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class InnerProcessAction extends ActionSupport {
	private String doc_id;
	private String process_message;
	private String process_type;
	private String entrust_user;
	private ProcessManager processManager;
	private InputStream result;

	public String getDoc_id() {
		return doc_id;
	}

	public void setDoc_id(String doc_id) {
		this.doc_id = doc_id;
	}

	public String getProcess_message() {
		return process_message;
	}

	public void setProcess_message(String process_message) {
		this.process_message = process_message;
	}

	public String getProcess_type() {
		return process_type;
	}

	public void setProcess_type(String process_type) {
		this.process_type = process_type;
	}

	public String getEntrust_user() {
		return entrust_user;
	}

	public void setEntrust_user(String entrust_user) {
		this.entrust_user = entrust_user;
	}

	public ProcessManager getProcessManager() {
		return processManager;
	}

	public void setProcessManager(ProcessManager processManager) {
		this.processManager = processManager;
	}

	public InputStream getResult() {
		return result;
	}

	public void setResult(InputStream result) {
		this.result = result;
	}

	// 公文承办
	public String doProcess() throws Exception {
		String username = (String) ActionContext.getContext().getSession().get("user");
		boolean process_result = processManager.doProcess(doc_id, process_message, process_type, username);
		if (process_result)
			result = new ByteArrayInputStream("办理提交成功".getBytes("UTF-8"));
		else
			result = new ByteArrayInputStream("办理提交失败".getBytes("UTF-8"));
		return SUCCESS;
	}

	// 公文委办
	public String entrustProcess() throws Exception {
		String username = (String) ActionContext.getContext().getSession().get("user");
		boolean process_result = processManager.entrustProcess(doc_id, process_message, process_type, username , entrust_user);
		if (process_result)
			result = new ByteArrayInputStream("办理提交成功".getBytes("UTF-8"));
		else
			result = new ByteArrayInputStream("办理提交失败".getBytes("UTF-8"));
		return SUCCESS;
	}

	// 公文退办
	public String doNotProcess() throws Exception {
		String username = (String) ActionContext.getContext().getSession().get("user");
		boolean process_result = processManager.doNotProcess(doc_id, process_message, process_type, username);
		if (process_result)
			result = new ByteArrayInputStream("办理提交成功".getBytes("UTF-8"));
		else
			result = new ByteArrayInputStream("办理提交失败".getBytes("UTF-8"));
		return SUCCESS;
	}
}
