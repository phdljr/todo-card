package com.phdljr.todocard.fixturemonkey.entity;

public class TestEntity {
    private String name;
    private Animal animal;

    public TestEntity() {
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setAnimal(final Animal animal) {
        this.animal = animal;
    }

    @Override
    public String toString() {
        return "TestEntity{" +
            "name='" + name + '\'' +
            ", animal=" + animal +
            '}';
    }
}
