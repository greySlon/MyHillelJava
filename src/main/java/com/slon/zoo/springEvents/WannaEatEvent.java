package com.slon.zoo.springEvents;

import org.springframework.context.ApplicationEvent;

import java.util.Date;

/**
 * Created by Sergii on 04.04.2017.
 */
public class WannaEatEvent extends ApplicationEvent {
    private String message;
    private Date date;

    public WannaEatEvent(Object source, String message) {
        super(source);
        this.message = message;
        this.date = new Date();
    }

    @Override
    public String toString() {
        return "WannaEatEvent{" +
                "message='" + message + '\'' +
                ", date=" + date +
                '}';
    }
}
