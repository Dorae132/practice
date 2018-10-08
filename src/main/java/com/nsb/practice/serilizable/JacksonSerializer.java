package com.nsb.practice.serilizable;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.msgpack.jackson.dataformat.MessagePackFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class JacksonSerializer {
    private final static ObjectMapper objectMapper = new ObjectMapper(new MessagePackFactory());;
    static {
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
    };
    
    /**
     * 序列化
     */
    public static byte[] msg(Object obj) {
        try {
            return getJsonMapper().writeValueAsBytes(obj);
        } catch (Exception e) {
            throw new RuntimeException("msg出错：" + e.getMessage());
        }
    }

    /**
     * 反序列化
     */
    public static <T> T fromMsg(byte[] msg, TypeReference<T> typeref){
        try {
            return getJsonMapper().readValue(msg, typeref);
        } catch (Exception e) {
            throw new RuntimeException("readObject TypeReference出错：" + e.getMessage());
        }
    }

    /**
     * 反序列化
     */
    public static <T> T fromMsg(byte[] msg, JavaType type){
        try {
            return getJsonMapper().readValue(msg, type);
        } catch (Exception e) {
            throw new RuntimeException("readObject type 出错：" + e.getMessage());
        }
    }

    /**
     * 反序列化
     */
    public static <T> T fromMsg(byte[] msg, Class<T> clz){
        try {
            return getJsonMapper().readValue(msg, clz);
        } catch (Exception e) {
            throw new RuntimeException("readObject clz出错：" + e.getMessage());
        }
    }
    
    /**
     *  type 泛型化的type
     *  
     *  这里需要注意的，java方法被aop代理后， 就无法反解参数的泛型类型， 所以server里带会带interface申明
     */
    public static JavaType createJavaTypeFromType(Type type) throws Exception{
        if(type instanceof ParameterizedType){
            ParameterizedType ptype = (ParameterizedType) type;
            Type[] argTypes = ptype.getActualTypeArguments();
            JavaType[] tmpTypes = new JavaType[argTypes.length];
            for(int i=0;i<argTypes.length;i++){
                tmpTypes[i] = createJavaTypeFromType(argTypes[i]);
            }
            // JDK1.8以后，就可以 ptype.getRawType().getTypeName();
            // 目前先这么诡异的处理掉了
            String typeString=ptype.getRawType().toString();
            int index=typeString.indexOf(" ");
            if (index>0) {
                typeString=typeString.substring(index).trim();
            }
            Class<?> rawClass = Class.forName(typeString);
            return getTypeFactory().constructParametrizedType(rawClass, rawClass, tmpTypes);
        }else{
            return getTypeFactory().constructType(type);
        }
    }

    public static JavaType createMethodReturnJavaType(Method method) throws Exception{
        if (method.getReturnType().toString().equals("void")) {
            return null;
        }
        return createJavaTypeFromType(method.getGenericReturnType());
    }
    
    public static Object fromMsg(byte[] msg, Type type) throws Exception{
        JavaType javaType = createJavaTypeFromType(type);
        return fromMsg(msg, javaType);
    }
    
    public static TypeFactory getTypeFactory(){
        return objectMapper.getTypeFactory();
    }
    
    public static ObjectMapper getJsonMapper() {
        return objectMapper;
    }
}


