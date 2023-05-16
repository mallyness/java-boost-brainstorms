package org.javaboost.collection;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Try to add and change elements in the set while iterating with an iterator.
 */
public class IterationDemo {
    public static void main(String[] args) {
        iterateSet();
//        iterateCopyOnWriteSet();
//        iterateConcurrentHashMap();
    }

    // Fail-fast
    private static void iterateSet() {
        Set<String> set = generateSet();
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println("Current element: " + iterator.next());
            System.out.println("Set size before removing: " + set.size());
            iterator.remove();  // removes element
//            set.remove("One");  // ConcurrentModificationException
            System.out.println("Set size after removing: " + set.size());
        }
    }

    private static Set<String> generateSet() {
        Set<String> set = new HashSet<>();
        set.add("One");
        set.add("Two");
        set.add("Three");
        return set;
    }

    // Fail-safe
    private static void iterateCopyOnWriteSet() {
        CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<>(generateSet());
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println("Current element: " + iterator.next());
            System.out.println("Set size before removing: " + set.size());
//            iterator.remove();  // UnsupportedOperationException
            set.remove("Three");  // Removes in original, remains in iterator snapshot
            System.out.println("Set size after removing: " + set.size());
        }
    }

    // Fail-safe
    private static void iterateConcurrentHashMap() {
        ConcurrentHashMap<String, Integer> map = genereateConcurrentHashMap();
        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            System.out.println("Current element: " + iterator.next());
            System.out.println("Map size before removing: " + map.size());
//            iterator.remove();  // removes element
            map.remove("Two");  // removes in original, but can remain in iterator "next" field if we want to remove next elements
            System.out.println("Map size after removing: " + map.size());
        }
    }

    private static ConcurrentHashMap<String, Integer> genereateConcurrentHashMap() {
        ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("One", 1);
        concurrentHashMap.put("Two", 2);
        concurrentHashMap.put("Three", 3);
        concurrentHashMap.put("Four", 4);
        concurrentHashMap.put("Five", 5);
        return concurrentHashMap;
    }
}
