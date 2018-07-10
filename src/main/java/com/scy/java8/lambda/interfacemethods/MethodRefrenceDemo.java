package com.scy.java8.lambda.interfacemethods;

import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

/**
 * 类名： PredicateDemo <br>
 * 描述：TODO <br>
 * 创建日期： 2018/7/9 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public class MethodRefrenceDemo {

    public static void main(String[] args) {
        //断言函数接口 predicate
        Predicate<Integer> predicate = i -> i>0;
        //int类型断言，可不用泛型
        IntPredicate predicate1 = p -> p>0;
        System.out.println(predicate.test(-9));
        System.out.println(predicate1.test(8));

        //消费函数
        Consumer<String> consumer = s -> System.out.println(s);
        consumer.accept("《====== 输入数据1");
        consumer = System.out::println;
        consumer.accept("《====== 输入数据2");
        //int类型消费函数 右边简写
        IntConsumer consumer1 = System.out::println;
        consumer1.accept(1);

        //静态方法的引用
        Consumer<Dog> dogConsumer = System.out::println;
        dogConsumer.accept(new Dog());

    }
}

class Dog{
    private String name = "litte dog";
    //初始数量
    private int food = 10;

    //静态方法
    public static void bark(Dog dog) {
        System.out.println(dog+" wong wong wong !");
    }

    //非静态方法
    public int eat(int num) {
        System.out.println("吃了" + num + "斤");
        this.food -=num;
        return this.food;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
