package com.slon.utils.pointcuts;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

/**
 * Created by Sergii on 10.04.2017.
 */
public class MethodNamePoincut extends StaticMethodMatcherPointcut {
    private String methodName;
    private Class cls;

    public MethodNamePoincut(String methodName, Class cls) {
        this.methodName = methodName;
        this.cls = cls;
    }

    public MethodNamePoincut(String methodName) {
        this.methodName = methodName;
    }

    public MethodNamePoincut() {

    }

    @Override
    public ClassFilter getClassFilter() {
        return new ClassFilter() {
            @Override
            public boolean matches(Class<?> aClass) {
                return cls == null ? true : cls == aClass;
            }
        };
    }

    @Override
    public boolean matches(Method method, Class<?> aClass) {
        return methodName == null ? true : methodName.equals(method.getName());
    }
}
