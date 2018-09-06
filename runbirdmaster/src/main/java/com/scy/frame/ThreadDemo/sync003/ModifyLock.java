package com.scy.frame.ThreadDemo.sync003;

/**
 * 同一对象属性的修改不会影响锁的情况
 * 对一个对象进行操作 sychronieze 这种情况下可以同步
 * 对象发生改变不影响 lock
 *
 * @author alienware
 */
public class ModifyLock {

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public synchronized void changeAttributte(String name, int age) {

        try {
            System.out.println("<======= 当前线程 : " + Thread.currentThread().getName() + " 开始");
            this.setName(name);
            this.setAge(age);

            System.out.println("当前线程 : " + Thread.currentThread().getName() + " 修改对象内容为： "
                    + this.getName() + ", " + this.getAge());
            Thread.sleep(2000);
            System.out.println("当前线程 : " + Thread.currentThread().getName() + " 结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        final ModifyLock modifyLock = new ModifyLock();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                modifyLock.changeAttributte("张三", 20);
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                modifyLock.changeAttributte("李四", 21);
            }
        }, "t2");

        t1.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }
}
