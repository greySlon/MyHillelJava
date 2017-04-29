package com.slon.spring_YT;

import com.slon.spring_YT.interfaces.EventLogger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Sergii on 27.04.2017.
 */
public class App {
    private Client client;
    private EventLogger loger;

    public App(Client client, EventLogger loger) {
        this.client = client;
        this.loger = loger;
    }

    public Event createEvent() {
//        return new Event(new Date(), DateFormat.getDateInstance());
        throw new RuntimeException("Not supported");
    }

    public void logEvent(String msg) {
        Event event = createEvent();
        event.setMsg(msg);
        loger.logEvent(event);
//        msg = msg.replaceAll(client.getId(), client.getFullName());
//        loger.logEvent(new Event(new D));
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("context_YT.xml");
        App app = ctx.getBean("app", App.class);
        app.logEvent("Some event fo user 1!");
        app.logEvent("Some event fo user 2!");
    }
}
