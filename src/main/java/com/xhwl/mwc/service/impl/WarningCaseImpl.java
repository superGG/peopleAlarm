package com.xhwl.mwc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.xhwl.mwc.bean.MaisiWarningCaseVo;
import com.xhwl.mwc.bean.WarningCasePo;
import com.xhwl.mwc.dao.WarningCaseDao;
import com.xhwl.mwc.service.WarningCaseService;
import com.xhwl.mwc.utils.HttpUtils;

@Service("warningCaseService")
public class WarningCaseImpl implements WarningCaseService {
	
	@Autowired
	WarningCaseDao warningCaseDao;
	
	@Value("${little_seven}")
	private String little_seven_url;
	
	
	public void uploadWarningCase(MaisiWarningCaseVo caseVo) {
		if (caseVo != null) {
			for (WarningCasePo casePo :  caseVo.getAlarmInfo()) {
				casePo.setProjectCode(caseVo.getProjectID());
				warningCaseDao.save(casePo);
				if(casePo.getAlarmTypeID().equals("-1342177268") || 
						casePo.getAlarmTypeID().equals("-1342177267") || 
						casePo.getAlarmTypeID().equals("-1342177266")) { //重点关注告警信息
					JSONObject jsonParams = (JSONObject) new JSONObject().toJSON(caseVo);
					JSONObject jsonResult = HttpUtils.httpPost(little_seven_url, jsonParams);
					if (jsonResult!=null) System.out.println("----------请求结果:"+jsonResult.toString()+"-------------");
				}
			}
		}
	}

}
