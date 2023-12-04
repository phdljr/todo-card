package com.phdljr.todocard.fixturemonkey.entity;

public class Animal {
    private String type;
    private int age;
    private String name;

    @Override
    public String toString() {
        return "Animal{" +
            "type='" + type + '\'' +
            ", age=" + age +
            ", name='" + name + '\'' +
            '}';
    }
}
