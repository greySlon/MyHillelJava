package com.slon.utils;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;

/**
 * Created by Sergii on 10.04.2017.
 */
public class Utils {
    public static <T extends Advice> Object getProxy(Object target, T advice) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvice(advice);
        return proxyFactory.getProxy();

    }
    public static <T extends Advisor> Object getProxy(Object target, T advisor) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvisor(advisor);
        return proxyFactory.getProxy();
    }
}
