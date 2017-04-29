package com.slon.zoo.models;

import com.slon.zoo.interfaces.Listener;
import com.slon.zoo.events.WannaEatEvent;
import com.slon.zoo.springEvents.WannaEatEventListener;
import org.springframework.context.ApplicationListener;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by Sergii on 27.03.2017.
 */
public class FoodManager<T extends ApplicationListener> {
    private static int counter;


    private Map<Class<T>, T> map;
    private int id;
    private String name = this.getClass().getCanonicalName().replaceAll("(\\S+\\.)*", "");

    public void setMap(Map<Class<T>, T> map) throws Exception {
        this.map = map;
        for (Map.Entry<Class<T>, T> entry : map.entrySet()) {
            Method setter = entry.getValue().getClass().getDeclaredMethod("setOwner", String.class);
            setter.invoke(entry.getValue(), name);

            System.out.println(entry.getValue().toString()+"************");
        }
    }

    public FoodManager() {
        this.id = ++counter;
        this.name = id + "-" + this.getClass().getCanonicalName().replaceAll("(\\S+\\.)*", "");
        System.out.println(id + "-foodmanager created");
    }
}
