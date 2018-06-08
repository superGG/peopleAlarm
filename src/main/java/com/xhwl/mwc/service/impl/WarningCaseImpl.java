package com.xhwl.mwc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
	
	@Value("${little_seven_test}")
	private String little_seven_test_url;
	@Value("${little_seven_pro}")
	private String little_seven_pro_url;
	
	
	public void uploadWarningCase(MaisiWarningCaseVo caseVo) {
		if (caseVo != null) {
			for (WarningCasePo casePo :  caseVo.getAlarmInfo()) {
				casePo.setProjectCode(caseVo.getProjectID());
				warningCaseDao.save(casePo);
				if(casePo.getAlarmTypeID().equals("-1342177268") || 
						casePo.getAlarmTypeID().equals("-1342177267") || 
						casePo.getAlarmTypeID().equals("-1342177266")) { //重点关注告警信息
					if (StringUtils.isEmpty(casePo.getAlarmClearTime())) {
						JSONObject jsonParams = (JSONObject) new JSONObject().toJSON(caseVo);
						JSONObject jsonResult_test = HttpUtils.httpPost(little_seven_test_url, jsonParams);
						if (jsonResult_test!=null) System.out.println("----------请求结果:"+jsonResult_test.toString()+"-------------");
						JSONObject jsonResult_pro = HttpUtils.httpPost(little_seven_pro_url, jsonParams);
						if (jsonResult_pro!=null) System.out.println("----------请求结果:"+jsonResult_pro.toString()+"-------------");
					}
				}
			}
		}
	}

}
