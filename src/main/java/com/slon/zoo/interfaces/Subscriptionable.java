package com.slon.zoo.interfaces;

/**
 * Created by Sergii on 27.03.2017.
 */
public interface Subscriptionable<T> {
    void subscribe(Listener<T> listener);
    void unsubscribe(Listener<T> listener);
}
