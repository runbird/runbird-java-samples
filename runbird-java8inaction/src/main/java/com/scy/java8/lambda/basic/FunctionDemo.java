package com.scy.java8.lambda.basic;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class FunctionDemo {

    public static void main(String[] args) {
        BiFunction<String, Integer, Integer> bifunc = Integer::parseInt;
        Function<String, Integer> function = Integer::parseInt;

        System.out.println(bifunc.apply("10", 10));
        System.out.println(function.apply("10"));

        BiFunction<String, Integer, User> userBiFunction = User::new;
        User foo = userBiFunction.apply("foo", 10);
        Consumer cons = System.out::print;
        cons.accept(foo.getName() + " " + foo.getAge());

        sayHello(String::toLowerCase, "    FOO");
    }

    private static void sayHello(Function<String, String> func, String param) {
        String result = func.apply(param);
        System.out.println(result);
    }

}

class User {
    String name;
    Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
