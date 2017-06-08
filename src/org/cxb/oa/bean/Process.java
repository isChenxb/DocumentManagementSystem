package org.cxb.oa.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="process")
public class Process {
	@Id
	//办理记录号
	private String process_id;
	@Column(nullable = false)
	//对应公文id
	private String doc_id;
	@Column(nullable = false)
	//办理类型
	private String process_type;
	@Column(nullable = false)
	//办理人
	private String process_user;
	@Column(nullable = false)
	//办理意见
	private String process_message;
	@Column(nullable = false)
	//办理结果
	private String process_result;
	public String getProcess_id() {
		return process_id;
	}
	public void setProcess_id(String process_id) {
		this.process_id = process_id;
	}
	public String getDoc_id() {
		return doc_id;
	}
	public void setDoc_id(String doc_id) {
		this.doc_id = doc_id;
	}
	public String getProcess_type() {
		return process_type;
	}
	public void setProcess_type(String process_type) {
		this.process_type = process_type;
	}
	public String getProcess_user() {
		return process_user;
	}
	public void setProcess_user(String process_user) {
		this.process_user = process_user;
	}
	public String getProcess_message() {
		return process_message;
	}
	public void setProcess_message(String process_message) {
		this.process_message = process_message;
	}
	public String getProcess_result() {
		return process_result;
	}
	public void setProcess_result(String process_result) {
		this.process_result = process_result;
	}
	
	
}
