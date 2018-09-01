package com.nsb.practice.designpatterns.strategy;

public class Client {
	public static void main(String[] args) {
		Player player = new Player();
		player.buy(100000D);
		System.out.println(player.calLastAmount());
	}
}
