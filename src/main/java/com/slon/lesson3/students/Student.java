package com.slon.lesson3.students;

import com.slon.lesson3.students.exceptions.OverflowGroupException;

import java.util.Arrays;

/**
 * Created by Sergii on 03.02.2017.
 */
public class Student implements Comparable<Student> {
    public final Person person;

    private Group group;
    private int listenerCount = 0;
    private Listener[] listeners = new Listener[10];

    public Student(Person person, Group group) throws OverflowGroupException {
        this.person = person;
        this.group = group;
        group.addStudent(this);
    }


    public void addListener(Listener listener) {
        if (listenerCount < listeners.length) {
            listeners[listenerCount++] = listener;
        } else {
            listeners = Arrays.copyOf(listeners, listeners.length * 3 / 2);
            listeners[listenerCount++] = listener;
        }
    }

    public boolean removeListener(Listener listener) {
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
            return true;
        } else {
            return false;
        }
    }

    public void killStudent() {
        for (int i = 0; i < listenerCount; i++) {
            listeners[i].breakCourse();
        }
        clear();
        breakUpGroup();
    }

    private void clear() {
        for (int i = 0; i < listeners.length; i++) {
            listeners[i] = null;
        }
    }

    private void breakUpGroup() {
        group.removeStudent(this);
        outOfGroup();
    }

    public void outOfGroup(){
        group=null;
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
