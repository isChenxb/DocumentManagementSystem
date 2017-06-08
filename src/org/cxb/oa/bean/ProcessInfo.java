package org.cxb.oa.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="process")
public class ProcessInfo {
	@Id
	//办理记录号
	private String process_id;
	@Column(nullable = false)
	//对应公文id
	private String doc_id;
	//办理类型
	private String process_type;
	@Column(nullable = false)
	//办理人
	private String process_user;
	//办理意见
	private String process_message;
	@Column(nullable = false)
	//办理结果
	private Boolean process_result;
	@Temporal(TemporalType.TIMESTAMP)
	//办理日期
	private Date process_date;
	//委托办理人
	private String entrust_user;
	
	
	public String getEntrust_user() {
		return entrust_user;
	}
	public void setEntrust_user(String entrust_user) {
		this.entrust_user = entrust_user;
	}
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
	public Boolean getProcess_result() {
		return process_result;
	}
	public void setProcess_result(Boolean process_result) {
		this.process_result = process_result;
	}
	public Date getProcess_date() {
		return process_date;
	}
	public void setProcess_date(Date process_date) {
		this.process_date = process_date;
	}
	
	
}
