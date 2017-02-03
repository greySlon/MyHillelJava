package com.slon.lesson3.students;

import com.slon.lesson3.students.exceptions.ListenerNotFoundException;
import com.slon.lesson3.students.exceptions.StudentNotFoundException;

import java.util.Arrays;

/**
 * Created by Sergii on 03.02.2017.
 */
public class Student implements Comparable<Student>{
    public final Person person;
    public final Group group;

    private int listenerCount = 0;
    private Listener[] listeners = new Listener[10];


    public Student(Person person, Group group) {
        this.person = person;
        this.group = group;
    }


    public void addListener(Listener listener) {
        if (listenerCount < listeners.length) {
            listeners[listenerCount++] = listener;
        } else {
            listeners = Arrays.copyOf(listeners, listeners.length * 3 / 2);
            listeners[listenerCount++] = listener;
        }
    }

    public void removeListener(Listener listener) throws ListenerNotFoundException {
        int pos;
        boolean listenerFound = false;
        for (pos = 0; pos < listeners.length; pos++) {
            if (listeners[pos] == listener) {
                listenerFound = true;
                break;
            }
        }
        if (listenerFound) {
            if (pos != listenerCount - 1) {
                System.arraycopy(listeners, pos + 1, listeners, pos, listenerCount - 1 - pos);
            } else {
                listeners[pos] = null;
            }
            listenerCount--;
        } else {
            throw new ListenerNotFoundException("Listener is not found");
        }
    }

    public void killStudent() {
        for (int i = 0; i < listenerCount; i++) {
            deleteFromCourse(listeners[i]);
        }
        deleteFromGroup();
    }

    private void deleteFromCourse(Listener listenerToRemove) {
        try {
            listenerToRemove.course.removeListener(listenerToRemove);
        } catch (ListenerNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    private void deleteFromGroup() {
        try {
            group.removeStudent(this);
        } catch (StudentNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public int compareTo(Student o) {
        return person.name.compareTo(o.person.name);
    }

    @Override
    public String toString() {
        return String.format("%s (attends %d cources)", person.name, listenerCount);
    }
}
