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

    private boolean contains(Student student) {
        for (int i = 0; i < studentsCount; i++) {
            if (students[i] == student) {
                return true;
            }
        }
        return false;
    }

    public void removeStudent(Student student) throws StudentNotFoundException {
        int pos;
        boolean studentFound = false;
        for (pos = 0; pos < students.length; pos++) {
            if (students[pos] == student) {
                studentFound = true;
                break;
            }
        }
        if (studentFound) {
            if (pos != studentsCount - 1) {
                System.arraycopy(students, pos + 1, students, pos, studentsCount - 1 - pos);
            } else {
                students[pos] = null;
            }
            studentsCount--;
        } else {
            throw new StudentNotFoundException("Student is not found");
        }
    }

    public void clear() {
        for (int i = 0; i < studentsCount; i++) {
            students[i] = null;
        }
    }

    public boolean killStudentByName(String name) {
        Student studentToRemove = null;
        for (int i = 0; i < studentsCount; i++) {
            if (name.equals(students[i].person.name)) {
                studentToRemove = students[i];
                break;
            }
        }
        if (studentToRemove != null) {
            try {
                removeStudent(studentToRemove);
            } catch (StudentNotFoundException e) {
                System.err.println(e.getMessage());
                return false;
            }
            return true;
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
}