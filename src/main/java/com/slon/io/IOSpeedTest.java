package com.slon.io;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

/**
 * Created by Sergii on 07.04.2017.
 */
public class IOSpeedTest {
    private long startTime;
    private long stopTime;

    public long timeTest(Copieble copieble, TimeUnit timeUnit, String... args) throws Exception {
        startTime = System.nanoTime();
        copieble.copyTo(args[0], args[1]);
        stopTime = System.nanoTime();
        return timeUnit.convert(stopTime - startTime, TimeUnit.NANOSECONDS);
    }

    public String getDateFromConsole() throws Exception{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }
    public static void main(String[] args) throws Exception {
        IOSpeedTest speedTest = new IOSpeedTest();
        UnbufferdeIO unbufferdeIO = new UnbufferdeIO();
        BufferedIO bufferedIO = new BufferedIO();

        String sourceFileName=speedTest.getDateFromConsole();
        String destinationFileName=speedTest.getDateFromConsole();

        System.out.println(speedTest.timeTest(((Copieble) unbufferdeIO), TimeUnit.MILLISECONDS, sourceFileName, destinationFileName));
        System.out.println(speedTest.timeTest(((Copieble) bufferedIO), TimeUnit.MILLISECONDS, sourceFileName, destinationFileName));
    }
}
