package com.slon.zoo.models;

import com.slon.zoo.implement.NotifierImpl;
import com.slon.zoo.interfaces.Listener;
import com.slon.zoo.interfaces.Notifier;
import com.slon.zoo.events.FurCareEvent;

import java.util.Random;

/**
 * Created by Sergii on 27.03.2017.
 */
public class DomesticAnimal extends Animal {
    private Notifier<FurCareEvent> furCareNotifier = new NotifierImpl<>();

    public void subscribeOnFurCareEvent(Listener<FurCareEvent> listener) {
        furCareNotifier.subscribe(listener);
    }

    public void unsubscribeFromFurCareEvent(Listener<FurCareEvent> listener) {
        furCareNotifier.unsubscribe(listener);
    }

    protected void needGroom() {
        furCareNotifier.raiseEvent(this, new FurCareEvent(id, String.format("Animal want to be groomed", id)));
    }

    @Override
    public void eventGenerator() {
        Random rand = new Random();
        int i = rand.nextInt(3);
        switch (i) {
            case 0:
                this.feelIll();
                break;
            case 1:
                this.needGroom();
                break;
            default:
                this.wannaEat();
        }
    }
}
