package com.slon.zoo.implement;

import com.slon.zoo.interfaces.Listener;
import com.slon.zoo.interfaces.Notifier;

import java.util.WeakHashMap;

/**
 * Created by Sergii on 27.03.2017.
 */
public class NotifierImpl<T> implements Notifier<T> {
WeakHashMap<Listener<T>, Object> listenersMap=new WeakHashMap<>();

    @Override
    public void subscribe(Listener<T> listener) {
        if (listener != null) {
            listenersMap.put(listener, null);
        }
    }

    @Override
    public void unsubscribe(Listener<T> listener) {
        listenersMap.remove(listener);
    }

    @Override
    public void raiseEvent(Object sender, T eventArg) {
        for (Listener<T> listener : listenersMap.keySet()) {
            listener.handleEvent(sender, eventArg);
        }
    }
}
