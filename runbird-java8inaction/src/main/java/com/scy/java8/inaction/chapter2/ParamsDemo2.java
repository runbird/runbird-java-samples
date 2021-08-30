package com.scy.java8.inaction.chapter2;

import java.util.Arrays;
import java.util.List;

/**
 * 类名： ParamsDemo2 <br>
 * 描述：TODO <br>
 * 创建日期： 2020/7/29 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */

//java.util.Comparator
//interface Comparator<T>{
//    int compare(T o1, T o2);
//}

public class ParamsDemo2 {

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red"));

//        inventory.sort(new Comparator<Apple>() {
//            @Override
//            public int compare(Apple o1, Apple o2) {
//                return o1.getWeight().compareTo(o2.getWeight());
//            }
//        });

//        inventory.sort((Apple o1,Apple o2) -> o1.getWeight().compareTo(o2.getWeight()));
    }

}
