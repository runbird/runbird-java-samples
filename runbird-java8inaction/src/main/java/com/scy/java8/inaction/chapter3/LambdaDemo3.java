package com.scy.java8.inaction.chapter3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 类名： LambdaDemo3 <br>
 * 描述：TODO <br>
 * 创建日期： 2020/8/21 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public class LambdaDemo3 {

    //1 函数接口传递行为
    @FunctionalInterface
    public interface Predicate<T> {
        boolean test(T t);
    }

    //2 执行行为
    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T s : list) {
            if (p.test(s)) {
                result.add(s);
            }
        }
        return result;
    }

    //========================================================================================================================
    @FunctionalInterface
    public interface Consumer<T> {
        void accpet(T t);
    }

    public static <T> void forEach(List<T> list, Consumer<T> consumer) {
        for (T i : list) {
            consumer.accpet(i);
        }
    }

    @FunctionalInterface
    public interface Function<T,R>{
        R apply(T t);
    }

    public static <T,R> List<R> map(List<T> list, Function<T,R> f){
        List<R> result = new ArrayList<>();
        for (T s : list) {
            result.add(f.apply(s));
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("ccc", "aaa", null);
        //3 传递lambda
        Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
        List<String> nonEmpty = filter(list, nonEmptyStringPredicate);

        //consumer
        forEach(list,(String s) -> System.out.println(s));
        forEach(list, System.out::println);

        //Function
        List<Integer> result = map(list, (String s) -> s.length());
        List<Integer> results = map(list, String::length);
    }
}
