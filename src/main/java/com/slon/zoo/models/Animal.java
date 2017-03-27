package com.slon.zoo.models;

import com.slon.zoo.implement.NotifierImpl;
import com.slon.zoo.interfaces.Listener;
import com.slon.zoo.interfaces.Notifier;
import com.slon.zoo.events.DiseaseEvent;
import com.slon.zoo.events.WannaEatEvent;

/**
 * Created by Sergii on 27.03.2017.
 */
public abstract class Animal {
    static protected int counter;

    protected int id;
    protected Notifier<DiseaseEvent> diseaseNotifier = new NotifierImpl<DiseaseEvent>();
    protected Notifier<WannaEatEvent> wannaEatNotifier = new NotifierImpl<WannaEatEvent>();

    protected Animal() {
        this.id = ++counter;
    }

    public void subscribeOnDiseaseEvent(Listener<DiseaseEvent> listener) {
        diseaseNotifier.subscribe(listener);
    }

    public void unsubscribeFromDiseaseEvent(Listener<DiseaseEvent> listener) {
        diseaseNotifier.unsubscribe(listener);
    }

    public void subscribeOnWannaEatEvent(Listener<WannaEatEvent> listener) {
        wannaEatNotifier.subscribe(listener);
    }

    public void unsubscribeFromWannaEatEvent(Listener<WannaEatEvent> listener) {
        wannaEatNotifier.unsubscribe(listener);
    }

    protected void feelIll() {
        diseaseNotifier.raiseEvent(this, new DiseaseEvent(id, String.format("Animal feels ill", id)));
    }

    protected void wannaEat() {
        wannaEatNotifier.raiseEvent(this, new WannaEatEvent(id, String.format("Animal want to eat", id)));
    }

    public abstract void eventGenerator();
}
