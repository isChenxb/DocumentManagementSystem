package org.cxb.oa.action;

import org.cxb.oa.service.UserManager;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
	private String username;
	private String password;
	private UserManager userManager;
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
	

	@Override
	public String execute() throws Exception {
		if (ActionContext.getContext().getSession().get("user") != null)
			return SUCCESS;
		String username = getUsername();
		String password = getPassword();
		if (userManager.login(username, password)) {
			String name = userManager.getName(username);
			ActionContext.getContext().getSession().put("user", username);
			ActionContext.getContext().getSession().put("name", name);
			return SUCCESS;
		}
		else
			return INPUT;
	}
}
