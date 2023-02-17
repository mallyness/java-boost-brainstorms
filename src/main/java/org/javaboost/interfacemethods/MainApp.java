package org.javaboost.interfacemethods;

/**
 * Task: Create  2  interfaces  with  the  same  names  for  default, the same names for static
 * and the same names for private methods and try to use it.
 */
public class MainApp {
    public static void main(String[] args) {

        System.out.println("------ Pure static call ---------");

        // Every static method can only be called with a class.
        Moon.shine();
        Sun.shine();
        SpaceObject.shine();

        System.out.println("------ Default method call ---------");

        Sun object = new SpaceObject();
//        object.shine();       compile error!
//        object.up();          private methods cannot be called
        object.down();       // default with custom overriding is OK

        System.out.println("-------- Anonymous class -------");

        // This anonymous class will be generated as Main$1.class, not like Moon.class in bytecode
        Moon moon = new Moon() {
            @Override
            public void down() {
                Moon.super.down();  // can use parent interface default method implementation via `super`
                check();            // can create and usage own methods (private, static)
                staticCheck();
            }

            private void check() {
                System.out.println("Check");
            }

            private static void staticCheck() {
                System.out.println("Static check");
            }
        };
        moon.down();

        System.out.println("------- Call static method on an instance of the class --------");

        SpaceObject spaceObject = new SpaceObject();
        spaceObject.shine(); // Do NOT this, but NO compile error! So we can call static method on an instance of class.
        // Static member 'org.javaboost.defaultmethods.SpaceObject.shine()' accessed via instance reference
    }
}
