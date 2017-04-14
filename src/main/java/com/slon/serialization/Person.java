package com.slon.serialization;

/**
 * Created by Sergii on 14.04.2017.
 */
public class Person {
    protected String name = "qwert";
    protected int id = 4;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
