package org.javaboost.reflection;

import java.lang.reflect.Field;

public class ReflectionDemo {

    public static void main(String[] args) throws Exception {
        checkRecordsAccess();
        checkFinalField();
        checkStaticField();
        checkConstant();
    }

    private static void checkRecordsAccess() throws NoSuchFieldException, IllegalAccessException {
        MyRecord myRecord = new MyRecord("sun");
        Field field = myRecord.getClass().getDeclaredField("name");
        field.setAccessible(true);
        System.out.println(field.get(myRecord));
        field.set(myRecord, "moon"); // IllegalAccessException: Can not set final java.lang.String field org.javaboost.reflection.MyRecord.name to java.lang.String
    }

    private static void checkFinalField() throws NoSuchFieldException, IllegalAccessException {
        FinalClass finalClass = new FinalClass();
        Field field = finalClass.getClass().getDeclaredField("finalField");
        field.setAccessible(true);
        field.set(finalClass, "changed_final");
        System.out.println("field.get = " + field.get(finalClass));
        System.out.println("getter = " + finalClass.getFinalField());
    }

    private static void checkStaticField() throws NoSuchFieldException, IllegalAccessException {
        FinalClass finalClass = new FinalClass();
        Field field = finalClass.getClass().getDeclaredField("staticField");
        field.setAccessible(true);
        System.out.println("Before changes: " + field.get(finalClass));
        field.set(finalClass, "changed_static");
        System.out.println("After changes: " + field.get(finalClass));
    }

    private static void checkConstant() throws NoSuchFieldException, IllegalAccessException {
        FinalClass finalClass = new FinalClass();
        Field field = finalClass.getClass().getDeclaredField("CONSTANT");
        field.setAccessible(true);
        System.out.println("Before changes: " + field.get(finalClass));
        field.set(finalClass, "changed_constant"); // IllegalAccessException
    }
}

final class FinalClass {
    private final String finalField = "super_final";
    private static String staticField = "super_static";
    private final static String CONSTANT = "super_constant";

    public String getFinalField() {
        return finalField;
    }

    public static String getStaticField() {
        return staticField;
    }

    @Override
    public String toString() {
        return "FinalClass{" +
                "finalField='" + finalField + '\'' +
                '}';
    }
}

record MyRecord(String name) {
}
