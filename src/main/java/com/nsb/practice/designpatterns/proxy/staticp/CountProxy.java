package com.nsb.practice.designpatterns.proxy.staticp;

public class CountProxy implements Count {

	private Count count;

	@Override
	public void queryCount() {
		System.out.println("查询账户的预处理——————");
		// 调用真正的查询账户方法
		count.queryCount();
		System.out.println("查询账户之后————————");
	}

	public CountProxy(Count count) {
		super();
		this.count = count;
	}

	@Override
	public void updateCount() {
		System.out.println("修改账户之前的预处理——————");
		// 调用真正的修改账户操作
		count.updateCount();
		System.out.println("修改账户之后——————————");
	}

}
