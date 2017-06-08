package org.cxb.oa.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cxb.oa.bean.Approve;
import org.cxb.oa.bean.Dep;
import org.cxb.oa.bean.Doc;
import org.cxb.oa.bean.ProcessInfo;
import org.cxb.oa.bean.User;
import org.cxb.oa.dao.ApproveDao;
import org.cxb.oa.dao.DepDao;
import org.cxb.oa.dao.DocDao;
import org.cxb.oa.dao.ProcessDao;
import org.cxb.oa.dao.UserDao;
import org.cxb.oa.service.ApproveManager;
import org.cxb.oa.service.DocManager;
import org.cxb.oa.service.ProcessManager;
import org.cxb.oa.util.DocUtils;

public class DocManagerImpl implements DocManager {
	private DocDao docDao;
	private UserDao userDao;
	private DepDao depDao;
	private ProcessDao processDao;
	private ApproveDao approveDao;
	private ApproveManager approveManager;
	private ProcessManager processManager;

	public ProcessManager getProcessManager() {
		return processManager;
	}

	public void setProcessManager(ProcessManager processManager) {
		this.processManager = processManager;
	}

	public ApproveManager getApproveManager() {
		return approveManager;
	}

	public void setApproveManager(ApproveManager approveManager) {
		this.approveManager = approveManager;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public DocDao getDocDao() {
		return docDao;
	}

	public void setDocDao(DocDao docDao) {
		this.docDao = docDao;
	}

	public DepDao getDepDao() {
		return depDao;
	}

	public void setDepDao(DepDao depDao) {
		this.depDao = depDao;
	}

	public ProcessDao getProcessDao() {
		return processDao;
	}

	public void setProcessDao(ProcessDao processDao) {
		this.processDao = processDao;
	}

	public ApproveDao getApproveDao() {
		return approveDao;
	}

	public void setApproveDao(ApproveDao approveDao) {
		this.approveDao = approveDao;
	}

	// �����ڲ�����id
	private String DocIdGenerator() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String date = format.format(new Date());
		String lastId = docDao.getIdNumber(date);
		if (lastId == null)
			lastId = "0000";

		// ������һ������id
		int number = Integer.parseInt(lastId);
		number++;
		String newId = String.valueOf(number);
		int zeroNum = 4 - newId.length();
		for (int i = 0; i < zeroNum; i++) {
			newId = "0" + newId;
		}
		newId = date + newId;
		return newId;
	}

	/**
	 * �����ļ��洢
	 * 
	 * @param file
	 *            �ļ�
	 * @param path
	 *            �ļ��洢Ŀ¼
	 * @param fileName
	 *            �ļ���
	 * @param doc_id
	 *            ����id
	 * @return �ļ����Ĵ洢·��
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

	/**
	 * �ڲ����ĵǼ�
	 * 
	 * @param doc
	 *            ���Ķ���
	 * @param file
	 *            Ҫ�洢���ļ�����
	 * @param fileName
	 *            �ϴ�ʱ���ļ���
	 * @param path
	 *            �ļ��洢·��
	 * @param username
	 *            �����û���
	 * @return �������
	 */
	@Override
	public synchronized boolean innerDocLog(Doc doc, File file, String fileName, String path, String username) {
		try {
			User user = userDao.get(User.class, username);
			Dep fromDep = depDao.get(Dep.class, user.getUser_dep());
			Dep targetDep = depDao.get(Dep.class, doc.getTarget_dep());
			String doc_id = DocIdGenerator();
			String approveLog = "";
			// �����������
			if (user.getApprove_path() == null) {
				String approve1 = approveManager.ApproveGenerator(fromDep.getIncharge(), doc_id);
				String approve2 = approveManager.nextApproveGenerator(approve1, targetDep.getIncharge(), doc_id);
				approveLog = approve1 + "," + approve2;
				doc.setInprocess(fromDep.getIncharge());
			} else {
				String[] approvers = user.getApprove_path().split(",");
				StringBuilder sb = new StringBuilder();
				String lastApprove = "";
				for (int i = 0 ; i < approvers.length ; i++) {
					if (i == 0) {
						lastApprove = approveManager.ApproveGenerator(approvers[i], doc_id);
					} else {
						lastApprove = approveManager.nextApproveGenerator(lastApprove, approvers[i], doc_id);
					}
					sb.append(lastApprove);
					if (i != approvers.length - 1)
						sb.append(",");
				}
				approveLog = sb.toString();
				doc.setInprocess(approvers[0]);
			}
			// ���ɴ�������
			String process = processManager.processGenerator(doc_id, doc.getTarget_user());
			// �洢�ļ�
			String filePath = fileSave(file, path, fileName, doc_id);
			doc.setDoc_id(doc_id);
			doc.setDoc_type(DocUtils.INNER_DOC);
			doc.setDep_id(user.getUser_dep());
			doc.setLog_time(doc.getStart_time());
			doc.setLog_user(username);
			doc.setFile_name(fileName);
			doc.setFile_path(filePath);
			doc.setProcess_status(false);
			doc.setApprove_status(false);
//			doc.setInprocess(fromDep.getIncharge());
			doc.setApprove_log(approveLog);
			doc.setProcess_log(process);
			doc.setStatus(DocUtils.WAIT_APPROVE);
			docDao.save(doc);
		} catch (Exception e) {
			return false;
		}
		return true;
	}


	/**
	 * ��ҳ��ȡ���������Ϣ
	 * 
	 * @param username
	 *            ��ǰ�û���
	 * @param pageNo
	 *            ��ǰҳ��
	 * @param pageSize
	 *            ҳ���¼��
	 */
	@Override
	public Map<String, Object> getMyInngerDocs(String username, int pageNo, int pageSize) {
		List<Doc> docList = docDao.findDocByPage(username, pageNo, pageSize);
		long total = docDao.findCountByUser(username);
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		for (Doc item : docList) {
			Dep fromDep = depDao.get(Dep.class, item.getDep_id());
			Dep targetDep = depDao.get(Dep.class, item.getTarget_dep());
			User inprocess = userDao.get(User.class, item.getInprocess());
			Map<String, Object> itemMap = new HashMap<String, Object>();
			itemMap.put("doc_id", item.getDoc_id());
			itemMap.put("doc_type", item.getDoc_type());
			itemMap.put("doc_name", item.getDoc_name());
			itemMap.put("emergency", item.getEmergency());
			itemMap.put("from_dep", fromDep.getDep_name());
			itemMap.put("target_dep", targetDep.getDep_name());
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String start_time = format.format(item.getStart_time());
			itemMap.put("start_time", start_time);
			itemMap.put("inprocess", inprocess.getName());
			itemMap.put("status", item.getStatus());
			mapList.add(itemMap);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", total);
		result.put("rows", mapList);
		return result;
	}
	
	/**
	 * ��ҳ��ȡָ��ʱ�乫�������Ϣ
	 * 
	 * @param username
	 *            ��ǰ�û���
	 * @param pageNo
	 *            ��ǰҳ��
	 * @param pageSize
	 *            ҳ���¼��
	 * @param from 
	 *            ��ʼʱ��
	 * @param to 
	 *            ����ʱ��
	 */
	@Override
	public Map<String, Object> getMyInnerDocsByTime(String username, int pageNo, int pageSize, Date from, Date to) {
		List<Doc> docList = docDao.findDocByPageInTime(username, pageNo, pageSize , from , to);
		long total = docDao.findCountByUserInTime(username , from , to);
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		for (Doc item : docList) {
			Dep fromDep = depDao.get(Dep.class, item.getDep_id());
			Dep targetDep = depDao.get(Dep.class, item.getTarget_dep());
			User inprocess = userDao.get(User.class, item.getInprocess());
			Map<String, Object> itemMap = new HashMap<String, Object>();
			itemMap.put("doc_id", item.getDoc_id());
			itemMap.put("doc_type", item.getDoc_type());
			itemMap.put("doc_name", item.getDoc_name());
			itemMap.put("emergency", item.getEmergency());
			itemMap.put("from_dep", fromDep.getDep_name());
			itemMap.put("target_dep", targetDep.getDep_name());
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String start_time = format.format(item.getStart_time());
			itemMap.put("start_time", start_time);
			itemMap.put("inprocess", inprocess.getName());
			itemMap.put("status", item.getStatus());
			mapList.add(itemMap);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", total);
		result.put("rows", mapList);
		return result;
	}
	
	/**
	 * ��ȡ�ڲ����ĵ���ϸ��Ϣ
	 * 
	 * @Param doc_id ����id
	 * @param username
	 *            �û���
	 * @return ������ϸ��Ϣ
	 */
	@Override
	public Map<String, String> getInnerDocDetail(String doc_id, String username) {
		Doc doc = docDao.get(Doc.class, doc_id);
		User inprocess = userDao.get(User.class, doc.getInprocess());
		User target_user = userDao.get(User.class, doc.getTarget_user());
		// User from_user = userDao.get(User.class, doc.getFrom_user());
		User log_user = userDao.get(User.class, doc.getLog_user());
		Dep from_dep = depDao.get(Dep.class, doc.getDep_id());
		Dep target_dep = depDao.get(Dep.class, doc.getTarget_dep());
		String approve_logs = formatApprove_log(doc.getApprove_log());
		String process_logs = formatProcess_log(doc.getProcess_log());

		Map<String, String> result = new HashMap<String, String>();
		result.put("doc_id", doc.getDoc_id());
		result.put("doc_type", doc.getDoc_type());
		result.put("doc_name", doc.getDoc_name());
		result.put("emergency", doc.getEmergency());
		result.put("inprocess", inprocess.getName());
		result.put("target_dep", target_dep.getDep_name());
		result.put("target_user", target_user.getName());
		result.put("doc_des", doc.getDoc_des());
		result.put("other", doc.getOthers());
		result.put("from_dep", from_dep.getDep_name());
		result.put("from_uesr", doc.getFrom_user());
		result.put("start_time", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(doc.getStart_time()));
		result.put("log_user", log_user.getName());
		result.put("log_time", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(doc.getLog_time()));
		result.put("approve_log", approve_logs);
		result.put("process_log", process_logs);
		result.put("filePath", doc.getFile_path());
		result.put("fileName", doc.getFile_name());
		return result;
	}

	/**
	 * ��ʽ��process_log
	 * 
	 * @param process_logs
	 * @return ��ʽ������ַ���
	 */
	private String formatProcess_log(String process_logs) {
		StringBuilder result = new StringBuilder();
		String[] process = process_logs.split(",");
		for (int i = 0; i < process.length; i++) {
			ProcessInfo proInf = processDao.get(ProcessInfo.class, process[i]);
			User process_user = userDao.get(User.class, proInf.getProcess_user());
			Dep process_dep = depDao.get(Dep.class, process_user.getUser_dep());
			if (proInf.getProcess_date() == null)
				result.append(process_dep.getDep_name() + "��" + process_user.getName() + "�����졿");
			else {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String process_time = format.format(proInf.getProcess_date());
				String process_type = proInf.getProcess_type();
				if ("����ί��".equals(process_type)) {
					User entrust_user = userDao.get(User.class, proInf.getEntrust_user());
					result.append(process_dep.getDep_name() + "��" + process_user.getName() + "���������ͣ�" + process_type
							+ "��ί�а����ˣ�" + entrust_user.getName() + "������ʱ�䣺" + process_time + "�����������"
							+ proInf.getProcess_message() + "��");
				} else
					result.append(process_dep.getDep_name() + "��" + process_user.getName() + "���������ͣ�" + process_type
							+ "������ʱ�䣺" + process_time + "�����������" + proInf.getProcess_message() + "��");
			}
			if (i != (process.length - 1)) {
				result.append("-->");
			}
		}
		return result.toString();
	}

	/**
	 * ��ʽ��approve_log
	 * 
	 * @param approve_logs
	 * @return ��ʽ������ַ���
	 */
	private String formatApprove_log(String approve_logs) {
		StringBuilder sBuilder = new StringBuilder();
		String[] approves = approve_logs.split(",");
		for (int i = 0; i < approves.length; i++) {
			Approve approve = approveDao.get(Approve.class, approves[i]);
			User approve_user = userDao.get(User.class, approve.getApprove_user());
			Dep approve_dep = depDao.get(Dep.class, approve_user.getUser_dep());
			if (approve.getApprove_date() == null)
				sBuilder.append(approve_dep.getDep_name() + "��" + approve_user.getName() + "������ˡ�");
			else {
				String approve_result = (approve.getApprove_result()) ? "ͬ��" : "��ͬ��";
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String approve_time = format.format(approve.getApprove_date());
				sBuilder.append(approve_dep.getDep_name() + "��" + approve_user.getName() + "����˽����" + approve_result
						+ "����������" + approve.getApprove_message() + "�����ʱ�䣺" + approve_time + "��");
			}
			if (i != (approves.length - 1))
				sBuilder.append("-->");
		}
		return sBuilder.toString();
	}

	@Override
	public String downloadFile(String doc_id, String username) {
		Doc doc = docDao.get(Doc.class, doc_id);
		String fileName = doc_id + doc.getFile_name();
		return fileName;
	}

	/**
	 * ��ȡ����ǰ�û���������
	 * 
	 * @param username
	 *            ��ǰ�û��û���
	 * @param pageNo
	 *            ��ǰҳ��
	 * @param pageSize
	 *            ÿҳ��ʾ��������
	 * @return ���صĹ�����Ϣ
	 */
	@Override
	public Map<String, Object> getMyApproveDocs(String username, int pageNo, int pageSize) {
		List<Doc> docList = docDao.findApproveDocByPage(username, pageNo, pageSize);
		Long total = docDao.findCountNeedApprove(username);
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		for (Doc item : docList) {
			Dep fromDep = depDao.get(Dep.class, item.getDep_id());
			Dep targetDep = depDao.get(Dep.class, item.getTarget_dep());
			User inprocess = userDao.get(User.class, item.getInprocess());
			Map<String, Object> itemMap = new HashMap<String, Object>();
			itemMap.put("doc_id", item.getDoc_id());
			itemMap.put("doc_type", item.getDoc_type());
			itemMap.put("doc_name", item.getDoc_name());
			itemMap.put("emergency", item.getEmergency());
			itemMap.put("from_dep", fromDep.getDep_name());
			itemMap.put("target_dep", targetDep.getDep_name());
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String start_time = format.format(item.getStart_time());
			itemMap.put("start_time", start_time);
			itemMap.put("inprocess", inprocess.getName());
			itemMap.put("status", item.getStatus());
			mapList.add(itemMap);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", total);
		result.put("rows", mapList);
		return result;
	}

	/**
	 * ��ȡ����ǰ�û�������
	 * 
	 * @param username
	 *            ��ǰ�û��û���
	 * @param pageNo
	 *            ��ǰҳ��
	 * @param pageSize
	 *            ÿҳ��ʾ��������
	 * @return ���صĹ�����Ϣ
	 */
	@Override
	public Map<String, Object> getMyProcessDocs(String username, int pageNo, int pageSize) {
		List<Doc> docList = docDao.findProcessDocByPage(username, pageNo, pageSize);
		Long total = docDao.findCountNeedProcess(username);
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		for (Doc item : docList) {
			Dep fromDep = depDao.get(Dep.class, item.getDep_id());
			Dep targetDep = depDao.get(Dep.class, item.getTarget_dep());
			User inprocess = userDao.get(User.class, item.getInprocess());
			Map<String, Object> itemMap = new HashMap<String, Object>();
			itemMap.put("doc_id", item.getDoc_id());
			itemMap.put("doc_type", item.getDoc_type());
			itemMap.put("doc_name", item.getDoc_name());
			itemMap.put("emergency", item.getEmergency());
			itemMap.put("from_dep", fromDep.getDep_name());
			itemMap.put("target_dep", targetDep.getDep_name());
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String start_time = format.format(item.getStart_time());
			itemMap.put("start_time", start_time);
			itemMap.put("inprocess", inprocess.getName());
			itemMap.put("status", item.getStatus());
			mapList.add(itemMap);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", total);
		result.put("rows", mapList);
		return result;
	}

	/**
	 * ��ȡ��ǰ�û��������Ĺ���
	 * 
	 * @param username
	 *            ��ǰ�û��û���
	 * @param pageNo
	 *            ��ǰҳ��
	 * @param pageSize
	 *            ÿҳ��ʾ��������
	 * @param from 
	 *            ��ʼʱ��
	 * @param to
	 *            ����ʱ��
	 * @return ���صĹ�����Ϣ
	 */
	@Override
	public Map<String, Object> getMyApprovedDocs(String username, int pageNo, int pageSize, Date from, Date to) {
		List<Approve> approveList = approveDao.findApprovedByPage(username, pageNo, pageSize, from, to);
		long total = approveDao.findCountApproved(username, from, to);
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		for (Approve approveItem : approveList) {
			Doc item = docDao.get(Doc.class , approveItem.getDoc_id());
			Dep fromDep = depDao.get(Dep.class, item.getDep_id());
			Dep targetDep = depDao.get(Dep.class, item.getTarget_dep());
			User inprocess = userDao.get(User.class, item.getInprocess());
			Map<String, Object> itemMap = new HashMap<String, Object>();
			itemMap.put("doc_id", item.getDoc_id());
			itemMap.put("doc_type", item.getDoc_type());
			itemMap.put("doc_name", item.getDoc_name());
			itemMap.put("emergency", item.getEmergency());
			itemMap.put("from_dep", fromDep.getDep_name());
			itemMap.put("target_dep", targetDep.getDep_name());
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String start_time = format.format(item.getStart_time());
			String process_time = format.format(approveItem.getApprove_date());
			itemMap.put("process_time", process_time);
			itemMap.put("start_time", start_time);
			itemMap.put("inprocess", inprocess.getName());
			itemMap.put("status", item.getStatus());
			mapList.add(itemMap);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", total);
		result.put("rows", mapList);
		return result;
	}

	/**
	 * ��ȡ��ǰ�û�������Ĺ���
	 * 
	 * @param username
	 *            ��ǰ�û��û���
	 * @param pageNo
	 *            ��ǰҳ��
	 * @param pageSize
	 *            ÿҳ��ʾ��������
	 * @param from 
	 *            ��ʼʱ��
	 * @param to
	 *            ����ʱ��
	 * @return ���صĹ�����Ϣ
	 */
	@Override
	public Map<String, Object> getMyProcessedDocs(String username, int pageNo, int pageSize, Date from, Date to) {
		List<ProcessInfo> processList = processDao.findProcessedByPage(username, pageNo, pageSize, from, to);
		long total = processDao.findCountProcessed(username, from, to);
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		for (ProcessInfo processItem : processList) {
			Doc item = docDao.get(Doc.class , processItem.getDoc_id());
			Dep fromDep = depDao.get(Dep.class, item.getDep_id());
			Dep targetDep = depDao.get(Dep.class, item.getTarget_dep());
			User inprocess = userDao.get(User.class, item.getInprocess());
			Map<String, Object> itemMap = new HashMap<String, Object>();
			itemMap.put("doc_id", item.getDoc_id());
			itemMap.put("doc_type", item.getDoc_type());
			itemMap.put("doc_name", item.getDoc_name());
			itemMap.put("emergency", item.getEmergency());
			itemMap.put("from_dep", fromDep.getDep_name());
			itemMap.put("target_dep", targetDep.getDep_name());
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String start_time = format.format(item.getStart_time());
			String process_time = format.format(processItem.getProcess_date());
			itemMap.put("process_time", process_time);
			itemMap.put("start_time", start_time);
			itemMap.put("inprocess", inprocess.getName());
			itemMap.put("status", item.getStatus());
			mapList.add(itemMap);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", total);
		result.put("rows", mapList);
		return result;
	}
}
