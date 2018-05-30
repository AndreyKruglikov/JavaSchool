package com.company.classloaders;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class ApiClassloader extends ClassLoader {

    private String pathToBin;

    public ApiClassloader(ClassLoader parent, String pathToBin) {
        super(parent);
        this.pathToBin = pathToBin;
    }

    public Class<?> loadClazz(String className) throws IOException {
        List<String> modules = Arrays.asList(new File(pathToBin).list());
        String[] s = className.split("\\.");
        String filename = s[s.length - 1] + ".class";
        if (!modules.contains(filename))
            throw new FileNotFoundException();
        byte[] bytes = Files.readAllBytes(Paths.get(pathToBin + filename));
        Class<?> clazz = defineClass(className, bytes, 0, bytes.length);
        return clazz;
    }
}
