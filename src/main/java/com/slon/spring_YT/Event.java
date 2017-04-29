package com.slon.spring_YT;

import com.google.common.base.MoreObjects;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by Sergii on 27.04.2017.
 */
public class Event {
    private static Random rand = new Random();
    private int id;
    private String msg;
    private Date date;
    private DateFormat dateFormat;

    public Event(Date date, DateFormat format) {
        this.dateFormat = format;
        this.date = date;
        this.id = rand.nextInt();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .omitNullValues()
                .add("Id", id)
                .add("Message", msg)
                .add("Date", dateFormat.format(date))
                .toString();
    }
}
