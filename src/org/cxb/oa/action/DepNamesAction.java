package org.cxb.oa.action;

import java.util.List;
import java.util.Map;

import org.cxb.oa.service.DepManager;

import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class DepNamesAction extends ActionSupport {
	private DepManager depManager;
	private JSONArray json;
	
	
	public JSONArray getJson() {
		return json;
	}


	public void setJson(JSONArray json) {
		this.json = json;
	}


	public DepManager getDepManager() {
		return depManager;
	}


	public void setDepManager(DepManager depManager) {
		this.depManager = depManager;
	}


	@Override
	public String execute() throws Exception {
		List list = depManager.getAllDepNames();
		json = JSONArray.fromObject(list);
		return SUCCESS;
	}
}
