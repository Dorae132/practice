package com.nsb.practice.excel;

/**
 * 
 * @author Dorae
 *
 */
public class Main {

    public static void main(String[] args) throws Exception {
//        POIUtil.MathRandomCastTime();
        long beginTime = System.currentTimeMillis();

        String path = "C:\\Users\\Dorae\\Desktop\\test.xlsx";
//        POIUtil.Excel2007AboveOperate(path);
        POIUtil.Excel2007AboveOperateOld(path);
        long endTime = System.currentTimeMillis();

        System.out.println("Cast time : " + (endTime - beginTime));
    }
}
