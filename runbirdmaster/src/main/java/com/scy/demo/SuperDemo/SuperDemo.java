package com.scy.demo.SuperDemo;

/**
 * 类名： SuperDemo <br>
 * 描述：TODO <br>
 * 创建日期： 2018/5/23 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public class SuperDemo {

    public static void main(String[] args) {

    }

}

class Animal {

    String name;

    String color;

    public Animal(String name, String color) {
        this.name = name;
        this.color = color;
    }
}

class Dog extends Animal {


    public Dog(String name, String color) {
        super(name, color);
        // 有参数的构造方法 需要 使用 super(name, color);
        /*this.name = name;
        this.color = color;
        没有使用这个的话，编译器会自动加上 super();
        */
    }
}

