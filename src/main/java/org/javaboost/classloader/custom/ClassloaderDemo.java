package org.javaboost.classloader.custom;

public class ClassloaderDemo {

    private static final String CLASS_NAME = "com.example.core.classloader.SimpleClass";

    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println("ClassloaderDemo class is loaded by: " + ClassloaderDemo.class.getClassLoader());
        System.out.println("Object class is loaded by: " + Object.class.getClassLoader());

        CustomClassloader classloader = new CustomClassloader();
        Class<?> aClass = classloader.loadClass(CLASS_NAME);
        System.out.println(".loadClass() method: " + aClass.getClassLoader().getName());
    }
}
