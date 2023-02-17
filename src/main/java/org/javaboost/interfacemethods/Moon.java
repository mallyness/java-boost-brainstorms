package org.javaboost.interfacemethods;

public interface Moon {

    static void shine() {
        System.out.println("Moon is shine");
    }

    private void up() {
        System.out.println("Moon is up");
    }

    private static void staticUp() {
        System.out.println("Moon is static up");
    }

    /*
     * We can use private static and non-static methods in default method.
     * */
    default void down() {
        System.out.println("Moon is down");
        System.out.print("Call private up() method in Moon:");
        up();
        System.out.print("Call private staticUp() method in Moon:");
        staticUp();
    }
}
