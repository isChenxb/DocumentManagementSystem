package org.cxb.oa.service;

import java.io.File;

import org.cxb.oa.bean.OuterDoc;

public interface OuterDocManager {
	//���ĵǼ�
	public boolean docLog(OuterDoc doc, File file , String fileName , String path , String username);
}
