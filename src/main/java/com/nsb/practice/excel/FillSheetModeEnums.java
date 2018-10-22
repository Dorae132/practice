package com.nsb.practice.excel;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellUtil;
import org.springframework.data.util.Pair;
import org.springframework.util.CollectionUtils;

/**
 * 写入模式枚举
 * 
 * @author Dorae
 *
 */
public enum FillSheetModeEnums {

	COMMON_MODE(new IFillSheet() {

		@Override
		public void fill(ExcelProperties excelProperties, Sheet sheet) throws Exception {
			createHeadRow(excelProperties, sheet);
			fillContentRow(excelProperties, sheet);
		}
		
	}),
	APPEND_MODE(new IFillSheet() {

		@Override
		public void fill(ExcelProperties properties, Sheet sheet) throws Exception {
			int rowOffset = 0;
			Pair datasPair = null;
			List<?> dataList = null;
			boolean hashNext = false;
			while (true) {
				AbstractDataSupplier dataSupplier = properties.getDataSupplier();
				if (dataSupplier == null) {
					break;
				}
				datasPair = dataSupplier.getDatas();
				dataList = (List<?>) datasPair.getFirst();
				boolean hasNext = (boolean) datasPair.getSecond();
				if (hasNext) {
					properties.setDataList(dataList);
					properties.setRowOffset(rowOffset);
					createHeadRow(properties, sheet);
					fillContentRow(properties, sheet);
					rowOffset += dataList.size();
				} else {
					break;
				}
			}
		}
		
	});
	
	private IFillSheet iFillSheet;

	private FillSheetModeEnums(IFillSheet iFillSheet) {
		this.iFillSheet = iFillSheet;
	}
	
	public IFillSheet getValue() {
		return this.iFillSheet;
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
	 * 创建表头行（第0行创建）
	 *
	 * @param titleMap
	 *            对象属性名称->表头显示名称
	 */
	private static void createHeadRow(ExcelProperties properties, Sheet sheet) {
		List<String> titles = properties.getTitles();
		// 偏移不为0
		if (properties.getRowOffset() != 0) {
			return;
		}
		Row headRow = sheet.createRow(0);
		int i = 0;
		for (String title : titles) {
			headRow.createCell(i++).setCellValue(title);
		}
	}
}
