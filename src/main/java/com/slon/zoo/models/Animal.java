package com.slon.zoo.models;

import com.slon.zoo.springEvents.DiseaseEvent;
import com.slon.zoo.springEvents.WannaEatEvent;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by Sergii on 27.03.2017.
 */
public abstract class Animal implements ApplicationContextAware {
    static protected int counter;

    protected int id;
    protected ApplicationContext ctx;

    protected Animal() {
        this.id = ++counter;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx = applicationContext;
    }

    protected void wannaEat() {
        ctx.publishEvent(new WannaEatEvent(this, String.format("Animal want to eat", id)));
    }

    public void feelIll() {
        ctx.publishEvent(new DiseaseEvent(this, String.format("Animal feels ill", id)));
    }

    public abstract void eventGenerator();
}
