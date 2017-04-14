package com.slon.utils.Advices;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.util.StopWatch;

import java.text.MessageFormat;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Sergii on 09.04.2017.
 */
public class ProfilerAdvice implements MethodInterceptor {
    Map<String, Long> map = new TreeMap<>();

    public Map<String, Long> getMapMethodLasting() {
        return map;
    }

    String getClassName(Object obj) {
        return obj.getClass().getName().replaceAll("(\\S+\\.)*", "");
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Object result;
        long start = System.nanoTime();
        try {
            result = methodInvocation.proceed();
            return result;
        } finally {
            long x = (System.nanoTime() - start) / 1000;
            String info = MessageFormat.format("Class:{0}, Method:{1}",
                    getClassName(methodInvocation.getThis()),
                    methodInvocation.getMethod().getName());
            map.merge(info, x, (a, b) -> (a + b) / 2);
            System.out.println(MessageFormat.format("{0} {1} mcsec", info, x));
        }
    }
}
