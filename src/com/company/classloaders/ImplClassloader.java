package com.company.classloaders;

public class ImplClassloader extends ApiClassloader {

    public ImplClassloader(ClassLoader parent, String pathToBin) {
        super(parent, pathToBin);
    }
}
