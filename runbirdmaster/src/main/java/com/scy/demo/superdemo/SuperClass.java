package com.scy.demo.superdemo;

/**
 * 类名： SuperClass <br>
 * 描述：TODO <br>
 * 创建日期： 2018/6/14 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public class SuperClass {

    public static void main(String[] args) {
        Son son = new Son();
        son.run();
      /*  Son s = new Son(3);
        System.out.println(s.y);// 4*/
    }

    static class Father {
        int x = 1;

        Father() {
            System.out.println("这是父类无参构造");
        }

        Father(int x) {

            this.x = x;
            System.out.println("这是父类有参构造");
        }

        void speak() {
            System.out.println("我是父亲");
        }
    }

    static class Son extends Father {
        int y = 1;

        Son() {
            System.out.println("这是子类的无参构造");
        }

        Son(int y) {

            this.y = y + x;
            System.out.println("这是子类的有参构造");
        }

        void run() {
            // super.speak(); // 访问父类的函数
            System.out.println("我是儿子");
        }
    }

}
