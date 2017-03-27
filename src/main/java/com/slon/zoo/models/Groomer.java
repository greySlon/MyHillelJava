package com.slon.zoo.models;

import com.slon.zoo.interfaces.Listener;
import com.slon.zoo.events.FurCareEvent;

/**
 * Created by Sergii on 27.03.2017.
 */
public class Groomer {
    Listener<FurCareEvent> furCareEventListener = (Object sender, FurCareEvent eventArg) -> System.out.println("Groomer receive message from animal: " + eventArg.toString());

    public Listener<FurCareEvent> getFurCareEventListener() {
        return furCareEventListener;
    }
}
