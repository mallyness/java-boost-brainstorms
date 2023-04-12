package org.javaboost.classloader.custom;

import java.io.FileInputStream;
import java.io.IOException;

public class CustomClassloader extends ClassLoader {

    private static final String FILE_FOLDER = "C:\\Users\\Kseniya_Astapava\\Downloads\\test\\";
    private static final String CLASS_EXTENSION = ".class";

    public CustomClassloader() {
        super("Custom", CustomClassloader.getSystemClassLoader());
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return super.loadClass(name);
    }

    @Override
    protected Class<?> findClass(String name)  {
        String escapedName = name.replace(".", "\\");
        String classLocation = FILE_FOLDER + escapedName + CLASS_EXTENSION;
        try (FileInputStream inputStream = new FileInputStream(classLocation)) {
            byte[] bytes = inputStream.readAllBytes();
            return defineClass(name, bytes, 0, bytes.length);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
