package org.cxb.oa.action;

import java.util.Map;

import org.cxb.oa.service.DocManager;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

public class MyInnerProcessAction extends ActionSupport {
	private DocManager docManager;
	private JSONObject json;
	private int rows;
	private int page;
	
	public DocManager getDocManager() {
		return docManager;
	}

	public void setDocManager(DocManager docManager) {
		this.docManager = docManager;
	}

	public JSONObject getJson() {
		return json;
	}

	public void setJson(JSONObject json) {
		this.json = json;
	}

	

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	@Override
	public String execute() throws Exception {
		String username = (String)ActionContext
				.getContext().getSession().get("user");
		Map<String , Object> result = docManager.getMyProcessDocs(username, page, rows);
		json = JSONObject.fromObject(result);
		return SUCCESS;
	}
}
