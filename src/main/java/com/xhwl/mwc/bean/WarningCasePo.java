package com.xhwl.mwc.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 告警实体
 * @author Kellan_Song
 * @createTime 2018年5月22日
 */
@Entity
@Table(name="warning_case")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonInclude(JsonInclude.Include.NON_NULL)
@DynamicInsert(true)
public class WarningCasePo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Integer alarmID;  //告警ID
	private String alarmSource;//告警源
	private Integer deviceID;//设备ID，非设备产生的告警则为0
	private Integer alarmLevel;//告警等级（0：事件，1：一般告警：2重要告警，3：紧急告警）
	private String alarmDesc;//门开超时，暴力入侵
	private String alarmOccurTime; //告警产生时间
	private String alarmConfirmTime;//告警确认时间，没确认则为空
	private String alarmClearTime;//告警结束时间，没结束则为空
	private String projectCode; //项目编号
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="alarm_id")
	public Integer getAlarmID() {
		return alarmID;
	}
	public void setAlarmID(Integer alarmID) {
		this.alarmID = alarmID;
	}
	@Column(name="alarm_source")
	public String getAlarmSource() {
		return alarmSource;
	}
	public void setAlarmSource(String alarmSource) {
		this.alarmSource = alarmSource;
	}
	@Column(name="device_id")
	public Integer getDeviceID() {
		return deviceID;
	}
	public void setDeviceID(Integer deviceID) {
		this.deviceID = deviceID;
	}
	@Column(name="alarm_level")
	public Integer getAlarmLevel() {
		return alarmLevel;
	}
	public void setAlarmLevel(Integer alarmLevel) {
		this.alarmLevel = alarmLevel;
	}
	@Column(name="alarm_desc")
	public String getAlarmDesc() {
		return alarmDesc;
	}
	public void setAlarmDesc(String alarmDesc) {
		this.alarmDesc = alarmDesc;
	}
	@Column(name="alarm_occur_time")
	public String getAlarmOccurTime() {
		return alarmOccurTime;
	}
	public void setAlarmOccurTime(String alarmOccurTime) {
		this.alarmOccurTime = alarmOccurTime;
	}
	@Column(name="alarm_confirm_time")
	public String getAlarmConfirmTime() {
		return alarmConfirmTime;
	}
	public void setAlarmConfirmTime(String alarmConfirmTime) {
		this.alarmConfirmTime = alarmConfirmTime;
	}
	@Column(name="alarm_clear_time")
	public String getAlarmClearTime() {
		return alarmClearTime;
	}
	public void setAlarmClearTime(String alarmClearTime) {
		this.alarmClearTime = alarmClearTime;
	}
	@Column(name="project_code")
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	@Override
	public String toString() {
		return "AlarmInfo [alarmID=" + alarmID + ", alarmSource="
				+ alarmSource + ", deviceID=" + deviceID + ", alarmLevel="
				+ alarmLevel + ", alarmDesc=" + alarmDesc
				+ ", alarmOccurTime=" + alarmOccurTime
				+ ", alarmConfirmTime=" + alarmConfirmTime
				+ ", alarmClearTime=" + alarmClearTime + "]";
	}

}
