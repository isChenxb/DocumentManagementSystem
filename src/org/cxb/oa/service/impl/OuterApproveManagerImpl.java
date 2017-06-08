package org.cxb.oa.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.cxb.oa.bean.OuterApprove;
import org.cxb.oa.dao.OuterApproveDao;
import org.cxb.oa.service.OuterApproveManager;

public class OuterApproveManagerImpl implements OuterApproveManager {
	private OuterApproveDao outerApproveDao;

	public OuterApproveDao getOuterApproveDao() {
		return outerApproveDao;
	}

	public void setOuterApproveDao(OuterApproveDao outerApproveDao) {
		this.outerApproveDao = outerApproveDao;
	}
	
	//生成审核号
		private String ApproveIdGenerator() {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			String date = format.format(new Date());
			String lastId = outerApproveDao.getIdNumber(date);
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
		OuterApprove approve = new OuterApprove();
		approve.setApprove_id(ApproveIdGenerator());
		approve.setApprove_result(false);
		approve.setApprove_user(approve_user);
		approve.setDoc_id(doc_id);
		outerApproveDao.save(approve);
		return approve.getApprove_id();
	}
	@Override
	public synchronized String nextApproveGenerator(String lastApprove_id , String approve_user , String doc_id) {
		OuterApprove approve = new OuterApprove();
		approve.setApprove_id(nextApproveId(lastApprove_id));
		approve.setApprove_result(false);
		approve.setApprove_user(approve_user);
		approve.setDoc_id(doc_id);
		outerApproveDao.save(approve);
		return approve.getApprove_id();
	}
	
}
