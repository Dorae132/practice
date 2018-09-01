package com.nsb.practice.designpatterns.strategy;

public class Vip implements CalPrice {

	@Override
	public Double calPrice(Double orgnicPrice) {
		return 0.9 * orgnicPrice;
	}

}
