package com.nsb.practice.designpatterns.event;

import java.util.Vector;

public class EventSource {
	// 监听器列表，监听器的注册则加入此列表
	private Vector<EventListener> ListenerList = new Vector<EventListener>();

	// 注册监听器
	public void addListener(EventListener eventListener) {
		ListenerList.add(eventListener);
	}

	// 撤销注册
	public void removeListener(EventListener eventListener) {
		ListenerList.remove(eventListener);
	}

	// 接受外部事件
	public void notifyListenerEvents(EventObject event) {
		for (EventListener eventListener : ListenerList) {
			eventListener.handleEvent(event);
		}
	}

}
