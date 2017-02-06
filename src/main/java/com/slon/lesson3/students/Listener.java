package com.slon.lesson3.students;

import com.slon.lesson3.students.exceptions.ListenerNotFoundException;
import com.slon.lesson3.students.exceptions.OverflowCourseException;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Sergii on 03.02.2017.
 */
public class Listener {
    public final Course course;
    public final Student student;

    private Mark[] marks = new Mark[20];
    private int markCount = 0;
    private int attendance;

    public Listener(Student student, Course course) throws OverflowCourseException {
        if (student == null || course == null) {
            throw new NullPointerException("student or course cannot be null");
        }
        this.student = student;
        this.course = course;
        course.addListener(this);
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

    public void breakCourse() {
        course.removeListener(this);
    }

    public void deleteStudent() {
        student.removeListener(this);
    }

    public void killListener() {
        breakCourse();
        deleteStudent();
    }
}
