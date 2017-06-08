package org.cxb.oa.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.cxb.oa.bean.OuterDoc;
import org.cxb.oa.service.OuterDocManager;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LogOuterDocAction extends ActionSupport {
	private OuterDocManager outerDocManager;
	private InputStream result;
	private String doc_id;
	private String doc_name;
	private String from_dep;
	private String from_user;
	private String start_time;
	private String target_dep;
	private String target_user;
	private String emergency;
	private String doc_des;
	private String others;
	private File upload;
	private String uploadContentType;
	private String uploadFileName;
	private String savePath;
	
	
	
	public OuterDocManager getOuterDocManager() {
		return outerDocManager;
	}

	public void setOuterDocManager(OuterDocManager outerDocManager) {
		this.outerDocManager = outerDocManager;
	}

	public String getSavePath() {
		return ServletActionContext.getServletContext().getRealPath(savePath);
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	
	
	
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

	public String getDoc_name() {
		return doc_name;
	}

	public void setDoc_name(String doc_name) {
		this.doc_name = doc_name;
	}

	public String getFrom_dep() {
		return from_dep;
	}

	public void setFrom_dep(String from_dep) {
		this.from_dep = from_dep;
	}

	public String getFrom_user() {
		return from_user;
	}

	public void setFrom_user(String from_user) {
		this.from_user = from_user;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getTarget_dep() {
		return target_dep;
	}

	public void setTarget_dep(String target_dep) {
		this.target_dep = target_dep;
	}

	public String getTarget_user() {
		return target_user;
	}

	public void setTarget_user(String target_user) {
		this.target_user = target_user;
	}

	public String getEmergency() {
		return emergency;
	}

	public void setEmergency(String emergency) {
		this.emergency = emergency;
	}

	public String getDoc_des() {
		return doc_des;
	}

	public void setDoc_des(String doc_des) {
		this.doc_des = doc_des;
	}

	public String getOthers() {
		return others;
	}

	public void setOthers(String others) {
		this.others = others;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	@Override
	public String execute() throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		 Date date = dateFormat.parse(start_time);
		 OuterDoc doc = new OuterDoc();
		 doc.setFrom_dep(from_dep);
		 doc.setDoc_id(doc_id);
		 doc.setDoc_name(doc_name);
		 doc.setFrom_user(from_user);
		 doc.setStart_time(date);
		 doc.setTarget_dep(target_dep);
		 doc.setTarget_user(target_user);
		 doc.setEmergency(emergency);
		 doc.setDoc_des(doc_des);
		 doc.setOthers(others);
		 
		 String username = (String)ActionContext.getContext().getSession().get("user");
		 boolean logResult = 
				 outerDocManager.docLog(doc, getUpload(), getUploadFileName(), getSavePath(), username);
		 if(logResult)
			 result = new ByteArrayInputStream("公文登记成功".getBytes("UTF-8"));
		 else
			 result = new ByteArrayInputStream("公文登记失败".getBytes("UTF-8"));
		 return SUCCESS;
	}

}
