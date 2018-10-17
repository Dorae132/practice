package com.nsb.practice.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;

/**
 * Excel utils
 * 
 * @author Dorae
 *
 */
public class ExcelUtils {

	private static int ROW_ACCESS_WINDOWSIZE = 100;
	
	static class TestValue {
		private String name;
		private String age;
		private String school;
		private String clazz;
		public TestValue(String name, String age, String school, String clazz) {
			super();
			this.name = name;
			this.age = age;
			this.school = school;
			this.clazz = clazz;
		}
	}
	@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
	public static void main(String[] args) throws Exception {
		List<TitleToFieldObj> titleToFieldObjs = Lists.newArrayList(new TitleToFieldObj("姓名", "name"),
				new TitleToFieldObj("年龄", "age"), new TitleToFieldObj("学校", "school"), new TitleToFieldObj("班级", "clazz"));
		List<TestValue> dataList = Lists.newArrayListWithCapacity(10000);
		for(int i = 0; i < 10000; i++) {
			dataList.add(new TestValue("张三" + i, "age: " + i, null, "clazz: " + i));
		}
		ExcelProperties<TestValue> excelProperties = new ExcelProperties("", titleToFieldObjs, dataList, "C:\\Users\\Dorae\\Desktop\\ttt\\", "", Lists.newArrayList(""));
		long start = System.currentTimeMillis();
		String excelExport = excelExport(excelProperties);
		System.out.println(System.currentTimeMillis() - start);
	}

	/**
	 * 生成excel报表
	 *
	 * @param properties
	 *            传入参数
	 * @return
	 */
	public static String excelExport(ExcelProperties properties) throws Exception {
		validateFileDir(properties.getFilePath());
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
		SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook(xssfWorkbook, ROW_ACCESS_WINDOWSIZE);
		Sheet sheet = sxssfWorkbook.createSheet(properties.getSheetName());
		createHeadRow(properties.getTitleToFieldObjs(), sheet);
		fillContentRow(properties, sheet);
		File file = new File(new StringBuilder(properties.getFilePath()).append(properties.getFileName()).toString());
		try (FileOutputStream outputStream = new FileOutputStream(file)) {
			sxssfWorkbook.write(outputStream);
		} catch (Exception e) {
			throw e;
		}
		return uploadFile(file);
	}

	/**
	 * 上传文件
	 * @param file
	 * @return
	 */
	private static String uploadFile(File file) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 填充内容
	 * 
	 * @param dataList
	 * @param titleToFieldObjs
	 * @param sheet
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	private static void fillContentRow(ExcelProperties<?> excelProperties, Sheet sheet) throws Exception {
		if (CollectionUtils.isEmpty(excelProperties.getDataList())) {
			return;
		}
		List<Field> fields = excelProperties.getFields();
		Map<String, Field> fieldNameMap = excelProperties.getFieldNameMap();
		int row = 1;
		for (Object object : excelProperties.getDataList()) {
			int col = 0;
			Row createRow = sheet.createRow(row);
			for (Field field : fields) {
				field.setAccessible(true);
				Object value = field.get(object);
				CellUtil.createCell(createRow, col, value == null ? "" : value.toString());
				col++;
			}
			row++;
		}
	}

	/**
	 * 校验目录是否存在
	 * 
	 * @param filePath
	 */
	private static void validateFileDir(String filePath) {
		File tempDir = new File(filePath);
		if (!tempDir.exists() && !tempDir.isDirectory()) {
			tempDir.mkdir();
		}
	}

	/**
	 * 创建表头行（第0行创建）
	 *
	 * @param titleMap
	 *            对象属性名称->表头显示名称
	 */
	private static void createHeadRow(List<TitleToFieldObj> titleToFieldObjs, Sheet sheet) {
		Row headRow = sheet.createRow(0);
		int i = 0;
		for (TitleToFieldObj titleToFieldObj : titleToFieldObjs) {
			headRow.createCell(i++).setCellValue(titleToFieldObj.getTitle());
		}
	}
}
