package org.cxb.oa.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.cxb.oa.bean.Approve;
import org.cxb.oa.bean.Doc;
import org.cxb.oa.bean.ProcessInfo;
import org.cxb.oa.dao.ApproveDao;
import org.cxb.oa.dao.DocDao;
import org.cxb.oa.dao.ProcessDao;
import org.cxb.oa.service.ApproveManager;
import org.cxb.oa.util.DocUtils;

public class ApproveManagerImpl implements ApproveManager {
	private ApproveDao approveDao;
	private DocDao docDao;
	private ProcessDao processDao;
	

	public ProcessDao getProcessDao() {
		return processDao;
	}

	public void setProcessDao(ProcessDao processDao) {
		this.processDao = processDao;
	}

	public DocDao getDocDao() {
		return docDao;
	}

	public void setDocDao(DocDao docDao) {
		this.docDao = docDao;
	}

	public ApproveDao getApproveDao() {
		return approveDao;
	}

	public void setApproveDao(ApproveDao approveDao) {
		this.approveDao = approveDao;
	}
	
	//生成审核号
	private String ApproveIdGenerator() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String date = format.format(new Date());
		String lastId = approveDao.getIdNumber(date);
		if (lastId == null)
			lastId = "0000";
		lastId = date + lastId;
		return nextApproveId(lastId);
	}
	//生成下一个id
	private String nextApproveId(String id) {
		String date = id.substring(0 , 8);
		String lastId = id.substring(8);
		
		int number=Integer.parseInt(lastId);
        number++;
        String newId=String.valueOf(number);
        int zeroNum=4-newId.length();
        for (int i=0;i<zeroNum;i++){
        	newId="0"+newId;
		}
        newId=date+newId;
		return newId;
	}

	@Override
	public synchronized String ApproveGenerator(String approve_user, String doc_id) {
		Approve approve = new Approve();
		approve.setApprove_id(ApproveIdGenerator());
		approve.setApprove_result(false);
		approve.setApprove_user(approve_user);
		approve.setDoc_id(doc_id);
		approveDao.save(approve);
		return approve.getApprove_id();
	}
	@Override
	public synchronized String nextApproveGenerator(String lastApprove_id , String approve_user , String doc_id) {
		Approve approve = new Approve();
		approve.setApprove_id(nextApproveId(lastApprove_id));
		approve.setApprove_result(false);
		approve.setApprove_user(approve_user);
		approve.setDoc_id(doc_id);
		approveDao.save(approve);
		return approve.getApprove_id();
	}
	
	/**
	 * 提交审核结果
	 * @param doc_id 审核的公文id
	 * @param username 用户名
	 * @param approve_result 审核结果
	 * @param approve_message 审核意见
	 * @return 提交是否成功
	 * @throws ParseException 
	 */
	@Override
	public boolean submitApprove(String doc_id, String username, boolean approve_result, String approve_message) throws ParseException {
		Doc doc = docDao.get(Doc.class , doc_id);
		//公文已审批完成则退出
		if (doc.getApprove_status())
			return false;
		String[] approve_log = doc.getApprove_log().split(",");
		
		boolean result = false;
		for (int i = 0 ; i < approve_log.length ; i++) {
			Approve item = approveDao.get(Approve.class, approve_log[i]);
			//待审批用户为当前用户则开始审批
			if (item.getApprove_date() == null && item.getApprove_user().equals(username)) {
					item.setApprove_result(approve_result);
					item.setApprove_message(approve_message);
					Date date = new Date();
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
					String time = format.format(date);
					Date approve_date = format.parse(time);
					item.setApprove_date(approve_date);
					approveDao.update(item);
					//如果是最后一个审核节点则修改doc状态
					if(i == (approve_log.length - 1)) {
						//审批结果为true
						if (approve_result) {
							doc.setApprove_status(true);
							doc.setStatus(DocUtils.WAIT_PROCESS);
							String[] processLog = doc.getProcess_log().split(",");
							doc.setInprocess(processDao.get(ProcessInfo.class, processLog[0]).getProcess_user());
						//审批结果为false
						} else {
							doc.setStatus(DocUtils.APPROVE_FAIL);
							doc.setInprocess("null");
						}
						docDao.save(doc);
					//不是最后一个审核节点
					} else {
						//审批结果为true
						if (approve_result) {
							doc.setStatus(DocUtils.IN_APPROVE);
							doc.setInprocess(approveDao.get(Approve.class, approve_log[++i]).getApprove_user());
						//审批结果为false
						} else {
							doc.setStatus(DocUtils.APPROVE_FAIL);
							doc.setInprocess("null");
						}
						docDao.save(doc);
					}
					result = true;
					break;
			}
		}
		return result;
	}
	
}
