package com.scy.java8.lambda.basic;

/**
 * 类名： DefaultInterfaceDemo <br>
 * 描述：TODO <br>
 * 创建日期： 2018/7/10 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */



@FunctionalInterface
interface DefaultInterfaceDemo {
    int doubleNum(int i);

    default int second(int x,int y){
        return x+y;
    }
}

@FunctionalInterface
interface DefaultInterfaceDemo2{
    int doubleNum(int i);

    default int second(int x,int y){
        return x+y;
    }
}

//extends 相同的default的时候
@FunctionalInterface
interface DefaultInterfaceDemo3 extends DefaultInterfaceDemo,DefaultInterfaceDemo2{

    @Override //需要指明实现的是 DefaultInterfaceDemo 还是 DefaultInterfaceDemo2
    default int second(int x, int y) {
        return 0;
    }
}
