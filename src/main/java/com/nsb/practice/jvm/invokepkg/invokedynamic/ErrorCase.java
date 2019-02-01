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

    private static int getInt(final MyInterface1 i) {
        return i.getValue1();
    }

    /**
     * 这里对于方法引用而言，因为没有为lambda生成静态方法以及匿名内部类，所以将会导致
     * implMethod与实例method不符的情况发生，如果此时有符合类型， 那么将会导致类型无法推断。运行时error
     * @param values
     * @return
     */
    private static <T extends MyInterface2 & MyInterface1> OptionalInt findMinValue(final Collection<T> values) {
        return values.stream().mapToInt(e -> ErrorCase.getInt(e)).min();
//        return values.stream().mapToInt(ErrorCase :: getInt).min();
    }

    public static void main(String[] args) {
        Collection<MyInteger> list = Lists.newArrayList(new MyInteger(), new MyInteger());
        System.out.println(ErrorCase.findMinValue(list));
    }
    
    static interface MyInterface1 {

        public int getValue1();
    }
    
    static interface MyInterface2 {
        public int getValue2();
    }
    
    static class MyInteger implements MyInterface1, MyInterface2 {

        @Override
        public int getValue2() {
            return 2;
        }

        @Override
        public int getValue1() {
            return 1;
        }
        
    }
}
