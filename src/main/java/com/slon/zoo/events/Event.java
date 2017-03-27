package com.slon.zoo.events;

import java.util.Date;

/**
 * Created by Sergii on 27.03.2017.
 */
public class Event {
    private Date eventDate;
    private int senderId;
    private String message;

    public Event(int senderId, String message) {
        this.senderId = senderId;
        this.message = message;
        this.eventDate=new Date();
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventDate=" + eventDate +
                ", senderId=" + senderId +
                ", message='" + message + '\'' +
                '}';
    }
}
