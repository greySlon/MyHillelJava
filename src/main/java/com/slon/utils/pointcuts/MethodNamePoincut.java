package com.slon.utils.pointcuts;

import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

/**
 * Created by Sergii on 10.04.2017.
 */
public class MethodNamePoincut extends StaticMethodMatcherPointcut {
    private String methodName;

    public MethodNamePoincut(String methodName) {
        this.methodName = methodName;
    }

    public MethodNamePoincut() {

    }

    @Override
    public boolean matches(Method method, Class<?> aClass) {
        return methodName == null ? true : methodName.equals(method.getName());
    }
}
