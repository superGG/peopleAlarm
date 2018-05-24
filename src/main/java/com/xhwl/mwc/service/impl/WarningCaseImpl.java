package com.xhwl.mwc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xhwl.mwc.bean.MaisiWarningCaseVo;
import com.xhwl.mwc.bean.WarningCasePo;
import com.xhwl.mwc.dao.WarningCaseDao;
import com.xhwl.mwc.service.WarningCaseService;

@Service("warningCaseService")
public class WarningCaseImpl implements WarningCaseService {
	
	@Autowired
	WarningCaseDao warningCaseDao;
	
	
	public void uploadWarningCase(MaisiWarningCaseVo caseVo) {
		if (caseVo != null) {
			for (WarningCasePo casePo :  caseVo.getAlarmInfo()) {
				casePo.setProjectCode(caseVo.getProjectID());
				warningCaseDao.save(casePo);
			}
		}
	}

}
