package org.javaboost.classloaders;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/*
 * Load two different versions of the library and show how you can use different versions of the library.
 */
public class ClassloadersDemo {

    private static final String FIRST_LIB_URL = "file:/C:\\Users\\Kseniya_Astapava\\all\\java\\java-boost-brainstorms\\brainstorms\\src\\main\\java\\org\\javaboost\\classloaders\\commons-lang3-3.12.0.jar";
    private static final String SECOND_LIB_URL = "file:/C:\\Users\\Kseniya_Astapava\\all\\java\\java-boost-brainstorms\\brainstorms\\src\\main\\java\\org\\javaboost\\classloaders\\commons-lang3-3.0.jar";
    private static final String CLASS_NAME = "org.apache.commons.lang3.text.StrBuilder";
    private static final String METHOD_NAME = "length";

    public static void main(String[] args) throws Exception {
        URL firstLib = new URL(FIRST_LIB_URL);
        URL secondLib = new URL(SECOND_LIB_URL);

        try (URLClassLoader urlClassLoader1 = new URLClassLoader(new URL[]{firstLib});
             URLClassLoader urlClassLoader2 = new URLClassLoader(new URL[]{secondLib})) {

            Object strBuilder1 = processClass(urlClassLoader1);
            Object strBuilder2 = processClass(urlClassLoader2);

            System.out.println(strBuilder1.getClass() == strBuilder2.getClass()); //false
        }
    }

    private static Object processClass(URLClassLoader classLoader) throws ClassNotFoundException, InstantiationException, IllegalAccessException, java.lang.reflect.InvocationTargetException, NoSuchMethodException {
        Class<?> strBuilderClass = classLoader.loadClass(CLASS_NAME);
        Object strBuilder = strBuilderClass.getDeclaredConstructor().newInstance();
        Method lengthMethod = strBuilderClass.getDeclaredMethod(METHOD_NAME);
        System.out.println(lengthMethod.invoke(strBuilder));
        System.out.println(strBuilder.getClass().getClassLoader());
        return strBuilder;
    }
}
