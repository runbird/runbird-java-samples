package com.scy.java8.lambda;

/**
 * 类名： LambdaDemo <br>
 * 描述：TODO <br>
 * 创建日期： 2018/7/9 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */


//函数接口  单一责任制，一个接口只有一个要实现的事情
@FunctionalInterface //可以加，明示不要加其他的方法
interface lambdaInterface {

    int doubleNum(int i);

    //该接口只有一个 {要实现} 的方法，因为1.8有default
    // int secondNum();

    default int second(int x,int y){
        return x+y;
    }
}

public class LambdaDemo {
    public static void main(String[] args) {

        lambdaInterface interf1 = (i) -> i * 2;
        System.out.println(interf1.second(2,3));
        System.out.println(interf1.doubleNum(5));

        //只有一个参数，常见的写法
        lambdaInterface interf2 = i -> i * 2;

        lambdaInterface interf3 = (int i) -> i * 2;


        //方法体多的情况
        lambdaInterface interf4 = (int i) -> {
            System.out.println("hello lambda!!");
            return i*2;
        };

    }
}
