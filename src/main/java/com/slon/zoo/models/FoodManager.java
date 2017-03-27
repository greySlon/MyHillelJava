package com.slon.zoo.models;

import com.slon.zoo.interfaces.Listener;
import com.slon.zoo.events.WannaEatEvent;

/**
 * Created by Sergii on 27.03.2017.
 */
public class FoodManager {
    private static int counter;
    private int id;

    public FoodManager() {
        this.id = ++counter;
    }

    Listener<WannaEatEvent> eatEventListener = (Object sender, WannaEatEvent eventArg) ->
            System.out.println("FoodManager #" + id + " receive message from animal: " + eventArg.toString());

    public Listener<WannaEatEvent> getEatEventListener() {
        return eatEventListener;
    }
}
