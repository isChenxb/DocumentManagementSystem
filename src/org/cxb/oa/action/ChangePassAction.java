package org.cxb.oa.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.cxb.oa.service.UserManager;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ChangePassAction extends ActionSupport {
	private UserManager userManager;
	private String old_pass;
	private String new_pass1;
	private String new_pass2;
	private InputStream result;
	
	
	
	
	
	public UserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public String getOld_pass() {
		return old_pass;
	}

	public void setOld_pass(String old_pass) {
		this.old_pass = old_pass;
	}

	public String getNew_pass1() {
		return new_pass1;
	}

	public void setNew_pass1(String new_pass1) {
		this.new_pass1 = new_pass1;
	}

	public String getNew_pass2() {
		return new_pass2;
	}

	public void setNew_pass2(String new_pass2) {
		this.new_pass2 = new_pass2;
	}

	public InputStream getResult() {
		return result;
	}

	public void setResult(InputStream result) {
		this.result = result;
	}

	@Override
	public String execute() throws Exception {
		String username = (String) ActionContext.getContext().getSession().get("user");
		if (!userManager.checkOldPass(username, old_pass)) {
			result = new ByteArrayInputStream("初始密码错误".getBytes("UTF-8"));			
			return SUCCESS;
		}
		if (!new_pass1.equals(new_pass2)) {
			result = new ByteArrayInputStream("两次密码输入不一致".getBytes("UTF-8"));			
			return SUCCESS;
		}
		if (userManager.changePass(username, new_pass1)) {
			result = new ByteArrayInputStream("密码修改成功".getBytes("UTF-8"));	
		}
		return SUCCESS;
	}
	
}
