package com.slon.zoo.springEvents;

import org.springframework.context.ApplicationEvent;

import java.util.Date;

/**
 * Created by Sergii on 04.04.2017.
 */
public class FurCareEvent extends ApplicationEvent {
    private String message;
    private Date date;

    public void setDate(Date date) {
        this.date = date;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public FurCareEvent(Object source, String message) {
        super(source);
        this.message = message;
        this.date = new Date();
    }

    @Override
    public String toString() {
        return "FurCareEvent{" +
                "message='" + message + '\'' +
                ", date=" + date +
                '}';
    }
}
