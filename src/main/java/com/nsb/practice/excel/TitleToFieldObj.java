package com.nsb.practice.excel;

/**
 * 存储表头和对象属性名的映射
 * @author Dorae
 *
 */
public class TitleToFieldObj {

	// 表头
	private String title;
	
	// 属性名
	private String fieldName;

	public TitleToFieldObj(String title, String fieldName) {
		super();
		this.title = title;
		this.fieldName = fieldName;
	}

	public TitleToFieldObj() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
}
