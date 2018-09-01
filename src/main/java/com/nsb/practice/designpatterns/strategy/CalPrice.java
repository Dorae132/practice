package com.nsb.practice.designpatterns.strategy;

public interface CalPrice {
	
	/**
	 * 根据原价进行打折
	 * @param orgnicPrice
	 * @return
	 */
	Double calPrice(Double orgnicPrice);
}
