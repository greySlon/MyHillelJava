package com.slon.zoo.interfaces;

/**
 * Created by Sergii on 27.03.2017.
 */
public interface Notifier<T> extends Subscriptionable<T> {
    void raiseEvent(Object sender, T eventArg);
}
