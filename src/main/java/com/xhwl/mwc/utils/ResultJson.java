package com.xhwl.mwc.utils;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;



public class ResultJson {
    private Boolean state = true;
    private String flag;
    private String message;
    private Object result;
    private Integer errorCode = 200;//状态码
    @JsonIgnore
    private Map<String, Object> resultMap;
    
	public Map<String, Object> getResultMap() {
		if (resultMap == null) resultMap = new HashMap<String, Object>();
    	return resultMap;
    }
    
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getResult() {
		if(resultMap != null) result = resultMap;
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
    public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
}
