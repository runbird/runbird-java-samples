package com.scy.java8.stream;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.commons.collections4.MapUtils;

/**
 * 收集器
 *
 * @author suocaiyuan
 */

public class CollectDemo {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("Daive", 10, Gender.MALE, Grade.FOUR),
                new Student("Ame", 13, Gender.MALE, Grade.ONE),
                new Student("Cindy", 11, Gender.FEMALE, Grade.THREE),
                new Student("Bob", 12, Gender.MALE, Grade.TWO),
                new Student("Ellen", 13, Gender.FEMALE, Grade.ONE),
                new Student("Ford", 12, Gender.MALE, Grade.TWO),
                new Student("Gland", 11, Gender.MALE, Grade.THREE));

        //使用collector收集器
        //List<Student> students2 = students.stream()
        //		.collect(Collectors.toList());
        List<Integer> ageList = students.stream().map(s -> s.getAge())
                .collect(Collectors.toList());
        //s -> s.getAge() Student::getAge 可以用方法引用  替换
        Set<Integer> ageSet = students.stream().map(Student::getAge)
                .collect(Collectors.toSet());
        //自定义集合类型
        TreeSet<String> nameTreeSet = students.stream().map(Student::getName)
                .collect(Collectors.toCollection(TreeSet::new));
        System.out.println("<====== all age of students" + ageList.toString());
        System.out.println("<====== all age of students" + ageSet.toString());
        System.out.println("<====== all age of students" + nameTreeSet.toString());

        //collectors下方法： summarizing average
        IntSummaryStatistics sumAge = students.stream()
                .collect(Collectors.summarizingInt(Student::getAge));
        System.out.println("<====== age partition:" + sumAge);

        //分块   根据条件进行分块
        Map<Boolean, List<Student>> gender = students.stream()
                .collect(Collectors.partitioningBy(s -> s.getGender() == Gender.MALE));
        System.out.println("<====== female and male:" + gender.toString());

        MapUtils.verbosePrint(System.out, "female and male", gender);

        //分组  根据条件进行分组
        Map<Grade, List<Student>> group = students.stream()
                .collect(Collectors.groupingBy(Student::getGrade));
        MapUtils.verbosePrint(System.out, "group by grade", group);

        //对group 结果进一步操作 获取各个班级学生的个数
        //Map<Grade, List<Student>> ==> Map<Grade, Long>
        Map<Grade, Long> countGroup = students.stream()
                .collect(Collectors.groupingBy(Student::getGrade,
                        Collectors.counting()));
        MapUtils.verbosePrint(System.out, "group by grade count", countGroup);

        //Map<Grade, List<Student>> ==> Map<Grade, IntSummaryStatistics>
        Map<Grade, IntSummaryStatistics> ageGroup = students.stream()
                .collect(Collectors.groupingBy(Student::getGrade,
                        Collectors.summarizingInt(Student::getAge)));
        MapUtils.verbosePrint(System.out, "group by age count", ageGroup);
    }
}

class Student {

    private String name;

    private Integer age;

    private Gender gender;

    private Grade grade;

    public Student(String name, Integer age, Gender gender, Grade grade) {
        super();
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.grade = grade;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", age=" + age + ", gender=" + gender + ", grade=" + grade + "]";
    }

}

/**
 * 性别
 *
 * @author suocaiyuan
 */
enum Gender {
    FEMALE, MALE
}

/**
 * 班级
 *
 * @author suocaiyuan
 */
enum Grade {
    ONE, TWO, THREE, FOUR
}
