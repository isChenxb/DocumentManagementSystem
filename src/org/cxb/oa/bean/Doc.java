package org.cxb.oa.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "doc_info")
public class Doc {
	@Id
	// ���ı��
	private String doc_id;
	@Column(nullable = false)
	//��������
	private String doc_type;
	@Column(nullable = false)
	// ��������
	private String doc_name;
	@Column(nullable = false)
	//�����̶�
	private String emergency;
	public String getEmergency() {
		return emergency;
	}
	public void setEmergency(String emergency) {
		this.emergency = emergency;
	}
	@Column(nullable = false)
	// ���Ĳ���
	private String dep_id;
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	// ���ʱ��
	private Date start_time;
	@Column(nullable = false)
	// ����ժҪ
	private String doc_des;

	@Column(nullable = false)
	// �����
	private String from_user;
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	// �Ǽ�ʱ��
	private Date log_time;
	@Column(nullable = false)
	// �Ǽ���
	private String log_user;
	@Column(nullable = false)
	// �������
	private String target_user;
	@Column(nullable = false)
	//�������
	private String target_dep;
	@Column(nullable = false)
	//�ļ���
	private String file_name;
	@Column(nullable = false)
	// �ļ�·��
	private String file_path;
	@Column(nullable = false)
	// �������
	private Boolean process_status;
	@Column(nullable = false)
	//������
	private Boolean approve_status;
	@Column(nullable = false)
	// ��ǰ��������
	private String inprocess;
	@Column(nullable = false)
	// ��������
	private String process_log;
	@Column(nullable = false)
	//�������
	private String approve_log;
	//��ע
	private String others;
	@Column(nullable = false)
	//����״̬
	private String status;
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDoc_id() {
		return doc_id;
	}
	public void setDoc_id(String doc_id) {
		this.doc_id = doc_id;
	}
	public String getDoc_type() {
		return doc_type;
	}
	public void setDoc_type(String doc_type) {
		this.doc_type = doc_type;
	}
	public String getDoc_name() {
		return doc_name;
	}
	public void setDoc_name(String doc_name) {
		this.doc_name = doc_name;
	}
	public String getDep_id() {
		return dep_id;
	}
	public void setDep_id(String dep_id) {
		this.dep_id = dep_id;
	}
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	public String getDoc_des() {
		return doc_des;
	}
	public void setDoc_des(String doc_des) {
		this.doc_des = doc_des;
	}
	public String getFrom_user() {
		return from_user;
	}
	public void setFrom_user(String from_user) {
		this.from_user = from_user;
	}
	public Date getLog_time() {
		return log_time;
	}
	public void setLog_time(Date log_time) {
		this.log_time = log_time;
	}
	public String getLog_user() {
		return log_user;
	}
	public void setLog_user(String log_user) {
		this.log_user = log_user;
	}
	public String getTarget_user() {
		return target_user;
	}
	public void setTarget_user(String target_user) {
		this.target_user = target_user;
	}
	public String getTarget_dep() {
		return target_dep;
	}
	public void setTarget_dep(String target_dep) {
		this.target_dep = target_dep;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public Boolean getProcess_status() {
		return process_status;
	}
	public void setProcess_status(Boolean process_status) {
		this.process_status = process_status;
	}
	public Boolean getApprove_status() {
		return approve_status;
	}
	public void setApprove_status(Boolean approve_status) {
		this.approve_status = approve_status;
	}
	public String getInprocess() {
		return inprocess;
	}
	public void setInprocess(String inprocess) {
		this.inprocess = inprocess;
	}
	public String getProcess_log() {
		return process_log;
	}
	public void setProcess_log(String process_log) {
		this.process_log = process_log;
	}
	public String getApprove_log() {
		return approve_log;
	}
	public void setApprove_log(String approve_log) {
		this.approve_log = approve_log;
	}
	public String getOthers() {
		return others;
	}
	public void setOthers(String others) {
		this.others = others;
	}
	
	
}
