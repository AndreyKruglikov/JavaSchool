package com.company;

import com.company.classloaders.ApiClassloader;
import com.company.classloaders.AppClassloader;
import com.company.classloaders.ImplClassloader;
import com.company.classloaders.api.Calculator;
import com.company.classloaders.app.MyApp;
import com.company.classloaders.impl.CalculatorImpl;

import java.io.File;
import java.io.IOException;

public class Main {

    private static final String PATH_TO_BIN_IMPL = ".\\.\\.\\out\\production\\JavaSchool\\com\\company\\classloaders\\impl\\";
    private static final String PATH_TO_BIN_APP = ".\\.\\.\\out\\production\\JavaSchool\\com\\company\\classloaders\\app\\";
    private static final String PATH_TO_BIN_API = ".\\.\\.\\out\\production\\JavaSchool\\com\\company\\classloaders\\api\\";

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        ImplClassloader implClassloader = new ImplClassloader(ClassLoader.getSystemClassLoader(), PATH_TO_BIN_IMPL);
        AppClassloader appClassloader = new AppClassloader(ClassLoader.getSystemClassLoader(), PATH_TO_BIN_APP);
        ApiClassloader apiClassloader = new ApiClassloader(ClassLoader.getSystemClassLoader(), PATH_TO_BIN_API);

        apiClassloader.loadClazz("com.company.classloaders.api.Calculator");
        Calculator calculator = new CalculatorImpl();
        System.out.println(calculator.getClass().getClassLoader());

//        implClassloader.loadClazz("com.company.classloaders.impl.CalculatorImpl");
//        CalculatorImpl calculatorImpl = new CalculatorImpl();
//        System.out.println(CalculatorImpl.class.getClassLoader());

//        appClassloader.loadClazz("com.company.classloaders.app.MyApp");
//        MyApp myApp = new MyApp();
//        System.out.println(MyApp.class.getClassLoader());
    }
}