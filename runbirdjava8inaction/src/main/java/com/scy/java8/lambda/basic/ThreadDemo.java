package com.scy.java8.lambda.basic;

/**
 * 类名： thread <br>
 * 描述：TODO <br>
 * 创建日期： 2018/7/9 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public class ThreadDemo {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("<====== thread before JDK1.8");
            }
        }).start();

        //jav8
        new Thread(() -> System.out.println("<====== thread in JDK1.8")).start();
//------------------------------------------------------------------------------------------

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("<====== thread before JDK1.8");
            }
        };
        new Thread(runnable).start();

        Runnable target = () -> System.out.println("<====== thread before JDK1.8");//ctrl+alt+m
        new Thread(target).start();
//-------------------------------------------------------------------------------------------
        Runnable target1 = () -> System.out.println("<====== thread before JDK1.8");
        System.out.println(target == target1);//false
//-------------------------------------------------------------------------------------------
        //强转
        Object target2 = (Runnable) () -> System.out.println("function interface force translate");
        new Thread((Runnable) target2).start();
    }
}
