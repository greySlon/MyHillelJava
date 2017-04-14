package com.slon.serialization;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergii on 14.04.2017.
 */
public class Student extends Person {
    private String courseName = "Java elementary";
    private List<String> list = new ArrayList<>();

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void add(String s) {
        list.add(s);
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", courseName='" + courseName + '\'' +
                ", list=" + list +
                '}';
    }
}
