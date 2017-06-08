package org.cxb.oa.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport {
	@Override
	public String execute() throws Exception {
		ActionContext.getContext().getSession().put("user", null);
		return SUCCESS;
	}
}
