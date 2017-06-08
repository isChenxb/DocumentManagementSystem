package org.cxb.oa.action;

import java.util.List;

import org.cxb.oa.service.UserManager;

import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;

public class UserNameAction extends ActionSupport {
	private UserManager userManager;
	private String dep_id;
	private JSONArray json;
	
	
	
	public JSONArray getJson() {
		return json;
	}


	public void setJson(JSONArray json) {
		this.json = json;
	}


	public UserManager getUserManager() {
		return userManager;
	}


	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
	
	

	public String getDep_id() {
		return dep_id;
	}


	public void setDep_id(String dep_id) {
		this.dep_id = dep_id;
	}


	@Override
	public String execute() throws Exception {
		List list = userManager.getDepUserNames(dep_id);
		json = JSONArray.fromObject(list);
		return super.execute();
	}
}
