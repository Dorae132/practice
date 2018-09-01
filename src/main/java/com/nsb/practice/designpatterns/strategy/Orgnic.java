package com.nsb.practice.designpatterns.strategy;

public class Orgnic implements CalPrice {

	@Override
	public Double calPrice(Double orgnicPrice) {
		return orgnicPrice;
	}

}
