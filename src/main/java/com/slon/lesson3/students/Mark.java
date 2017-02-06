package com.slon.lesson3.students;

/**
 * Created by Sergii on 03.02.2017.
 */
public class Mark {
    private int value;
    private String Subject;

    public Mark(int value, String subject) {
        this.value = value;
        Subject = subject;
    }

    public String getSubject() {
        return Subject;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
