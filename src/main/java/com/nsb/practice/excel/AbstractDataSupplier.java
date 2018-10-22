package com.nsb.practice.excel;

import java.util.List;

import org.springframework.data.util.Pair;

/**
 * for the append strategy
 * @author Dorae
 *
 * @param <T>
 */
public abstract class AbstractDataSupplier<T> {
	
	public abstract Pair<List<T>, Boolean> getDatas();

}
