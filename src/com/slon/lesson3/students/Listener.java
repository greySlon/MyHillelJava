package com.slon.lesson3.students;

import com.slon.lesson3.students.exceptions.ListenerNotFoundException;

import java.util.Arrays;

/**
 * Created by Sergii on 03.02.2017.
 */
public class Listener {
    public final Course course;
    public final Student student;

    private Mark[] marks = new Mark[20];
    private int markCount = 0;
    private int attendance;

    public Listener(Student student, Course course) {
        this.student = student;
        this.course = course;
        student.addListener(this);
    }

    public void setMark(Mark mark) {
        if (markCount < marks.length) {
            marks[markCount++] = mark;
        } else {
            marks = Arrays.copyOf(marks, marks.length * 3 / 2);
            marks[markCount++] = mark;
        }
    }

    public void addAttendance() {
        attendance++;
    }

    public int getAttendance() {
        return attendance;
    }
}
