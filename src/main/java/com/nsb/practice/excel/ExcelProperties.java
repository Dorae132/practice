package com.nsb.practice.excel;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;

/**
 * preperties if the excel which would to be extported
 * 
 * @author Dorae
 *
 * @param <T> the data type
 * @param <R> the type of the return value that excepted
 */
public class ExcelProperties<T, R> {
	
	// sheet名称和表头值
	private String sheetName = "sheet1";
	
	// 表头信息
	private List<TitleToFieldObj> titleToFieldObjs;

	// 表头map
	private Map<String, String> titleFieldMap;
	
	// 对象集合
	private List<T> dataList;
	
	// data类的field
	private List<Field> fields;
	
	// fieldName -> field
	private Map<String, Field> fieldNameMap;
	
	// 文件地址
	private String filePath = "./liveunionExcels/";
	
	// 文件名
	private String fileName = UUID.randomUUID() + ".xlsx";

	// 行偏移
	private int rowOffset = 0;

	// 列偏移
	private int colOffset = 0;
	
	private ExcelProcessor<R> processor;
	
	/**
	 * 
	 * @param sheetName
	 * @param titleToFieldObjs 表头与字段的对应关系
	 * @param dataList 数据集
	 * @param filePath 文件路径（默认./liveunionExcels/）
	 * @param fileName 文件名（默认随机）
	 * @param excludeFields 要排除的字段名
	 * @param rowOffset 行偏移
	 * @param colOffset 列偏移
	 * @throws Exception
	 */
	public ExcelProperties(String sheetName, List<TitleToFieldObj> titleToFieldObjs, List<T> dataList, String filePath,
			String fileName, List<String> excludeFields, int rowOffset, int colOffset, ExcelProcessor processor) throws Exception {
		super();
		if (StringUtils.isNotBlank(sheetName)) {
			this.sheetName = sheetName;
		}
		this.titleToFieldObjs = titleToFieldObjs;
		this.dataList = dataList;
		if (StringUtils.isNotBlank(filePath)) {
			this.filePath = filePath;
		}
		if (StringUtils.isNotBlank(fileName)) {
			this.fileName = fileName;
		}
		this.rowOffset = rowOffset;
		this.colOffset = colOffset;
		this.titleFieldMap = titleToFieldObjs.stream()
				.collect(Collectors.toMap(TitleToFieldObj :: getTitle, TitleToFieldObj :: getFieldName));
		Field field = ExcelProperties.class.getDeclaredField("dataList");
		if (!CollectionUtils.isEmpty(dataList)) {
			Field[] declaredFields = dataList.get(0).getClass().getDeclaredFields();
			this.fields = Lists.newArrayList(declaredFields);
			if (!CollectionUtils.isEmpty(excludeFields)) {
				for (Field field2 : declaredFields) {
					if (excludeFields.contains(field2.getName())) {
						fields.remove(field2);
					}
				}
			}
			this.fieldNameMap = Stream.of(declaredFields).collect(Collectors.toMap(Field :: getName, e -> e));
		}
		this.processor = processor;
	}
	
	public ExcelProcessor<R> getProcessor() {
		return processor;
	}

	public void setProcessor(ExcelProcessor<R> processor) {
		this.processor = processor;
	}

	public int getRowOffset() {
		return rowOffset;
	}

	public void setRowOffset(int rowOffset) {
		this.rowOffset = rowOffset;
	}

	public int getColOffset() {
		return colOffset;
	}

	public void setColOffset(int colOffset) {
		this.colOffset = colOffset;
	}

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	public Map<String, Field> getFieldNameMap() {
		return fieldNameMap;
	}

	public void setFieldNameMap(Map<String, Field> fieldNameMap) {
		this.fieldNameMap = fieldNameMap;
	}

	public Map<String, String> getTitleFieldMap() {
		return titleFieldMap;
	}

	public void setTitleFieldMap(Map<String, String> titleFieldMap) {
		this.titleFieldMap = titleFieldMap;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public List<TitleToFieldObj> getTitleToFieldObjs() {
		return titleToFieldObjs;
	}

	public void setTitleToFieldObjs(List<TitleToFieldObj> titleToFieldObjs) {
		this.titleToFieldObjs = titleToFieldObjs;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}
}
