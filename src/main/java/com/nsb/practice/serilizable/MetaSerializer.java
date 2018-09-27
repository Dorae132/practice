package com.nsb.practice.serilizable;

import java.lang.reflect.Type;

public interface MetaSerializer {

    public byte[] toBytes(Object obj) throws Exception ;
    
    public <T> T fromBytes(byte[] dat, Type type) throws Exception ;
}
