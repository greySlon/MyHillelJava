package com.slon.lesson3.students;

import com.slon.lesson3.students.exceptions.OverflowGroupException;
import com.slon.lesson3.students.exceptions.StudentNotFoundException;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by Sergii on 03.02.2017.
 */
public class Group implements Iterable<Student> {
    public final String groupName;

    private int studentsCount;
    private Student[] students;
    private int iteratorPos;

    public Group(String groupName, int maxStudent) {
        this.groupName = groupName;
        students = new Student[maxStudent];
    }


    public void addStudent(Student student) throws OverflowGroupException {
        if (studentsCount < students.length) {
            if (!contains(student)) {
                students[studentsCount++] = student;
            }
        } else {
            throw new OverflowGroupException("Group overflow");
        }
    }

    public boolean removeStudent(Student student) {
        int pos=indexOf(student);
        if (pos!=-1) {
            students[pos].outOfGroup();
            if (pos != studentsCount - 1) {
                System.arraycopy(students, pos + 1, students, pos, students.length - 1 - pos);
            } else {
                students[pos] = null;
            }
            studentsCount--;
            return true;
        } else {
            return false;
        }
    }

    private int indexOf(Student student){
        int pos;
        for (pos = 0; pos < students.length; pos++) {
            if (students[pos] == student) {
                return pos;
            }
        }
        return -1;
    }

    public void clear() {
        for (int i = 0; i < studentsCount; i++) {
            students[i].outOfGroup();
            students[i] = null;
        }
    }

    public boolean killStudentByName(String name) {
        Student student=findStudentByName(name);
        if (student != null) {
            return removeStudent(student);
        } else {
            return false;
        }
    }

    public boolean contains(String name) {
        for (int i = 0; i < studentsCount; i++) {
            if (name.equals(students[i].person.name)) {
                return true;
            }
        }
        return false;
    }

    public void union(Group anotherGroup) throws OverflowGroupException {
        if (this.studentsCount + anotherGroup.studentsCount <= this.students.length) {
            System.arraycopy(anotherGroup, 0, students, studentsCount, anotherGroup.studentsCount);
        } else {
            throw new OverflowGroupException("Group overflow");
        }
    }

    public void sortByName() {
        Arrays.sort(students);
    }

    public void print() {
        System.out.println(String.format("Group: %s", groupName));
        System.out.println(String.format("Total count of students: %d", studentsCount));

        int i = 1;
        for (Student student : this) {
            System.out.println(i + ". " + student.toString());
        }
    }

    public boolean equals(Group group) {
        if (studentsCount != group.studentsCount) {
            return false;
        } else {
            for (Student student : group) {
                if (!contains(student)) {
                    return false;
                }
            }
            return true;
        }
    }

    @Override
    public Iterator<Student> iterator() {
        iteratorPos = 0;
        return new Iterator<Student>() {
            @Override
            public boolean hasNext() {
                return iteratorPos < studentsCount;
            }

            @Override
            public Student next() {
                return students[iteratorPos++];
            }
        };
    }

    private boolean contains(Student student) {
        for (int i = 0; i < studentsCount; i++) {
            if (students[i] == student) {
                return true;
            }
        }
        return false;
    }

    private Student findStudentByName(String name){
        Student student = null;
        for (int i = 0; i < studentsCount; i++) {
            if (name.equals(students[i].person.name)) {
                student = students[i];
                break;
            }
        }
        return student;
    }
}