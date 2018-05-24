package com.xhwl.mwc.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xhwl.mwc.bean.MaisiWarningCaseVo;
import com.xhwl.mwc.service.WarningCaseService;

/**
 * api控制类
 * @author Kellan_Song
 * @createTime 2018年5月24日
 */
@Controller
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	WarningCaseService warningCaseService;

	@RequestMapping("/uploadWarningCase")
	@ResponseBody
	public Map<String, Object> uploadWarningCase(@RequestBody MaisiWarningCaseVo warningCase) {
		warningCaseService.uploadWarningCase(warningCase);
		if (1==1) throw new SecurityException("111",new Throwable("222"));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("resultCode", 0);
		map.put("msg", "上传数据成功");
		map.put("result", warningCase);
		return map;
	}
	
	
}
