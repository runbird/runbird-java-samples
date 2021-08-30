package com.scy.java8.inaction.chapter4;

/**
 * 类名： Menu <br>
 * 描述：TODO <br>
 * 创建日期： 2020/8/26 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public class Menu {
    String name;
    Double price;
    int caloric;

    public Menu(String name, Double price, Integer caloric) {
        this.name = name;
        this.price = price;
        this.caloric = caloric;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getCaloric() {
        return caloric;
    }

    public void setCaloric(int caloric) {
        this.caloric = caloric;
    }
}
