package com.scy.java8.lambda.interfacemethods;

import java.text.DecimalFormat;
import java.util.function.Function;


//--------------------------------------------
// 不用再写interface 使用 Funtion<T,R>
//interface MoneyFormat {
//    String format(int i);
//}
//--------------------------------------------

class MyMoney {
    private final int money;

    public MyMoney(int money) {
        this.money = money;
    }

//  public void printMoney(MoneyFormat moneyFormat) {
//      System.out.println("《======= deposit " + moneyFormat.format(this.money));
//  }

    public void printMoney(Function<Integer, String> moneyFormat) { //函数式接口 Funtion<Integer,String> 输入Integer,输出String
        System.out.println("《======= deposit " + moneyFormat.apply(this.money));
    }
}


public class InterfaceMethodDemo {

    public static void main(String[] args) {
        MyMoney mine = new MyMoney(9999999);
        mine.printMoney(i -> new DecimalFormat("#,###").format(i));
        //lambda表达式不需要知道 接口的名称和方法名称，只需要知道入参i，和实现的方法。  因此可进行优化

        Function<Integer, String> moneyFormat = i -> new DecimalFormat("#,###").format(i);
        mine.printMoney(moneyFormat);

    }
}
