package org.cxb.oa.service;

import java.util.List;
import java.util.Map;

public interface UserManager {
	public boolean login(String username , String password);
	public List<Map<String , String>> getDepUserNames(String dep_id);
	public String getName(String username);
	public boolean checkOldPass(String username , String oldPass);
	public boolean changePass(String username , String newPass);
}
