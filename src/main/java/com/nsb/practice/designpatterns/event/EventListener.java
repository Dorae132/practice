package com.nsb.practice.designpatterns.event;

import java.util.EventObject;

public interface EventListener extends java.util.EventListener {
	//事件处理
    public void handleEvent(EventObject event);
}
