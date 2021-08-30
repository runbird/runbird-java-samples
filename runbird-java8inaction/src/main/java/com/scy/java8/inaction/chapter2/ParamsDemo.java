package com.scy.java8.inaction.chapter2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 类名： ParamsDemo <br>
 * 描述：TODO <br>
 * 创建日期： 2020/7/27 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
interface ApplePredicate {
    boolean test(Apple apple);
}

class Apple {
    private String color;
    private int weight;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Apple(int weight, String color) {
        this.color = color;
        this.weight = weight;
    }
}

class AppleHeavyWeightPredicate implements ApplePredicate {

    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 80;
    }
}

class AppleGreenColorPredicate implements ApplePredicate {

    @Override
    public boolean test(Apple apple) {
        return "green".equals(apple.getColor());
    }
}

interface Predicate<T> {
    boolean test(T t);
}

public class ParamsDemo {

    /**
     * 以往的写法
     */
    public static List<Apple> filterApples(List<Apple> inventory, String color,
                                           int weight, boolean flag) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if ((flag && apple.getColor().equals(color)) ||
                    (!flag && apple.getWeight() > weight)) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 判断逻辑参数化，将逻辑抽出为 predicate
     */
    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            //将判断逻辑参数化
            if (predicate.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 扩大范围，将只限于苹果，进一步扩大
     */
    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T e : list) {
            if (p.test(e)) {
                result.add(e);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red"));

        List<Apple> greenApples = filterApples(inventory, "green", 0, true);

        //进一步
        List<Apple> greenApplesPro = filterApples(inventory, new AppleGreenColorPredicate());

        //使用匿名类
        List<Apple> anonymity = filterApples(inventory, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return "green".equals(apple.getColor());
            }
        });

        //更进一步 使用lambda
        List<Apple> result =
                filterApples(inventory, (Apple apple) -> "red".equals(apple.getColor()));

        //将 List 类型抽象化,不只是Apple，扩大范围
        List<Apple> redApples =
                filter(inventory, (Apple apple) -> "red".equals(apple.getColor()));

        List<Integer> numbers = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8);
        List<Integer> evenNumbers =
                filter(numbers, (Integer i) -> i % 2 == 0);
    }
}

