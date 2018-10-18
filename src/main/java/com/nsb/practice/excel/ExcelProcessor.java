package com.nsb.practice.excel;

import java.io.File;

/**
 * process the file that has been created
 * @author Dorae
 *
 */
@FunctionalInterface
public interface ExcelProcessor<T> {
	public T process(File file);
}
