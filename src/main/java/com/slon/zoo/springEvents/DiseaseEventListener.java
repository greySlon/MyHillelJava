package com.slon.zoo.springEvents;

import com.slon.zoo.events.*;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;

/**
 * Created by Sergii on 04.04.2017.
 */
public class DiseaseEventListener implements ApplicationListener<DiseaseEvent> {
    private String owner;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public void onApplicationEvent(DiseaseEvent diseaseEvent) {
        System.out.println(owner + ": " + diseaseEvent.toString());
    }
}
