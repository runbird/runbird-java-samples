package com.scy.java8.lambda.basic;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GroupByDemo extends Thread {

    public static void main(String[] args) {
        List<Student> list = Stream.of(new Student("foo", 10), new Student("foo1", 20),
                new Student("foo", 30), new Student("fooo1", 30)).collect(Collectors.toList());

        Map<String, List<Student>> byName = list.stream().collect(Collectors.groupingBy(Student::getName));
        Map<Integer, List<Student>> byAge = list.stream().collect(Collectors.groupingBy(Student::getAge));

        Set<Map.Entry<String, List<Student>>> entries = byName.entrySet();
        for (Map.Entry<String, List<Student>> entry : entries) {
            List<Student> sts = entry.getValue();
            for (Student st : sts) {
                System.out.println(entry.getKey() + " : " + st.getName() + "&" + st.getAge());
            }
        }
    }
}

class Student {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}