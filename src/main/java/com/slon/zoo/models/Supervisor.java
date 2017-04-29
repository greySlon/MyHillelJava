package com.slon.zoo.models;

import com.slon.zoo.interfaces.Listener;
import com.slon.zoo.events.DiseaseEvent;
import com.slon.zoo.events.FurCareEvent;
import com.slon.zoo.events.WannaEatEvent;
import com.slon.zoo.springEvents.DiseaseEventListener;
import com.slon.zoo.springEvents.FurCareEventListener;
import com.slon.zoo.springEvents.WannaEatEventListener;
import org.springframework.context.ApplicationListener;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by Sergii on 27.03.2017.
 */
public class Supervisor<T extends ApplicationListener> {
    private Map<Class<T>, T> map;
    private String name = this.getClass().getCanonicalName().replaceAll("(\\S+\\.)*", "");

    public void setMap(Map<Class<T>, T> map) throws Exception {
        this.map = map;
        for (Map.Entry<Class<T>, T> entry : map.entrySet()) {
            Method setter = entry.getValue().getClass().getDeclaredMethod("setOwner", String.class);
            setter.invoke(entry.getValue(), name);
        }
    }

    public Supervisor() {
        System.out.println("supervisor created");
    }
}
