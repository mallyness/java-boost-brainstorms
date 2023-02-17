package org.javaboost.interfacemethods;

public interface Sun {

    static void shine() {
        System.out.println("Sun is shine");
    }

    private void up() {
        System.out.println("Sun is up");
    }

    default void down() {
        System.out.println("Sun is down");
        System.out.print("Call private up() method in Sun:");
        up();
    }
}
