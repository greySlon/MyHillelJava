package com.slon.zoo.springEvents;

import org.springframework.context.ApplicationListener;

/**
 * Created by Sergii on 04.04.2017.
 */
public class FurCareEventListener implements ApplicationListener<FurCareEvent> {
    private String owner;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public void onApplicationEvent(FurCareEvent furCareEvent) {
        System.out.println(owner + ": " + furCareEvent.toString());
    }
}
