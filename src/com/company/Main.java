package com.company;

import com.company.reflection.GetterCounter;
import com.company.reflection.GetterCounterImpl;
import com.company.reflection.TestReflection;

import java.util.Date;

public class Main {

    public static void main(String[] args) {
        GetterCounter getterCounter = new GetterCounterImpl();
//        System.out.println(getterCounter.calcGetterCount(TestReflection.class));

        GetterCounter getterCounterProxy = GetterCounterImpl.makeCached(getterCounter);
        System.out.println(getterCounterProxy.calcGetterCount(TestReflection.class));
        System.out.println(getterCounterProxy.calcGetterCount(TestReflection.class));
    }
}
