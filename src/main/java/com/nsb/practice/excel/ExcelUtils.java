package com.nsb.practice.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.util.Pair;

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
		@ExcelCol(title = "姓名")
		private String name;
		@ExcelCol(title = "年龄", order = 1)
		private String age;
		@ExcelCol(title = "学校", order = 3)
		private String school;
		@ExcelCol(title = "年级", order = 2)
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
		List<TestValue> dataList = Lists.newArrayListWithCapacity(10000);
		for (int i = 0; i < 100000; i++) {
			dataList.add(new TestValue("张三" + i, "age: " + i, null, "clazz: " + i));
		}

		long start = System.currentTimeMillis();
		ExcelProperties<TestValue, File> properties = ExcelProperties.produceCommonProperties("", dataList,
				"C:\\Users\\Dorae\\Desktop\\ttt\\", "testxlsx", 0, null, null);
//		ExcelProperties<TestValue, File> properties = ExcelProperties.produceAppendProperties("",
//				"C:\\Users\\Dorae\\Desktop\\ttt\\", null, 0, null, new AbstractDataSupplier<TestValue>() {
//					private int i = 0;
//
//					@Override
//					public Pair<List<TestValue>, Boolean> getDatas() {
//						boolean hasNext = i < 10;
//						i++;
//						return Pair.of(dataList, hasNext);
//					}
//				}, TestValue.class);
		File file = (File) excelExport(properties, FillSheetModeEnums.COMMON_MODE);
		System.out.println(System.currentTimeMillis() - start);
	}
	
	/**
	 * support the append strategy, but not rocommend, please focus on excelExportApend() 
	 * @param properties
	 * @return
	 * @throws Exception
	 */
	private static Object excelExport(ExcelProperties properties, FillSheetModeEnums fillSheetMode) throws Exception {
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
			fillSheetMode.getValue().fill(properties, sheet);
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
}
