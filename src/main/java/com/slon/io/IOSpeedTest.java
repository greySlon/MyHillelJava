package com.slon.io;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * Created by Sergii on 07.04.2017.
 */
public class IOSpeedTest {
    private TimeUnit timeUnit;
    private double itemsToMeasure = 1;
    private long deltaTime;
    private Object result;

    public IOSpeedTest(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    public IOSpeedTest() {
        this(TimeUnit.MILLISECONDS);
    }

    public void setItemsToMeasure(int itemsToMeasure) {
        this.itemsToMeasure = itemsToMeasure;
    }

    public Object getResult() {
        return result;
    }

    public long timeTest(Object target, String methodName, Object... args) throws Exception {
        Class[] paramTypes = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            paramTypes[i] = args[i].getClass();
        }
        Method method = target.getClass().getMethod(methodName, paramTypes);

        long startTime = System.nanoTime();
        result = method.invoke(target, args);
        long stopTime = System.nanoTime();
        deltaTime = stopTime - startTime;
        return timeUnit.convert(deltaTime, TimeUnit.NANOSECONDS);
    }

    public double processPerSecond() {
        return itemsToMeasure * 1000_000_000 / deltaTime;
    }
}
