package com.slon.zoo.springEvents;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * Created by Sergii on 04.04.2017.
 */
public class WannaEatEventListener implements ApplicationListener<WannaEatEvent> {
    private String owner;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public void onApplicationEvent(WannaEatEvent wannaEatEvent) {
        System.out.println(owner + ": " + wannaEatEvent.toString());
    }
}
