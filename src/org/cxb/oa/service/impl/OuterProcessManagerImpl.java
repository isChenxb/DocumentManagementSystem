package org.cxb.oa.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.cxb.oa.bean.OuterProcessInfo;
import org.cxb.oa.dao.OuterProcessDao;
import org.cxb.oa.service.OuterProcessManager;

public class OuterProcessManagerImpl implements OuterProcessManager {
	private OuterProcessDao outerProcessDao;

	public OuterProcessDao getOuterProcessDao() {
		return outerProcessDao;
	}

	public void setOuterProcessDao(OuterProcessDao outerProcessDao) {
		this.outerProcessDao = outerProcessDao;
	}

	//生成处理号
		private String processIdGenerator() {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			String date = format.format(new Date());
			String lastId = outerProcessDao.getIdNumber(date);
			if (lastId == null)
				lastId = "0000";
				
			//生成下一个处理id
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
		public synchronized String processGenerator(String doc_id, String process_user) {
			OuterProcessInfo process = new OuterProcessInfo();
			
			process.setProcess_id(processIdGenerator());
			process.setDoc_id(doc_id);
			process.setProcess_result(false);
			process.setProcess_user(process_user);
			outerProcessDao.save(process);
			return process.getProcess_id();
		}
	
}
