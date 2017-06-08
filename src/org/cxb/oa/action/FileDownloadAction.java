package org.cxb.oa.action;

import java.io.InputStream;

import org.apache.struts2.ServletActionContext;
import org.cxb.oa.service.DocManager;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class FileDownloadAction extends ActionSupport {
	private String inputPath;
	private String fileName;
	private String doc_id;
	private DocManager docManager;
	
	
	
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getInputPath() {
		return inputPath;
	}

	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}
	
	public InputStream getTargetFile() throws Exception {
		String username = (String)ActionContext.getContext().getSession().get("user");
		String file = inputPath + "/" + docManager.downloadFile(doc_id, username);
		return ServletActionContext.getServletContext()
				.getResourceAsStream(file);
	}
}
