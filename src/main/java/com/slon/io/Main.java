package com.slon.io;

import com.slon.lesson3.students.Student;
import com.slon.zoo.models.Animal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Sergii on 07.04.2017.
 */
public class Main {
    public static String getDateFromConsole() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }

    public static double getAverage(List<Double> list) {
        double x = 0;
        for (Double item : list) {
            x += item;
        }
        return x;
    }

    public static void main(String[] args) throws Exception {
        int circle = 10;

        IOSpeedTest speedTest = new IOSpeedTest(TimeUnit.MILLISECONDS);
        byte[] bytes = new byte[1024 * 1024];
        speedTest.setItemsToMeasure(bytes.length / (1024 * 1024));

        UnbufferdeIO unbufferdeIO = new UnbufferdeIO();
        BufferedIO bufferedIO = new BufferedIO();
        NIO mappedIO=new NIO();


        List<Double> listBufferWrite = new ArrayList<>();
        List<Double> listUnbufferWrite = new ArrayList<>();
        List<Double> listBufferRead = new ArrayList<>();
        List<Double> listUnbufferRead = new ArrayList<>();
        List<Double> listMappedIORead = new ArrayList<>();
        List<Double> listMappedIOWrite = new ArrayList<>();


        for (int i = 0; i < circle; i++) {
            System.out.println(speedTest.timeTest(bufferedIO, "copyTo","d:/троелсен.djvu", "d:/троелсен-new.djvu" ));
            System.out.println(speedTest.timeTest(mappedIO, "copyTo","d:/троелсен.djvu", "d:/троелсен-new.djvu" ));


            System.out.println("***BUFFERED WRITE***");
            System.out.println(speedTest.timeTest(bufferedIO, "write", bytes, "d:/write.test"));
            System.out.println(speedTest.processPerSecond());
            listBufferWrite.add(speedTest.processPerSecond());

            System.out.println("***UNBUFFERED WRITE***");
            System.out.println(speedTest.timeTest(unbufferdeIO, "write", bytes, "d:/write.test"));
            System.out.println(speedTest.processPerSecond());
            listUnbufferWrite.add(speedTest.processPerSecond());

            System.out.println("***NIO WRITE***");
            System.out.println(speedTest.timeTest(mappedIO, "write", bytes, "d:/write.test"));
            System.out.println(speedTest.processPerSecond());
            listMappedIOWrite.add(speedTest.processPerSecond());


            System.out.println("***BUFFERED READ***");
            System.out.println(speedTest.timeTest(bufferedIO, "read", "d:/write.test"));
            System.out.println(speedTest.processPerSecond());
            listBufferRead.add(speedTest.processPerSecond());

            System.out.println("***UNBUFFERED READ***");
            System.out.println(speedTest.timeTest(unbufferdeIO, "read", "d:/write.test"));
            System.out.println(speedTest.processPerSecond());
            listUnbufferRead.add(speedTest.processPerSecond());

            System.out.println("***NIO READ***");
            System.out.println(speedTest.timeTest(mappedIO, "read", "d:/write.test"));
            System.out.println(speedTest.processPerSecond());
            listMappedIORead.add(speedTest.processPerSecond());
        }

        System.out.println("*** Circle count: " + circle + "***");
        System.out.println("***AVERAGE BUFFERED WRITE***");
        System.out.println(String.format("%f Mb/sec", getAverage(listBufferWrite) / listBufferWrite.size()));

        System.out.println("***AVERAGE UNBUFFERED WRITE***");
        System.out.println(String.format("%f Mb/sec", getAverage(listUnbufferWrite) / listUnbufferWrite.size()));

        System.out.println("***AVERAGE NIO WRITE***");
        System.out.println(String.format("%f Mb/sec", getAverage(listMappedIOWrite) / listMappedIORead.size()));

        System.out.println("***AVERAGE BUFFERED READ***");
        System.out.println(String.format("%f Mb/sec", getAverage(listBufferRead) / listBufferRead.size()));

        System.out.println("***AVERAGE UNBUFFERED READ***");
        System.out.println(String.format("%f Mb/sec", getAverage(listUnbufferRead) / listUnbufferRead.size()));

        System.out.println("***AVERAGE NIO READ***");
        System.out.println(String.format("%f Mb/sec", getAverage(listMappedIORead) / listMappedIORead.size()));
    }
}
