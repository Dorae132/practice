package com.nsb.practice.designpatterns.proxy.staticp;

public class CountImpl implements Count {

	@Override
	public void queryCount() {
		System.out.println("查看账户...");
	}

	@Override
	public void updateCount() {
		System.out.println("修改账户...");
	}

}
