package com.slon.lesson3.students;

/**
 * Created by Sergii on 03.02.2017.
 */
public class Mark {
    public final String SUBJECT;
    public final Course COURSE;

    private int value;

    public Mark(Course course, String subject, int value) {
        this.value = value;
        SUBJECT = subject;
        COURSE = course;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
