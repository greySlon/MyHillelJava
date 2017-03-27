package com.slon.zoo.interfaces;

/**
 * Created by Sergii on 27.03.2017.
 */
public interface Listener<T> {
    void handleEvent(Object sender, T eventArg);
}
