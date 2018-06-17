package com.company;

import com.company.classloaders.ApiClassloader;
import com.company.classloaders.AppClassloader;
import com.company.classloaders.ImplClassloader;
import com.company.classloaders.app.App;
import com.company.classloaders.impl.CalculatorImpl;

import java.io.IOException;

public class Main {

    private static final String PATH_TO_BIN_IMPL = ".\\.\\.\\out\\production\\JavaSchool\\com\\company\\classloaders\\impl\\";
    private static final String PATH_TO_BIN_APP = ".\\.\\.\\out\\production\\JavaSchool\\com\\company\\classloaders\\app\\";
    private static final String PATH_TO_BIN_API = ".\\.\\.\\out\\production\\JavaSchool\\com\\company\\classloaders\\api\\";

    public static void main(String[] args) throws ClassNotFoundException, IOException, IllegalAccessException, InstantiationException {

        ApiClassloader apiClassloader = new ApiClassloader(new String[] {PATH_TO_BIN_API});
        Class clazzCalculator = Class.forName("com.company.classloaders.api.Calculator",true, apiClassloader);

        ImplClassloader implClassloader = new ImplClassloader(new String[] {PATH_TO_BIN_IMPL});
        Class clazzCalculatorImpl = Class.forName("com.company.classloaders.impl.CalculatorImpl",true, implClassloader);
//        CalculatorImpl calculatorImpl = (CalculatorImpl) clazzCalculatorImpl.newInstance();
        System.out.println(clazzCalculatorImpl.newInstance().getClass().getClassLoader());

        AppClassloader appClassloader = new AppClassloader(new String[] {PATH_TO_BIN_APP});
        Class clazzApp = Class.forName("com.company.classloaders.app.App",true, appClassloader);
//        App app = (App) clazzApp.newInstance();
        System.out.println(clazzApp.newInstance().getClass().getClassLoader());

        AppClassloader appClassloader1 = new AppClassloader(new String[] {PATH_TO_BIN_APP});
        Class clazzApp1 = Class.forName("com.company.classloaders.app.MyApp",true, appClassloader);
//        App app = (App) clazzApp.newInstance();
        System.out.println(clazzApp1.newInstance().getClass().getClassLoader());
    }
}