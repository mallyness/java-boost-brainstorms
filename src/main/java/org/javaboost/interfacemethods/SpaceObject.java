package org.javaboost.interfacemethods;

public class SpaceObject implements Sun, Moon {

    /*
     * We must override default method if both implemented interfaces have the same default name.
     *
     * */
    @Override
    public void down() {
        System.out.println("SpaceObject down");
        Sun.super.down();
        Moon.super.down();
    }

    static void shine() {
        System.out.println("SpaceObject shine");
    }

    private void shineCheck(){
//        Static member 'org.javaboost.defaultmethods.SpaceObject.shine()' accessed via instance reference
        this.shine();  // Do NOT this, but NO compile error!

    }

    static class Star {

        void starShine() {
            SpaceObject spaceObject = new SpaceObject();
            spaceObject.shine();  // Do NOT this, but NO compile error!
        }
    }
}
