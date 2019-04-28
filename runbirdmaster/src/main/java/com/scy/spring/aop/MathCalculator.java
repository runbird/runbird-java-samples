package com.scy.spring.aop;


public class MathCalculator {

    public int div(int i, int j) {
        System.out.println("---> div 执行：" + i / j);
        return i / j;
    }

    public static void main(String[] args) {
        MathCalculator mathCalculator = new MathCalculator();
        int div = mathCalculator.div(10, 2);
        System.out.println(" div:"+div);
        System.out.println("======================================");

    }
}
