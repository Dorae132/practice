package com.nsb.practice.serilizable;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClientProxy implements InvocationHandler {

	public static final ThreadLocal<Method> currentMethod = new ThreadLocal<Method>();
    public static final ThreadLocal<Object> currentProxy = new ThreadLocal<Object>();
    
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("proxy start!");
		Object object = syncCall(proxy, method, args);
		System.out.println("proxy end!");
		return object;
	}
	
	public Object bind() {
		return Proxy.newProxyInstance(ClientProxy.class.getClassLoader(), new Class<?>[] { MyInterface.class }, this);
	}
	
	public Object syncCall(Object proxy, Method method, Object[] args) throws Exception {
        currentMethod.set(method);
        currentProxy.set(proxy);
        try {
        	MetaSerializer jacksonMetaSerializer = new JacksonMetaSerializer();
            List<byte[]> params = serializeParams(jacksonMetaSerializer, method, args);
            return params;
        } finally {
            currentMethod.remove();
            currentProxy.remove();
        }
    }

	public static final List<byte[]> serializeParams(MetaSerializer metaSerializer, Method method, Object[] args) throws Exception {
        if(args == null || args.length <= 0) {
            return Collections.emptyList();
        }
        List<byte[]> params = new ArrayList<byte[]>(args.length);
        for (Object arg : args) {
            byte[] seriesBytes = metaSerializer.toBytes(arg);
            params.add(seriesBytes);
        }
        return params;
    }
}
