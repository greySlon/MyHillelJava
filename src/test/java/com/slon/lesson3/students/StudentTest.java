package com.slon.lesson3.students;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Sergii on 05.02.2017.
 */
class StudentTest {
    Student student;
    Listener listener;
    @BeforeEach
    void setUp() throws Exception{
        Group group=new Group("GRP-1", 2);
        Person person=new Person("Sergii");
        student=new Student(person, group);
        Course course= new Course("Java Elementary", 2);
        listener=new Listener(student,course);
    }

    @Test
    void addListener() {
        student.removeListener(listener);

    }

}