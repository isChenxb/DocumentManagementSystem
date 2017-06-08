package org.cxb.oa.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.cxb.oa.service.UserManager;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginActionAjax extends ActionSupport{
	private String username;
	private String password;
	private UserManager userManager;
	private InputStream result;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
	
	

	public InputStream getResult() {
		return result;
	}

	public void setResult(InputStream result) {
		this.result = result;
	}

	@Override
	public String execute() throws Exception {
		String username = getUsername();
		String password = getPassword();
		//µÇÂ¼³É¹¦
		if (userManager.login(username, password)) {
			String name = userManager.getName(username);
			ActionContext.getContext().getSession().put("user", username);
			ActionContext.getContext().getSession().put("name", name);
			result = new ByteArrayInputStream("success".getBytes("UTF-8"));
		} else
			result = new ByteArrayInputStream("error".getBytes("UTF-8"));
		return SUCCESS;
	}
}
