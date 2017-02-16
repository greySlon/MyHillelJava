package com.slon.lesson3.students;

import com.slon.lesson3.students.exceptions.OverflowCourseException;
import com.slon.lesson3.students.exceptions.OverflowGroupException;

import java.util.*;

/**
 * Created by Sergii on 03.02.2017.
 */
public class Student implements Comparable<Student> {
    public final Person person;

    private Group group;
    private List<Mark> markList = new ArrayList<>();
    private Map<String, Integer> attendance = new HashMap<>();
    private List<Course> courseList = new ArrayList<>();

    public Student(Person person) {
        this.person = person;
    }

    public Student(Person person, Group group) {
        this.person = person;
        this.group = group;
    }


    public void setGroup(Group group) {
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }

    public void finishCourse(Course course) {
        courseList.remove(course);
    }

    public void startCourse(Course course) {
        courseList.add(course);
    }

    public void attend(Course course) {
        if (attendance.containsKey(course.COURSE_NAME)) {
            int value = attendance.get(course.COURSE_NAME);
            attendance.put(course.COURSE_NAME, value + 1);
        } else {
            attendance.put(course.COURSE_NAME, 1);
        }
    }

    public void addMark(Mark mark) {
        markList.add(mark);
    }

    public List<Mark> getMarkList(){
        return Collections.unmodifiableList(markList);
    }

    public Iterator<Mark> getMarkIterator() {
        return markList.iterator();
    }

    public Iterator<Course> getCourseIterator() {
        return courseList.iterator();
    }

    @Override
    public int compareTo(Student o) {
        return person.getName().compareTo(o.person.getName());
    }

    @Override
    public String toString() {
        return String.format("%s (attends %d cources)", person.getName(), courseList.size());
    }
}
