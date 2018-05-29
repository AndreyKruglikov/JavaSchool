package com.company.classloaders;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ApiClassloader extends ClassLoader {

    private String pathToBin;

    public ApiClassloader(ClassLoader parent, String pathToBin) {
        super(parent);
        this.pathToBin = pathToBin;
    }

    @Override
    public Class<?> findClass(String className) throws ClassNotFoundException {
        try {
            byte b[] = Files.readAllBytes(Paths.get(pathToBin + className + ".class"));
            return defineClass(className, b, 0, b.length);
        } catch (FileNotFoundException ex) {
            return super.findClass(className);
        } catch (IOException ex) {
            return super.findClass(className);
        }
    }
}
