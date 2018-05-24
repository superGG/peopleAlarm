package com.xhwl.mwc.utils;

import java.io.Serializable;
import java.util.Map;

/**
 * 用于接收
 * @author Kellan_Song
 * @createTime 2018年5月5日
 */
public class RequestBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String equipmentName;// 设备名称
	private String equipmentType;// 设备类型
	private String numberType;// 型号
	private String projectCode;// 项目编号
	private String roomName;// 房名
	private String slaveId;// 
	private String type; //
	private String roomCode;//房编号
	private String equipmentNumber;//设备编号
	
	private Map<String, Object> data; //数据

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public String getEquipmentType() {
		return equipmentType;
	}

	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}

	public String getNumberType() {
		return numberType;
	}

	public void setNumberType(String numberType) {
		this.numberType = numberType;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getSlaveId() {
		return slaveId;
	}

	public void setSlaveId(String slaveId) {
		this.slaveId = slaveId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRoomCode() {
		return roomCode;
	}

	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}

	public String getEquipmentNumber() {
		return equipmentNumber;
	}

	public void setEquipmentNumber(String equipmentNumber) {
		this.equipmentNumber = equipmentNumber;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
}
