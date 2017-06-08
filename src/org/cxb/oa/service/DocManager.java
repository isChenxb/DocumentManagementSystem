package org.cxb.oa.service;


import java.io.File;
import java.util.Date;
import java.util.Map;

import org.cxb.oa.bean.Doc;

public interface DocManager {
	//内部公文登记
	public boolean innerDocLog(Doc doc, File file , String fileName , String path , String username);
	//获取用户发出的内部公文
	public Map<String , Object> getMyInngerDocs(String username , int pageNo , int pageSize);
	//获取指定时间段用户发出的内部公文
	public Map<String , Object> getMyInnerDocsByTime(String username , int pageNo , int pageSize , Date from , Date to);
	//获取指定公文的详细信息
	public Map<String , String> getInnerDocDetail(String doc_id , String username);
	//下载公文
	public String downloadFile(String doc_id , String username);
	//获取当前用户的待审批公文
	public Map<String , Object> getMyApproveDocs(String username , int pageNo , int pageSize);
	//获取当前用户审批过的公文
	public Map<String , Object> getMyApprovedDocs(String username , int pageNo , int pageSize , Date from , Date to);
	//获取当前用户的待处理公文
	public Map<String , Object> getMyProcessDocs(String username , int pageNo , int pageSize);
	//获取当前用户办理过的公文
	public Map<String , Object> getMyProcessedDocs(String username , int pageNo , int pageSize , Date from , Date to);
}
