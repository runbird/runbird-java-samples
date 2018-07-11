package com.scy.java8.lambda.interfacemethods;

import java.util.function.*;

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
        System.out.println(predicate.test(-9)); //false
        System.out.println(predicate1.test(8)); //ture

        //消费函数
        Consumer<String> consumer = s -> System.out.println(s);
        consumer.accept("《====== 输入数据1");// 《====== 输入数据1
        Consumer<String> consumer2 = System.out::println;
        consumer2.accept("《====== 输入数据2");// 《====== 输入数据2
        //int类型消费函数 右边简写
        IntConsumer consumer3 = System.out::println;// 1
        consumer3.accept(1);

        Dog dog = new Dog();
        //静态方法的引用
        Consumer<Dog> dogConsumer = Dog::bark;
        dogConsumer.accept(dog);

        //非静态方法，使用对象实例的方法引用 实例化了dog
        Function<Integer,Integer> function = dog::eat;
        System.out.println("还剩下"+function.apply(4)+"斤");//还剩下6斤
        
        //输入输出同一类型可改进
        UnaryOperator<Integer> functionOfUnaryOperator = dog::eat;
        System.out.println("还剩下"+functionOfUnaryOperator.apply(3)+"斤");
        
        //JDK提供的Integer类型
        IntUnaryOperator functionOfIntUnary = dog::eat;
        System.out.println("还剩下"+functionOfIntUnary.applyAsInt(3)+"斤");

        //---------- Java 传值引用 不为null》
        IntUnaryOperator functionForNull = dog::eat;
        dog = null; //52行不为NULL
        System.out.println("null :"+functionForNull.applyAsInt(3)+"斤");

        //使用类名来引用非静态方法
        BiFunction<Dog,Integer,Integer> biFunction = Dog::eat; 
        System.out.println("还剩下"+biFunction.apply(dog,3)+"斤"); //51行为null,这里报错
        
        // 构造函数的引用
        // 没有参数的构造函数可以理解为一个 没有输入(),输出为对象dog的提供者
        Supplier<Dog> supplierOfDog = Dog::new;
        System.out.println("创建了新对象"+supplierOfDog.get());

        // 有参数的构造函数
        Function<String,Dog> functionOfDog = Dog::new;
        System.out.println("创建了新对象"+functionOfDog.apply("big dog"));


    }
}

class Dog{
    //无参的构造函数
    public Dog() {}
    //带参数的构造函数
    public Dog(String name) {
        this.name = name;
    }

    private String name = "litte dog";
    //初始数量
    private int food = 100;

    // --- 静态方法
    public static void bark(Dog dog) {
        System.out.println(dog+" wong wong wong !");//litte dog wong wong wong !
    }

    /**
     * @param num
     * @return int<br>
     * 方法描述： eat(Dog this,int num) JDK默认会把当前实例传入到非静态方法，参数名为this <br>
     * 作者： suocaiyuan<br>
     * 创建日期： 2018/7/11<br>
     * 版本： V1.0<br>
     */
    // --- 非静态方法
    public int eat(Dog this,int num) { // <---------------------->
        System.out.println("吃了" + num + "斤");//吃了4斤
        this.food -=num;
        return this.food;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
