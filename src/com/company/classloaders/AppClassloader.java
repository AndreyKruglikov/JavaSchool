package com.company.classloaders;

public class AppClassloader extends ApiClassloader {

    public AppClassloader(ClassLoader parent, String pathToBin) {
        super(parent, pathToBin);
    }
}
