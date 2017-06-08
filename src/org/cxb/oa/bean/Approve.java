package org.cxb.oa.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "approve_info")
public class Approve {
	@Id
	//审核编号
	private String approve_id;
	@Column(nullable = false)
	//对应公文id
	private String doc_id;
	@Column(nullable = false)
	//办理人
	private String approve_user;
	//办理意见
	private String approve_message;
	@Column(nullable = false)
	//办理结果
	private Boolean approve_result;
	@Temporal(TemporalType.TIMESTAMP)
	//办理日期
	private Date approve_date;
	public String getApprove_id() {
		return approve_id;
	}
	public void setApprove_id(String approve_id) {
		this.approve_id = approve_id;
	}
	public String getDoc_id() {
		return doc_id;
	}
	public void setDoc_id(String doc_id) {
		this.doc_id = doc_id;
	}
	public String getApprove_user() {
		return approve_user;
	}
	public void setApprove_user(String approve_user) {
		this.approve_user = approve_user;
	}
	public String getApprove_message() {
		return approve_message;
	}
	public void setApprove_message(String approve_message) {
		this.approve_message = approve_message;
	}
	public Boolean getApprove_result() {
		return approve_result;
	}
	public void setApprove_result(Boolean approve_result) {
		this.approve_result = approve_result;
	}
	public Date getApprove_date() {
		return approve_date;
	}
	public void setApprove_date(Date approve_date) {
		this.approve_date = approve_date;
	}
	
	
}
