package com.xhwl.mwc.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 迈斯上传告警 实体
 * @author Kellan_Song
 * @createTime 2018年5月22日
 */
public class MaisiWarningCaseVo implements Serializable{

	private static final long serialVersionUID = 1L;

	private String projectID;//项目编号
	
	private List<WarningCasePo> alarmInfo; //告警集合
	
	public String getProjectID() {
		return projectID;
	}

	public void setProjectID(String projectID) {
		this.projectID = projectID;
	}

	public List<WarningCasePo> getAlarmInfo() {
		return alarmInfo;
	}

	public void setAlarmInfo(List<WarningCasePo> alarmInfo) {
		this.alarmInfo = alarmInfo;
	}
	
	@Override
	public String toString() {
		return "MaisiWarningCaseEntity [projectID=" + projectID
				+ ", alarmInfo=" + alarmInfo.toString() + "]";
	}


}
