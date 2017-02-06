
package com.slon.lesson3.students;

import com.slon.lesson3.students.exceptions.OverflowCourseException;

import java.util.Iterator;


/**
 * Created by Sergii on 03.02.2017.
 */

public class Course implements Iterable<Student> {
    public final String COURSE_NAME;

    private int studentCount;
    private Student[] students;
    private int iteratorPos;

    public Course(String courseName, int maxListners) {
        students = new Student[maxListners];
        this.COURSE_NAME = courseName;
    }

    public void addStudent(Student student) throws OverflowCourseException {
        if (studentCount < students.length) {
            students[studentCount++] = student;
        } else {
            throw new OverflowCourseException("Cousre overflow");
        }
    }

    public boolean removeStudent(Student student) {
        int pos = indexOf(student);
        if (pos != -1) {
            students[pos].finishCourse(this);
            int numMoved = students.length - 1 - pos;
            if (numMoved > 0) {
                System.arraycopy(students, pos + 1, students, pos, numMoved);
            }
            students[--studentCount] = null;
            return true;
        } else {
            return false;
        }
    }

    public void terminate() {
        for (int i = 0; i < studentCount; i++) {
            students[i].finishCourse(this);
        }
        clear();
    }

    @Override
    public Iterator<Student> iterator() {
        iteratorPos = 0;
        return new Iterator<Student>() {
            @Override
            public boolean hasNext() {
                return iteratorPos < studentCount;
            }

            @Override
            public Student next() {
                return students[iteratorPos++];
            }
        };
    }

    private void clear() {
        for (int i = 0; i < students.length; i++) {
            students[i] = null;
        }
    }

    private int indexOf(Student student) {
        int pos;
        for (pos = 0; pos < students.length; pos++) {
            if (students[pos] == student) {
                return pos;
            }
        }
        return -1;
    }
}
