package com.scy.java8.inaction.chapter5;

import com.scy.chapter4.Menu;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 类名： MapDemo1 <br>
 * 描述：TODO <br>
 * 创建日期： 2020/8/26 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public class MapDemo1 {

    public static void main(String[] args) {
        List<Menu> menu = Arrays.asList(new Menu("soup", 30.00, 1000),
                new Menu("beef", 100.00, 5000), new Menu("chicken", 60.00, 2000));
        //map 使用方法
        List<String> names = menu.stream()
                .filter(d -> {
                    System.out.println("filtering :" + d);
                    return d.getPrice() > 30.00;
                })
                .map(d -> {
                    System.out.println("mapping: " + d.getName());
                    return d.getName();
                })
                .limit(3)
                .collect(Collectors.toList());
    }
}