package com.nsb.practice.excel;

import org.apache.poi.ss.usermodel.Sheet;

/**
 * 填充sheet
 * @author Dorae
 *
 */
public interface IFillSheet {

	void fill(ExcelProperties excelProperties, Sheet sheet) throws Exception;
	
}
