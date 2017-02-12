/*
package com.slon.lesson3.students;

import com.slon.lesson3.students.exceptions.OverflowCourseException;
import com.slon.lesson3.students.exceptions.OverflowGroupException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

*/
/**
 * Created by Sergii on 07.02.2017.
 *//*

class GroupTest {
    Group group;
    Student student, student2, student3;

    @BeforeEach
    void setUp() {
        student=new Student(new Person("Sergii"));
        student2=new Student(new Person("Sergii2"));
        student3=new Student(new Person("Sergii3"));
        group=new Group("Group-1", 2);
    }

    @Test
    void addStudent() throws Exception{
        assertTrue(group.addStudent(student));
        assertFalse(group.addStudent(student));
        assertTrue(group.addStudent(student2));
        assertThrows(OverflowGroupException.class, ()->group.addStudent(student3));
    }

}*/
