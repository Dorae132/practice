package com.nsb.practice.jvm.invokepkg.invokedynamic;

import java.util.Collection;
import java.util.OptionalInt;
import java.util.function.IntSupplier;

import com.google.common.collect.Lists;

/**
 * error case test for stream <br>
 * need to be resloved <br>
 * https://www.oschina.net/translate/hacking-lambda-expressions-in-java?cmp&p=3 <br>
 * @author Dorae
 *
 */
public class ErrorCase {

    private static int getInt(final IntSupplier i) {
        return i.getAsInt();
    }

    private static <T extends Number & IntSupplier> OptionalInt findMinValue(final Collection<T> values) {
        return values.stream().mapToInt(ErrorCase::getInt).min();
    }

    public static void main(String[] args) {
        // doraeTODO need to be resloved, the type inference error.
        Collection<MyInteger> list = Lists.newArrayList(new MyInteger(1), new MyInteger(2));
        ErrorCase.findMinValue(list);
    }
    
    static class MyInteger extends Number implements IntSupplier {

        private int value;
        
        public MyInteger(int value) {
            super();
            this.value = value;
        }

        @Override
        public int getAsInt() {
            return value;
        }

        @Override
        public int intValue() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public long longValue() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public float floatValue() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public double doubleValue() {
            // TODO Auto-generated method stub
            return 0;
        }
        
    }
}

