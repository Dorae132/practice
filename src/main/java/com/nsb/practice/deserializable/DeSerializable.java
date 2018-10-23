package com.nsb.practice.deserializable;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.msgpack.jackson.dataformat.MessagePackFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class DeSerializable {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		Method method = DeSerializable.class.getMethod("test");
		Type returnType = method.getGenericReturnType();
		System.out.println(returnType.getTypeName());
		if (returnType instanceof ParameterizedType) {
			Type[] types = ((ParameterizedType) returnType).getActualTypeArguments();
			for (Type type : types) {
				System.out.println("泛型类型: " + type);
			}
		}
		String jsonStr = "[[{\"value\":\"123\"}]]";
		JavaType javaType = createJavaTypeFromType(returnType);
		List<Generic> result = objectMapper.readValue(jsonStr, javaType);
		System.out.println(result);
	}

	public static JavaType createJavaTypeFromType(Type type) throws Exception{
        if(type instanceof ParameterizedType){
            ParameterizedType ptype = (ParameterizedType) type;
            Type[] argTypes = ptype.getActualTypeArguments();
            JavaType[] tmpTypes = new JavaType[argTypes.length];
            for(int i=0;i<argTypes.length;i++){
                tmpTypes[i] = createJavaTypeFromType(argTypes[i]);
            }
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
	
	public static TypeFactory getTypeFactory(){
        return objectMapper.getTypeFactory();
    }
	
	private final static ObjectMapper objectMapper = new ObjectMapper(new MessagePackFactory());;
    static {
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
    };
    
	public List<List<Generic>> test() {
		return null;
	}
}

class Generic {
	public String value;

	public Generic() {
		super();
	}

	public Generic(String value) {
		super();
		this.value = value;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}