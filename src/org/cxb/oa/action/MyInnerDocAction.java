package org.cxb.oa.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.cxb.oa.service.DocManager;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

public class MyInnerDocAction extends ActionSupport {
	private DocManager docManager;
	private int rows;
	private int page;
	private String from_time;
	private String to_time;
	private JSONObject json;
	

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getFrom_time() {
		return from_time;
	}

	public void setFrom_time(String from_time) {
		this.from_time = from_time;
	}

	public String getTo_time() {
		return to_time;
	}

	public void setTo_time(String to_time) {
		this.to_time = to_time;
	}



	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public JSONObject getJson() {
		return json;
	}

	public void setJson(JSONObject json) {
		this.json = json;
	}

	public DocManager getDocManager() {
		return docManager;
	}

	public void setDocManager(DocManager docManager) {
		this.docManager = docManager;
	}

	@Override
	public String execute() throws Exception {
		String username = (String) ActionContext.getContext().getSession().get("user");
		Map<String, Object> map = null;
		if (from_time != null && to_time != null) {
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm");
			try {
				Date from = format.parse(from_time);
				Date to = format.parse(to_time);
				map = docManager.getMyInnerDocsByTime(username, page, rows, from, to);
			} catch (Exception e) {
				map = docManager.getMyInngerDocs(username, page, rows);
			}
		} else {
			map = docManager.getMyInngerDocs(username, page, rows);
		}
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}
}
