package com.slon.io;

import com.slon.io.Advices.Profiler;
import org.aopalliance.aop.Advice;
import org.springframework.aop.framework.ProxyFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by Sergii on 07.04.2017.
 */
public class Main {

    public static <T extends Advice> Object getProxy(Object target, T advice) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvice(advice);
        return proxyFactory.getProxy();

    }

    public static void main(String[] args) throws Exception {
        Profiler profiler = new Profiler();

        BufferedIO bufferedIO = (BufferedIO) getProxy(new BufferedIO(), profiler);
        UnbufferedIO unbufferedIO = (UnbufferedIO) getProxy(new UnbufferedIO(), profiler);
        NIO nio = ((NIO) getProxy(new NIO(), profiler));


        int circle = 10;
        final int TEST_SIZE = 1024 * 1024;
        byte[] bytes = new byte[TEST_SIZE];


        for (int i = 0; i < circle; i++) {
//            bufferedIO.copyTo("d:/троелсен.djvu", "d:/троелсен-new.djvu");
//            nio.copyTo("d:/троелсен.djvu", "d:/троелсен-new.djvu");
//            unbufferedIO.copyTo("d:/троелсен.djvu", "d:/троелсен-new.djvu");

            bufferedIO.write(bytes, "d:/write.test");
            unbufferedIO.write(bytes, "d:/write.test");
            nio.write(bytes, "d:/write.test");
            bufferedIO.read("d:/write.test");
            unbufferedIO.read("d:/write.test");
            nio.read("d:/write.test");
        }

        System.out.println("*** Circle count: " + circle + "***");
        for (Map.Entry<String, Long> stringLongEntry : profiler.getMapMethodLasting().entrySet()) {
            System.out.println(MessageFormat.format("{0} {1} Mb/sec",
                    stringLongEntry.getKey(),
                    1000d / stringLongEntry.getValue()));
        }
    }
}
