package com.nsb.practice.serilizable;

import java.lang.reflect.Type;

import com.fasterxml.jackson.databind.JavaType;

public class JacksonMetaSerializer implements MetaSerializer {
    
    @Override
    public byte[] toBytes(Object obj) throws Exception{
        return JacksonSerializer.msg(obj);
    }
    
    @Override
    public <T> T fromBytes(byte[] dat, Type type) throws Exception {
        if (type.toString().equals("void")) {
            return null;
        }
        JavaType javaType = JacksonSerializer.createJavaTypeFromType(type);
        T result = null;
        if (javaType != null) {
            result = JacksonSerializer.fromMsg(dat, javaType);
        }
        return result;
    }
}
