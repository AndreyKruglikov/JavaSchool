package com.company.classloaders;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ApiClassloader extends ClassLoader
{
    private Map classesHash = new HashMap();
    public final String[] classPath;

    public ApiClassloader(String[] classPath) {
        this.classPath = classPath;
    }

    protected synchronized Class loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class result = findClass(name);
        if (resolve)
            resolveClass(result);
        return result;
    }

    protected Class findClass(String name) throws ClassNotFoundException {
        Class result = (Class) classesHash.get(name);
        if (result != null) {
            return result;
        }
        File file = findFile(name.replace('.','/'),".class");
        if (file == null) {
            return findSystemClass(name);
        }
        try {
            byte[] classBytes = loadFileAsBytes(file);
            name = name.replaceAll("/", ".");
            result = defineClass(name, classBytes,0, classBytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException("Cannot load class "  + name + ": " + e);
        } catch (ClassFormatError e) {
            throw new ClassNotFoundException("Format of class file incorrect for class " + name + ": " + e);
        }
        classesHash.put(name,result);
        return result;
    }

    protected URL findResource(String name) {
        File file = findFile(name, "");
        if (file == null)
            return null;
        try {
            return file.toURL();
        } catch(java.net.MalformedURLException e) {
            return null;
        }
    }

    private File findFile(String name, String extension) {
        for (int k = 0; k < classPath.length; k++) {
            File file = new File((new File(classPath[k])).getPath() + File.separatorChar + name.substring(name.lastIndexOf("/") + 1, name.length()) + extension);
            if (file.exists())
                return file;
        }
        return null;
    }

    public static byte[] loadFileAsBytes(File file) throws IOException {
        byte[] result = new byte[(int) file.length()];
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            fileInputStream.read(result,0, result.length);
        }
        return result;
    }
}
