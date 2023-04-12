package org.javaboost.classloader.singleton;

import org.javaboost.classloader.custom.CustomClassloader;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SingletonClassloadingDemo {

    private static final String ENUM_CLASS_NAME = "org.javaboost.classloader.singleton.EnumSingleton";
    private static final String SIMPLE_CLASS_NAME = "org.javaboost.classloader.singleton.SimpleSingleton";
    private static final String FIELD_NAME = "INSTANCE";

    public static void main(String[] args) throws Exception {

        Object enumInstance1 = loadEnumClass();
        Object enumInstance2 = loadEnumClass();
        System.out.println(enumInstance1 == enumInstance2); // false, so we can create 2 instance of enum singleton

        Object simpleSingletonInstance1 = loadClass();
        Object simpleSingletonInstance2 = loadClass();
        System.out.println(simpleSingletonInstance1 == simpleSingletonInstance2); // false,
        // so we can create 2 instance of simple singleton class
    }

    private static Object loadEnumClass() throws ClassNotFoundException, IllegalAccessException, NoSuchFieldException {
        Class<?> loadedClass = getLoadedClass(ENUM_CLASS_NAME);
        Object instance = loadedClass.getField(FIELD_NAME).get(null);
        System.out.printf("%s loaded %s object.%n", instance.getClass().getClassLoader(), instance);
        return instance;
    }

    private static Object loadClass() throws ClassNotFoundException, IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        Class<?> loadedClass = getLoadedClass(SIMPLE_CLASS_NAME);
        Constructor<?> constructor = loadedClass.getDeclaredConstructor();
        constructor.setAccessible(true);
        Object instance = constructor.newInstance();
        System.out.printf("%s loaded %s object.%n", instance.getClass().getClassLoader(), instance);
        return instance;
    }

    private static Class<?> getLoadedClass(String simpleClassName) throws ClassNotFoundException {
        CustomClassloader classloader = new CustomClassloader();
        return classloader.loadClass(simpleClassName);
    }
}
