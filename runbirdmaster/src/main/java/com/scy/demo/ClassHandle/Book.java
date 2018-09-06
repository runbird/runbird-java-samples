package com.scy.demo.ClassHandle;

/**
 * 类名： Book <br>
 * 描述：TODO <br>
 * 创建日期： 2018/6/20 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public class Book {
    public static void main(String[] args) {
        System.out.println("Hello ShuYi." + amount);
        //new Book();
    }

    Book() {
        System.out.println("书的构造方法");
        System.out.println("price=" + price + ",amount=" + amount);
    }

    {
        System.out.println("书的普通代码块");
    }

    int price = 110;

    static {
        System.out.println("书的静态代码块");
    }

    static int amount = 112;
}
