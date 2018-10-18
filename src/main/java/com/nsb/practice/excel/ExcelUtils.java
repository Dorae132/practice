package com.nsb.practice.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
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

	@SuppressWarnings({ "unused", "rawtypes", "unchecked", "resource" })
	public static void main(String[] args) throws Exception {
		List<TitleToFieldObj> titleToFieldObjs = Lists.newArrayList(new TitleToFieldObj("姓名", "name"),
				new TitleToFieldObj("年龄", "age"), new TitleToFieldObj("学校", "school"),
				new TitleToFieldObj("班级", "clazz"));
		List<TestValue> dataList = Lists.newArrayListWithCapacity(10000);
		for (int i = 0; i < 5000; i++) {
			dataList.add(new TestValue("张三" + i, "age: " + i, null, "clazz: " + i));
		}

		long start = System.currentTimeMillis();
		ExcelProperties<TestValue, File> properties = new ExcelProperties("", titleToFieldObjs, dataList,
				"C:\\Users\\Dorae\\Desktop\\ttt\\", "test.xlsx", Lists.newArrayList(""), 0, 0, null);
		excelExport(properties);
		
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			File file = new File(
					new StringBuilder(properties.getFilePath()).append(properties.getFileName()).toString());
			inputStream = new FileInputStream(file);
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
			SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook(xssfWorkbook, ROW_ACCESS_WINDOWSIZE);
			Sheet sheet = sxssfWorkbook.getSheet(properties.getSheetName());
			if (sheet == null) {
				sheet = sxssfWorkbook.createSheet(properties.getSheetName());
			}
			outputStream = new FileOutputStream(file);

			for (int i = 1; i < 5; i++) {
				properties.setRowOffset(i * 5000);
				fillContentRow(properties, sheet);
			}
			sxssfWorkbook.write(outputStream);
		} catch (Exception e) {
			throw e;
		} finally {
			inputStream.close();
			outputStream.close();
		}
		System.out.println(System.currentTimeMillis() - start);
	}

	/**
	 * support the append strategy, but not rocommend, please focus on excelExportApend() 
	 * @param properties
	 * @return
	 * @throws Exception
	 */
	public static Object excelExport(ExcelProperties properties) throws Exception {
		// 1.创建目录
		validateFileDir(properties.getFilePath());
		File file = new File(new StringBuilder(properties.getFilePath()).append(properties.getFileName()).toString());
		if (!file.exists()) {
			try (FileOutputStream tmpOutPutStream = new FileOutputStream(file)) {
				XSSFWorkbook tmpWorkBook = new XSSFWorkbook();
				tmpWorkBook.write(tmpOutPutStream);
			} catch (Exception e) {
				throw e;
			}
		}
		// 2.写入文件
		FileOutputStream outputStream = null;
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(file);
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
			SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook(xssfWorkbook, ROW_ACCESS_WINDOWSIZE);
			Sheet sheet = sxssfWorkbook.getSheet(properties.getSheetName());
			if (sheet == null) {
				sheet = sxssfWorkbook.createSheet(properties.getSheetName());
			}
			createHeadRow(properties, sheet);
			fillContentRow(properties, sheet);
			outputStream = new FileOutputStream(file);
			sxssfWorkbook.write(outputStream);
		} catch (Exception e) {
			throw e;
		} finally {
			if (outputStream != null) {
				outputStream.close();
			}
			if (inputStream != null) {
				inputStream.close();
			}
		}
		// 3.处理结果
		if (properties.getProcessor() != null) {
			return properties.getProcessor().process(file);
		} else {
			return file;
		}
	}

	/**
	 * append mode
	 * @param properties
	 * @param sheet
	 * @throws Exception
	 */
	public static void excelExportApend(ExcelProperties properties, Sheet sheet) throws Exception {
		createHeadRow(properties, sheet);
		fillContentRow(properties, sheet);
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
	private static void fillContentRow(ExcelProperties<?, ?> excelProperties, Sheet sheet) throws Exception {
		if (CollectionUtils.isEmpty(excelProperties.getDataList())) {
			return;
		}
		List<Field> fields = excelProperties.getFields();
		Map<String, Field> fieldNameMap = excelProperties.getFieldNameMap();
		int row = 1 + excelProperties.getRowOffset();
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
	private static void createHeadRow(ExcelProperties properties, Sheet sheet) {
		List<TitleToFieldObj> titleToFieldObjs = properties.getTitleToFieldObjs();
		// 偏移不为0
		if (properties.getRowOffset() != 0) {
			return;
		}
		Row headRow = sheet.createRow(0);
		int i = 0;
		for (TitleToFieldObj titleToFieldObj : titleToFieldObjs) {
			headRow.createCell(i++).setCellValue(titleToFieldObj.getTitle());
		}
	}
}
