package org.javaboost.reference;

import java.lang.ref.*;
import java.util.ArrayList;
import java.util.List;

public class ReferencesApp {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("\n------- checkSoftReference -------");
        checkSoftReference();

        System.out.println("\n------- checkWeakReference -------");
        checkWeakReference();

        System.out.println("\n------- checkPhantomReference -------");
        checkPhantomReference();
    }

    /**
     * run JVM with -Xmx5m -Xlog:gc* flags
     */
    private static void checkSoftReference() {
        Object object = new Object();
        SoftReference<Object> softReference = new SoftReference<>(object);
        System.out.println("softReference before gc = " + softReference.get());
        object = null;
        generateTrashAndRecommendGC();
        System.out.println("softReference after gc = " + softReference.get());
    }

    private static void checkWeakReference() {
        Object object = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(object);
        System.out.println("weakReference before gc = " + weakReference.get());
        object = null;
        generateTrashAndRecommendGC();
        System.out.println("weakReference after gc = " + weakReference.get());
    }

    /**
     * 1. finalize method in ReferenceQueue in FinalizeThread
     * 2. phantomReference go to ReferenceQueue
     * 3. removed by gc
     */
    private static void checkPhantomReference() {
        MyObject myObject = new MyObject();
        System.out.println("MyObject = " + myObject);
        ReferenceQueue<MyObject> referenceQueue = new ReferenceQueue<>();
        PhantomReference<MyObject> phantomReference = new PhantomReference<>(myObject, referenceQueue);
        Reference<? extends MyObject> polledReference = referenceQueue.poll();
        System.out.println("Current polled reference from referenceQueue = " + polledReference);
        myObject = null;
        generateTrashAndRecommendGC();
        try {
            Reference<? extends MyObject> removedReference = referenceQueue.remove(5_000);
            System.out.println("Removed reference from referenceQueue: " + removedReference);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void generateTrashAndRecommendGC() {
        generateTrash();
        recommendGC();
    }

    private static void generateTrash() {
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < 200_000; i++) {
            list.add(new Object());
        }
        list = null;

    }

    private static void recommendGC() {
        System.out.println("GC is recommend.");
        System.gc();
    }
}

class MyObject {

    @Override
    protected void finalize() {
//        System.out.println("Finalize method for object was invoked.");  // works as need
        System.out.println(this + ": Finalize method was invoked.");  // works as need
//        System.out.println("Finalize method for object " + this + " was invoked.");  // freezing
    }
}
