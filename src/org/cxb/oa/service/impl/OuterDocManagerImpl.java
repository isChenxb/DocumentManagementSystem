package org.cxb.oa.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.cxb.oa.bean.Dep;
import org.cxb.oa.bean.OuterDoc;
import org.cxb.oa.bean.User;
import org.cxb.oa.dao.DepDao;
import org.cxb.oa.dao.OuterDocDao;
import org.cxb.oa.dao.UserDao;
import org.cxb.oa.service.OuterApproveManager;
import org.cxb.oa.service.OuterDocManager;
import org.cxb.oa.service.OuterProcessManager;
import org.cxb.oa.util.DocUtils;

public class OuterDocManagerImpl implements OuterDocManager {
	private OuterDocDao outerDocDao;
	private UserDao userDao;
	private DepDao depDao;
	private OuterApproveManager outerApproveManager;
	private OuterProcessManager outerProcessManager;
	
	
	public OuterApproveManager getOuterApproveManager() {
		return outerApproveManager;
	}

	public void setOuterApproveManager(OuterApproveManager outerApproveManager) {
		this.outerApproveManager = outerApproveManager;
	}

	public OuterProcessManager getOuterProcessManager() {
		return outerProcessManager;
	}

	public void setOuterProcessManager(OuterProcessManager outerProcessManager) {
		this.outerProcessManager = outerProcessManager;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public DepDao getDepDao() {
		return depDao;
	}

	public void setDepDao(DepDao depDao) {
		this.depDao = depDao;
	}

	public OuterDocDao getOuterDocDao() {
		return outerDocDao;
	}

	public void setOuterDocDao(OuterDocDao outerDocDao) {
		this.outerDocDao = outerDocDao;
	}

	@Override
	public boolean docLog(OuterDoc doc, File file, String fileName, String path, String username) {
		try {
			User user = userDao.get(User.class, username);
			Dep fromDep = depDao.get(Dep.class, user.getUser_dep());
			Dep targetDep = depDao.get(Dep.class, doc.getTarget_dep());
			// 生成审核流程
			String approve1 = outerApproveManager.ApproveGenerator(fromDep.getIncharge(), doc.getDoc_id());
			String approve2 = outerApproveManager.nextApproveGenerator(approve1, targetDep.getIncharge(), doc.getDoc_id());
			String approveLog = approve1 + "," + approve2;
			// 生成处理流程
			String process = outerProcessManager.processGenerator(doc.getDoc_id(), doc.getTarget_user());
			// 存储文件
			String filePath = fileSave(file, path, fileName, doc.getDoc_id());
			doc.setDoc_type(DocUtils.INNER_DOC);
			doc.setLog_time(doc.getStart_time());
			doc.setLog_user(username);
			doc.setFile_name(fileName);
			doc.setFile_path(filePath);
			doc.setProcess_status(false);
			doc.setApprove_status(false);
			doc.setInprocess(fromDep.getIncharge());
			doc.setApprove_log(approveLog);
			doc.setProcess_log(process);
			doc.setStatus(DocUtils.WAIT_APPROVE);
			outerDocDao.save(doc);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	/**
	 * 公文文件存储
	 * 
	 * @param file
	 *            文件
	 * @param path
	 *            文件存储目录
	 * @param fileName
	 *            文件名
	 * @param doc_id
	 *            公文id
	 * @return 文件最后的存储路径
	 */
	private String fileSave(File file, String path, String fileName, String doc_id) {
		String realPath = path + "\\" + doc_id + fileName;
		try {
			FileOutputStream fos = new FileOutputStream(realPath);
			FileInputStream fis = new FileInputStream(file);
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0)
				fos.write(buffer, 0, len);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return realPath;
	}
	
}
