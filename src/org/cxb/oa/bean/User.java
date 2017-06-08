package org.cxb.oa.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "user_info")
public class User {
	@Id
	// �û���¼��
	private String username;
	@Column(nullable = false)
	private String pass;
	// �û���ʵ����
	@Column(nullable = false)
	private String name;
	// �û����
	@Column(nullable = false)
	private String user_identity;
	// �û�����
	@Column(nullable = false)
	private String user_dep;
	@Column(nullable = false)
	//�û�Ȩ��
	private Integer user_permission;
	// �û��Ա�
	private String user_sex;
	// ��������
	private String email;
	// �绰
	private String tel;
	// ��ַ
	private String address;
	// ����
	@Temporal(TemporalType.DATE)
	private Date birthday;
	//����ָ������·��
	private String approve_path;

	public User() {
	}

	public User(String name, String pass) {
		this.username = name;
		this.pass = pass;
	}
	
	

	public Integer getUser_permission() {
		return user_permission;
	}

	public void setUser_permission(Integer user_permission) {
		this.user_permission = user_permission;
	}

	public String getApprove_path() {
		return approve_path;
	}

	public void setApprove_path(String approve_path) {
		this.approve_path = approve_path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUser_identity() {
		return user_identity;
	}

	public void setUser_identity(String user_identity) {
		this.user_identity = user_identity;
	}

	public String getUser_dep() {
		return user_dep;
	}

	public void setUser_dep(String user_dep) {
		this.user_dep = user_dep;
	}

	public String getUser_sex() {
		return user_sex;
	}

	public void setUser_sex(String user_sex) {
		this.user_sex = user_sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

}
