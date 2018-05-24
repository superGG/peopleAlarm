package com.xhwl.mwc.utils;

import java.util.List;

/**
 * 用于 使用 @RequestBody 注解时，装list对象；
 * @author Kellan_Song
 * @createTime 2018年3月27日
 * @param <T>
 */
public class ObjectList<T> {
	
	private List<T> objectList;

	public List<T> getObjectList() {
		return objectList;
	}

	public void setObjectList(List<T> objectList) {
		this.objectList = objectList;
	}
	
}
