package org.cxb.oa.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.cxb.oa.service.DocManager;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

public class InnerDocSelectAction extends ActionSupport {
	private JSONObject json;
	private int rows;
	private int page;
	private String from;
	private String to;
	private DocManager docManager;
	
	public DocManager getDocManager() {
		return docManager;
	}
	public void setDocManager(DocManager docManager) {
		this.docManager = docManager;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
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
	private Date formateDate(String timeStr) {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm");
//		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date result = null;
		try {
			result = format.parse(timeStr);
		} catch (Exception e) {
		}
		return result;
	}
	
	public String myApprove() throws Exception{
		String username = (String) ActionContext.getContext().getSession().get("user");
		Map<String , Object> result = docManager
				.getMyApprovedDocs(username, page, rows, formateDate(from), formateDate(to));
		json = JSONObject.fromObject(result);
		return SUCCESS;
	}
	
	public String myProcess() throws Exception{
		String username = (String) ActionContext.getContext().getSession().get("user");
		Map<String , Object> result = docManager
				.getMyProcessedDocs(username, page, rows, formateDate(from), formateDate(to));
		json = JSONObject.fromObject(result);
		return SUCCESS;
	}
	
}
