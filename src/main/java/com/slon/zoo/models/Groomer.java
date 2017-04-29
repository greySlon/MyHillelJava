package com.slon.zoo.models;

import com.slon.zoo.interfaces.Listener;
import com.slon.zoo.events.FurCareEvent;
import com.slon.zoo.springEvents.FurCareEventListener;
import org.springframework.context.ApplicationListener;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by Sergii on 27.03.2017.
 */
public class Groomer<T extends ApplicationListener> {
    private String name = this.getClass().getCanonicalName().replaceAll("(\\S+\\.)*", "");
    private Map<Class<T>, T> map;

    public void setMap(Map<Class<T>, T> map) throws Exception {
        this.map = map;
        for (Map.Entry<Class<T>, T> entry : map.entrySet()) {
            Method setter = entry.getValue().getClass().getDeclaredMethod("setOwner", String.class);
            setter.invoke(entry.getValue(), name);
        }
    }

    public Groomer() {
        System.out.println("groomer created");
    }
}
