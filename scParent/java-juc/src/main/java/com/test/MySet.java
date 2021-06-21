package com.test;

import java.util.*;

/**
 * @author ：Breeze
 * @date ：Created in 2021/6/21 1:08
 * @description：
 */
public class MySet {

    public static void main(String[] args) {
        Set<Student> strings = new TreeSet<>(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o2.getAge() - o1.getAge();
            }
        });
        strings.add(new Student("A", 10));
        strings.add(new Student("AB", 11));
        strings.add(new Student("ABCD", 12));
        strings.add(new Student("ABC", 13));
        strings.add(new Student("ABCDE", 14));
        strings.add(new Student("ABCDEF", 15));

        System.out.println(strings);
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

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
