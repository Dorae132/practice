package com.nsb.practice.jvm.invokepkg.invokedynamic.v2;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SortedInterfacesTest {

    private static final Logger log = LoggerFactory.getLogger(SortedInterfacesTest.class);

    <T> void forAll(Consumer<T> consumer, T... values) {
        for (T t : values) {
            consumer.accept(t);
        }
    }

    public static void main(String[] args) {
        SortedInterfacesTest test = new SortedInterfacesTest();
        test.firstTest();
//        test.secondTest();
    }

    public void firstTest() {
        forAll(e -> e.draw(), new MyShape(), new Universal());
    }

    public void secondTest() {
        log.info("\njava.version {}", System.getProperty("java.version"));
        forAll(Picture::draw, new MyPicture(), new Universal());
    }

    
}

interface Shape {
    void draw();
}

interface Marker {
}

interface Picture {
    void draw();
}

class MyShape implements Marker, Shape {
    public void draw() {
        System.out.println("MyShape draw");
    }
}

class MyPicture implements Marker, Picture {
    public void draw() {
        System.out.println("MyPicture draw");
    }
}

class Universal implements Marker, Picture, Shape {
    public void draw() {
        System.out.println("Universal draw");
    }
}