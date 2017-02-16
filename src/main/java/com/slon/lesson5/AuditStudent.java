package com.slon.lesson5;

import com.slon.lesson3.students.Group;
import com.slon.lesson3.students.Mark;
import com.slon.lesson3.students.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergii on 16.02.2017.
 */
public class AuditStudent extends ArrayList<Student> {

    public boolean addStudent(Student student) {
        return super.add(student);
    }

    public boolean removeStudent(Student student) {
        return super.remove(student);
    }

    public Student getStudentByName(String name) {
        for (Student student : this) {
            if (name.equals(student.person.getName())) {
                return student;
            }
        }
        return null;
    }

    public List<Mark> getStudentMarks(String studentName) {
        Student student = getStudentByName(studentName);
        return student.getMarkList();
    }

    public List<Student> getStudentsByGroupName(String groupName) {
        List<Student> studentList = new ArrayList<>();
        int i = 0;
        for (Student student : this) {
            if(groupName.equals(student.getGroup().GROUP_NAME)){
                studentList.add(student);
            }
        }
        return studentList;
    }
}
