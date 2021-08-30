package com.scy.java8.inaction.chapter5;

/**
 * 类名： TransactionDemo <br>
 * 描述：TODO <br>
 * 创建日期： 2020/8/26 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public class TransactionDemo {

    public static void main(String[] args) {
        Trader jim = new Trader("Jim", "BJ");
        Trader bob = new Trader("bob", "SH");
        Trader jack = new Trader("jack", "SZ");
        Trader lucy = new Trader("lucy", "GZ");

    }
}

class Trader {
    private final String name;

    private final String city;

    public Trader(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Trader{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}

class Transaction {
    private final Trader trader;

    private final int year;

    private final int value;

    public Transaction(Trader trader, int year, int value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }

    public Trader getTrader() {
        return trader;
    }

    public int getYear() {
        return year;
    }

    public int getValue() {
        return value;
    }
}