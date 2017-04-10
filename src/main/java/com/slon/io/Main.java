package com.slon.io;

import com.slon.utils.Advices.ProfilerAdvice;
import com.slon.utils.pointcuts.MethodNamePoincut;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import java.text.MessageFormat;
import java.util.Map;

import static com.slon.utils.Utils.getProxy;

/**
 * Created by Sergii on 07.04.2017.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        ProfilerAdvice profilerAdvice = new ProfilerAdvice();
        Advisor profileAdvisor = new DefaultPointcutAdvisor(new MethodNamePoincut("write"), profilerAdvice);

        BufferedIO bufferedIO = (BufferedIO) getProxy(new BufferedIO(), profileAdvisor);
        UnbufferedIO unbufferedIO = (UnbufferedIO) getProxy(new UnbufferedIO(), profileAdvisor);
        NIO nio = ((NIO) getProxy(new NIO(), profileAdvisor));


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
        for (Map.Entry<String, Long> stringLongEntry : profilerAdvice.getMapMethodLasting().entrySet()) {
            System.out.println(MessageFormat.format("{0} {1} Mb/sec",
                    stringLongEntry.getKey(),
                    1000d / stringLongEntry.getValue()));
        }
    }
}
