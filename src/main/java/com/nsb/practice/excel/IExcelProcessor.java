package com.nsb.practice.excel;

import java.io.File;

/**
 * process the file that has been created
 * @author Dorae
 *
 * @param <T>
 */
@FunctionalInterface
public interface IExcelProcessor <T> {
	public T process(File file);
}
