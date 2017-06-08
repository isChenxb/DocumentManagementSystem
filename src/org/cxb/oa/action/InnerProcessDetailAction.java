package org.cxb.oa.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.cxb.oa.service.DocManager;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class InnerProcessDetailAction extends ActionSupport {
	private DocManager docManager;
	private String doc_id;
	
	
	public DocManager getDocManager() {
		return docManager;
	}


	public void setDocManager(DocManager docManager) {
		this.docManager = docManager;
	}


	public String getDoc_id() {
		return doc_id;
	}


	public void setDoc_id(String doc_id) {
		this.doc_id = doc_id;
	}


	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String , String> result = docManager.getInnerDocDetail(doc_id, 
				(String)ActionContext.getContext().getSession().get("user"));
		request.setAttribute("doc_id", result.get("doc_id"));
		request.setAttribute("doc_type", result.get("doc_type"));
		request.setAttribute("doc_name", result.get("doc_name"));
		request.setAttribute("emergency", result.get("emergency"));
		request.setAttribute("inprocess", result.get("inprocess"));
		request.setAttribute("target_dep", result.get("target_dep"));
		request.setAttribute("target_user", result.get("target_user"));
		request.setAttribute("doc_des", result.get("doc_des"));
		request.setAttribute("other" , result.get("other"));
		request.setAttribute("from_dep", result.get("from_dep"));
		request.setAttribute("from_uesr", result.get("from_uesr"));
		request.setAttribute("start_time", result.get("start_time"));
		request.setAttribute("log_user", result.get("log_user"));
		request.setAttribute("log_time", result.get("log_time"));
		request.setAttribute("approve_log", result.get("approve_log"));
		request.setAttribute("process_log", result.get("process_log"));
		request.setAttribute("fileName", result.get("fileName"));
		return SUCCESS;
	}
}
