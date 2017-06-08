package org.cxb.oa.service;

import java.io.File;

import org.cxb.oa.bean.OuterDoc;

public interface OuterDocManager {
	//¹«ÎÄµÇ¼Ç
	public boolean docLog(OuterDoc doc, File file , String fileName , String path , String username);
}
