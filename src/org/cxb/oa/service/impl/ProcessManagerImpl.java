package org.cxb.oa.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.cxb.oa.bean.Doc;
import org.cxb.oa.bean.ProcessInfo;
import org.cxb.oa.dao.DocDao;
import org.cxb.oa.dao.ProcessDao;
import org.cxb.oa.service.ProcessManager;
import org.cxb.oa.util.DocUtils;

public class ProcessManagerImpl implements ProcessManager {
	private ProcessDao processDao;
	private DocDao docDao;
	
	

	public DocDao getDocDao() {
		return docDao;
	}

	public void setDocDao(DocDao docDao) {
		this.docDao = docDao;
	}

	public ProcessDao getProcessDao() {
		return processDao;
	}

	public void setProcessDao(ProcessDao processDao) {
		this.processDao = processDao;
	}
	
	//生成处理号
	private String processIdGenerator() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String date = format.format(new Date());
		String lastId = processDao.getIdNumber(date);
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
		ProcessInfo process = new ProcessInfo();
		
		process.setProcess_id(processIdGenerator());
		process.setDoc_id(doc_id);
		process.setProcess_result(false);
		process.setProcess_user(process_user);
		processDao.save(process);
		return process.getProcess_id();
	}

	@Override
	public boolean doProcess(String doc_id, String process_message, String process_type , String username) {
		Doc doc = docDao.get(Doc.class , doc_id);
		if (!(doc.getApprove_status() && !doc.getProcess_status()))
			return false;
		String[] process_log = doc.getProcess_log().split(",");
		ProcessInfo process = processDao.get(ProcessInfo.class, process_log[process_log.length - 1]);
		if (process.getProcess_user().equals(username)) {
			process.setProcess_result(true);
			process.setProcess_message(process_message);
			process.setProcess_type(process_type);
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String time = format.format(date);
			Date process_date;
			try {
				process_date = format.parse(time);
				process.setProcess_date(process_date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			processDao.update(process);
			doc.setProcess_status(true);
			doc.setStatus(DocUtils.PROCESS_OVER);
			docDao.update(doc);
			return true;
		}
		return false;
	}

	@Override
	public boolean doNotProcess(String doc_id, String process_message, String process_type, String username) {
		Doc doc = docDao.get(Doc.class, doc_id);
		if (!(doc.getApprove_status() && !doc.getProcess_status()))
			return false;
		String[] process_log = doc.getProcess_log().split(",");
		ProcessInfo process = processDao.get(ProcessInfo.class, process_log[process_log.length - 1]);
		if (process.getProcess_user().equals(username)) {
			process.setProcess_message(process_message);
			process.setProcess_type(process_type);
			process.setProcess_result(false);
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String time = format.format(date);
			Date process_date;
			try {
				process_date = format.parse(time);
				process.setProcess_date(process_date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			processDao.update(process);
			doc.setProcess_status(false);
			doc.setStatus(DocUtils.PROCESS_FAIL);
			doc.setInprocess("null");
			docDao.update(doc);
			return true;
		}
		return false;
	}

	@Override
	public boolean entrustProcess(String doc_id, String process_message, String process_type, String username,
			String entrust_user) {
		Doc doc = docDao.get(Doc.class, doc_id);
		if (!(doc.getApprove_status() && !doc.getProcess_status()))
			return false;
		String[] process_log = doc.getProcess_log().split(",");
		ProcessInfo process = processDao.get(ProcessInfo.class, process_log[process_log.length - 1]);
		if (process.getProcess_user().equals(username)) {
			process.setProcess_message(process_message);
			process.setProcess_type(process_type);
			process.setProcess_result(true);
			process.setEntrust_user(entrust_user);
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String time = format.format(date);
			Date process_date;
			try {
				process_date = format.parse(time);
				process.setProcess_date(process_date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			processDao.update(process);
			String newProcessId = processGenerator(doc_id , entrust_user);
			String newProcessLog = doc.getProcess_log() + "," + newProcessId;
			doc.setProcess_log(newProcessLog);
			doc.setInprocess(entrust_user);
			docDao.update(doc);
			return true;
		}
		return false;
	}
	
}
