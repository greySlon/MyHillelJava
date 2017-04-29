package com.slon.zoo.springEvents;

import org.springframework.context.ApplicationEvent;

import java.util.Date;

/**
 * Created by Sergii on 04.04.2017.
 */
public class DiseaseEvent extends ApplicationEvent {
    private String message;
    private Date date;

    public DiseaseEvent(Object source, String message) {
        super(source);
        this.message = message;
        this.date = new Date();
    }

    @Override
    public String toString() {
        return "DiseaseEvent{" +
                "message='" + message + '\'' +
                ", date=" + date +
                '}';
    }
}
