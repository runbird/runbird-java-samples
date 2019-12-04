package com.scy.java8.lambda.curry;

import java.util.function.Function;

/**
 * 级联表达式 和 柯里化
 * 柯里化:把舵哥参数的函数转换为只有一个参数的函数
 * 柯里化目的：函数标准化（所有的函数只有一个参数）
 * 高阶函数：返回函数的函数
 */
public class CurryDemo {

    public static void main(String[] args) {
        //    级联表达式 和 柯里化
        //    x -> y -> x + y;
        Function<Integer, Function<Integer, Integer>> function =
                x -> y -> x + y;
        System.out.println(function.apply(2).apply(3));

        //    x->y->z->x+y+z;
        Function<Integer, Function<Integer, Function<Integer, Integer>>> function2 =
                x -> y -> z -> x + y + z;
        System.out.println(function2.apply(1).apply(1).apply(1));

        int[] nums = {1, 2, 3};
        Function f = function2;
        for (int i = 0; i < nums.length; i++) {
            if (f instanceof Function) {
                Object obj = f.apply(nums[i]);
                if (obj instanceof Function) {
                    f = (Function) obj;
                } else {
                    System.out.println("调用的结果为" + obj);
                }
            }
        }
    }
}
