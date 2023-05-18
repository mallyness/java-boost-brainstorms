package org.javaboost.reflection;

import java.awt.color.ColorSpace;
import java.lang.reflect.Field;
import java.util.Arrays;

public class EnumFieldDemo {

    public static void main(String[] args) throws Exception {
//        checkEnumAccess();
        checkEnumFieldAccess();
    }

    private static void checkEnumFieldAccess() throws ClassNotFoundException, IllegalAccessException, NoSuchFieldException {
        ClassLoader classLoader = ReflectionDemo.class.getClassLoader();
        Class<?> aClass = classLoader.loadClass(Color.class.getName());
        Field[] fields = aClass.getFields();
        printEnumFields();
        for (Field field : fields) {
            field.setAccessible(true);
            changedFieldInEachEnumConstant(aClass, field);
        }
        printEnumFields();
    }

    private static void printEnumFields() {
        for (Color value : Color.values()) {
            System.out.printf("%s = %s%n", value, value.getEnumField());
        }
    }

    private static void changedFieldInEachEnumConstant(Class<?> aClass, Field field) throws IllegalAccessException, NoSuchFieldException {
        Color colorEnum = (Color) field.get(aClass); // RED, GREEN, BLUE
        Field fieldToChange = colorEnum.getClass().getDeclaredField("enumField");
        fieldToChange.setAccessible(true);
        fieldToChange.set(colorEnum, "changedColor");
    }

    private static void checkEnumAccess() throws ClassNotFoundException, IllegalAccessException {
        ClassLoader classLoader = ReflectionDemo.class.getClassLoader();
        Class<?> aClass = classLoader.loadClass("org.javaboost.reflection.Color");
        Field[] fields = aClass.getFields();
        System.out.println(Arrays.asList(fields));
        for (Field field : fields) {
            field.setAccessible(true);
            field.set(aClass, ColorSpace.CS_GRAY); // IllegalAccessException
        }
    }
}

enum Color {
    RED("superRed"), GREEN("superGreen"), BLUE("superBlue");

    Color(String enumField) {
        this.enumField = enumField;
    }

    private final String enumField;

    public String getEnumField() {
        return enumField;
    }
}
