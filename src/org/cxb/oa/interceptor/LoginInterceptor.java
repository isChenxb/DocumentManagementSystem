package org.cxb.oa.interceptor;


import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext act = invocation.getInvocationContext();
		String user = (String) act.getSession().get("user");
		if (user != null)
			return invocation.invoke();
		return Action.LOGIN;
	}
	
}
