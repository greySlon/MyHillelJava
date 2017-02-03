package com.slon.lesson3.students;

import com.slon.lesson3.students.exceptions.ListenerNotFoundException;
import com.slon.lesson3.students.exceptions.OverflowCourseException;

import java.util.Iterator;


/**
 * Created by Sergii on 03.02.2017.
 */
public class Course implements Iterable<Listener> {
    public final String courseName;

    private int listenerCount;
    private Listener[] listeners;
    private int iteratorPos;

    public Course(String courseName, int maxListners) {
        listeners = new Listener[maxListners];
        this.courseName = courseName;
    }

    public void addListener(Listener listener) throws OverflowCourseException {
        if (listenerCount < listeners.length) {
            listeners[listenerCount++] = listener;
        } else {
            throw new OverflowCourseException("Cousre overflow");
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
            if (pos != listeners.length - 1) {
                System.arraycopy(listeners, pos + 1, listeners, pos, listenerCount - 1 - pos);
            } else {
                listeners[pos] = null;
            }
            listenerCount--;
        } else {
            throw new ListenerNotFoundException("Listener is not found");
        }
    }

    public void stopCourse() {
        for (int i = 0; i < listenerCount; i++) {
            Listener listenerToRemove = listeners[i];
            try {
                listenerToRemove.student.removeListener(listenerToRemove);
            } catch (ListenerNotFoundException e) {
                System.err.println(e.getMessage());
            }
            listeners[i] = null;
        }
    }

    @Override
    public Iterator<Listener> iterator() {
        iteratorPos = 0;
        return new Iterator<Listener>() {
            @Override
            public boolean hasNext() {
                return iteratorPos < listenerCount;
            }

            @Override
            public Listener next() {
                return listeners[iteratorPos++];
            }
        };
    }
}
