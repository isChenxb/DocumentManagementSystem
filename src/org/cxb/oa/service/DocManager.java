package org.cxb.oa.service;


import java.io.File;
import java.util.Date;
import java.util.Map;

import org.cxb.oa.bean.Doc;

public interface DocManager {
	//�ڲ����ĵǼ�
	public boolean innerDocLog(Doc doc, File file , String fileName , String path , String username);
	//��ȡ�û��������ڲ�����
	public Map<String , Object> getMyInngerDocs(String username , int pageNo , int pageSize);
	//��ȡָ��ʱ����û��������ڲ�����
	public Map<String , Object> getMyInnerDocsByTime(String username , int pageNo , int pageSize , Date from , Date to);
	//��ȡָ�����ĵ���ϸ��Ϣ
	public Map<String , String> getInnerDocDetail(String doc_id , String username);
	//���ع���
	public String downloadFile(String doc_id , String username);
	//��ȡ��ǰ�û��Ĵ���������
	public Map<String , Object> getMyApproveDocs(String username , int pageNo , int pageSize);
	//��ȡ��ǰ�û��������Ĺ���
	public Map<String , Object> getMyApprovedDocs(String username , int pageNo , int pageSize , Date from , Date to);
	//��ȡ��ǰ�û��Ĵ�������
	public Map<String , Object> getMyProcessDocs(String username , int pageNo , int pageSize);
	//��ȡ��ǰ�û�������Ĺ���
	public Map<String , Object> getMyProcessedDocs(String username , int pageNo , int pageSize , Date from , Date to);
}
