package com.slon.zoo.models;

import com.slon.zoo.interfaces.Listener;
import com.slon.zoo.events.DiseaseEvent;
import com.slon.zoo.events.FurCareEvent;
import com.slon.zoo.events.WannaEatEvent;

/**
 * Created by Sergii on 27.03.2017.
 */
public class Supervisor {
    Listener<WannaEatEvent> eatEventListener = (Object sender, WannaEatEvent eventArg) -> System.out.println("Supervisor receive message from animal: " + eventArg.toString());
    Listener<DiseaseEvent> diseaseEventListener = (Object sender, DiseaseEvent eventArg) -> System.out.println("Supervisor receive message from animal: " + eventArg.toString());
    Listener<FurCareEvent> furCareEventListener = (Object sender, FurCareEvent eventArg) -> System.out.println("Supervisor receive message from animal: " + eventArg.toString());

    public Listener<FurCareEvent> getFurCareEventListener() {
        return furCareEventListener;
    }

    public Listener<WannaEatEvent> getEatEventListener() {
        return eatEventListener;
    }

    public Listener<DiseaseEvent> getDiseaseEventListener() {
        return diseaseEventListener;
    }
}
