package com.slon.zoo.models;

import com.slon.zoo.interfaces.Listener;
import com.slon.zoo.events.DiseaseEvent;
import com.slon.zoo.events.WannaEatEvent;

/**
 * Created by Sergii on 27.03.2017.
 */
public class Doctor {
    Listener<WannaEatEvent> eatEventListener = (Object sender, WannaEatEvent eventArg) -> System.out.println("Doctor receive message from animal: " + eventArg.toString());
    Listener<DiseaseEvent> diseaseEventListener = (Object sender, DiseaseEvent eventArg) -> System.out.println("Doctor receive message from animal: " + eventArg.toString());

    public Listener<WannaEatEvent> getEatEventListener() {
        return eatEventListener;
    }

    public Listener<DiseaseEvent> getDiseaseEventListener() {
        return diseaseEventListener;
    }
}
