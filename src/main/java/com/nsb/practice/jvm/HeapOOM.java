package com.nsb.practice.jvm;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * -Xms20M -Xmx20M -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+HeapDumpOnOutOfMemoryError
 * @author Dorae
 *
 */
public class HeapOOM {

    static class OOMObject {

    }

    public static void main(String[] args) {
        List<OOMObject> list = Lists.newArrayList();
        while (true) {
            list.add(new OOMObject());
        }
    }
}
