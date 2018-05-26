package com.company.reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class GetterCounterImpl implements GetterCounter {

    private static final Map<Class<?>, Integer> cache = new HashMap<>();

    @Override
    public int calcGetterCount(Class<?> clazz) {
        int count = 0;
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Getter.class))
                count++;
        }
        return count;
    }

    public static GetterCounter makeCached(GetterCounter delegate) {
        return (GetterCounter) Proxy.newProxyInstance(GetterCounterImpl.class.getClassLoader(), GetterCounterImpl.class.getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                long start = System.nanoTime();
                if (cache.containsKey(delegate.getClass())) {
                    System.out.print("Value from cache: ");
                    return cache.get(delegate.getClass());
                } else {
                    Integer count = (Integer) method.invoke(delegate, args);
                    cache.put(delegate.getClass(), count);
                    return count;
                }
            }
        });
    }
}
