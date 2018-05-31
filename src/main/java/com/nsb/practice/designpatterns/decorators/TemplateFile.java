package com.nsb.practice.designpatterns.decorators;

/**
 * 模板文件类型。包装了文件的内容，{@link #fillContent()} 用于填充内容
 * @author Kingsley
 *
 */
public interface TemplateFile {
	public StringBuilder getContent();

	public void fillContent();
}
