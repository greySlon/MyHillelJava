package com.slon.spring_YT;

import com.slon.spring_YT.interfaces.EventLogger;

/**
 * Created by Sergii on 27.04.2017.
 */
public class ConsoleEventLoger implements EventLogger {
    @Override
    public void logEvent(Event event) {
        System.out.println(event);
    }
}
