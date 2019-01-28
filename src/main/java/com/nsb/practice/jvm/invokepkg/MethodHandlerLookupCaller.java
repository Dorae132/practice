package com.nsb.practice.jvm.invokepkg;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;

public class MethodHandlerLookupCaller {
    public MethodHandle lookupSpecial() throws NoSuchMethodException, IllegalAccessException, Throwable {
//        MethodHandles.Lookup lookup = MethodHandles.lookup();
//        MethodHandle mh = lookup.findSpecial(MethodHandlerLookup.class, "protectedMethod",
//                MethodType.methodType(void.class), MethodHandlerLookupCaller.class);
//        System.out.println(mh.type());
//        mh.invoke(this);

        MethodType mt = MethodType.methodType(void.class);
        Field IMPL_LOOKUP = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
        IMPL_LOOKUP.setAccessible(true);
        MethodHandles.Lookup lkp = (MethodHandles.Lookup) IMPL_LOOKUP.get(null);
        MethodHandle h1 = lkp.findSpecial(MethodHandlerLookup.class, "protectedMethod", mt, MethodHandlerLookupCaller.class);
        h1.invoke(this);
        return h1;
    }

    public static void main(String[] args) throws Throwable {
        MethodHandlerLookupCaller caller = new MethodHandlerLookupCaller();
        caller.lookupSpecial();
    }
}
