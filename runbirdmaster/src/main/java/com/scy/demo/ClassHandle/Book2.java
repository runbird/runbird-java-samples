package com.scy.demo.ClassHandle;

/**
 * @author suocaiyuan
 * @version V1.0
 * 类名： InitializationDemo3 <br>
 * 描述：
 * <p>
 * 在上面两个例子中，因为 main 方法所在类并没有多余的代码，我们都直接忽略了 main 方法所在类的初始化。
 * <p>
 * 但在这个例子中，main 方法所在类有许多代码，我们就并不能直接忽略了。
 * <p>
 * 当 JVM 在准备阶段的时候，便会为类变量分配内存和进行初始化。此时，我们的 book2 实例变量被初始化为 null，amount 变量被初始化为 0。
 * <p>
 * 当进入初始化阶段后，因为 Book 方法是程序的入口，根据我们上面说到的类初始化的五种情况的第四种
 * （当虚拟机启动时，用户需要指定一个要执行的主类（包含main()方法的那个类），虚拟机会先初始化这个主类）。
 * 所以JVM 会初始化 Book2 类，即执行类构造器 。
 * <p>
 * JVM 对 Book 类进行初始化首先是执行类构造器（按顺序收集类中所有静态代码块和类变量赋值语句就组成了类构造器 ），
 * 后执行对象的构造器（按顺序收集成员变量赋值和普通代码块，最后收集对象构造器，最终组成对象构造器 ）。<br>
 * <p>
 * 创建日期： 2018/6/20 <br>
 */
public class Book2 {
    public static void main(String[] args) {
        staticFunction();
    }

    static Book2 book = new Book2();

    static {
        System.out.println("书的静态代码块");
    }

    {
        System.out.println("书的普通代码块");
    }

    Book2() {
        System.out.println("书的构造方法");
        System.out.println("price=" + price + ",amount=" + amount);
    }

    public static void staticFunction() {
        System.out.println("书的静态方法");
    }

    int price = 110;
    static int amount = 112;
}
