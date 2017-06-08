package org.cxb.oa.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dep_info")
public class Dep {
	@Id
	private String dep_id;
	@Column(nullable = false)
	//部门名称
	private String dep_name;
	//部门描述
	private String dep_des;
	@Column(nullable = false)
	//负责人
	private String incharge;
	
	public String getDep_id() {
		return dep_id;
	}
	public void setDep_id(String dep_id) {
		this.dep_id = dep_id;
	}
	public String getDep_name() {
		return dep_name;
	}
	public void setDep_name(String dep_name) {
		this.dep_name = dep_name;
	}
	public String getDep_des() {
		return dep_des;
	}
	public void setDep_des(String dep_des) {
		this.dep_des = dep_des;
	}
	public String getIncharge() {
		return incharge;
	}
	public void setIncharge(String incharge) {
		this.incharge = incharge;
	}
	
	
}
