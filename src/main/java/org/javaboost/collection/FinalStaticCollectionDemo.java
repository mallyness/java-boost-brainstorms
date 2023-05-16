package org.javaboost.collection;

import java.util.*;

/**
 * Create example of final static collections.
 */
public class FinalStaticCollectionDemo {

    // Option 1
    private final static List<String> finalList;

    static {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        finalList = Collections.unmodifiableList(list);
    }

    public static void main(String[] args) {
        // Option 2
        List<Object> unmodifiableList = getFinalStaticList(new Object(), new Object());
        // Option 3
        Set<String> unmodifiableSet3 = Set.of("One", "Two", "Three", "Four", "Five");
        // Option 4
        Set<String> unmodifiableSet4 = Collections.unmodifiableSet(generateSet());
    }

    public static List<Object> getFinalStaticList(Object... objects) {
        List<Object> list = new ArrayList<>(Arrays.stream(objects).toList());
        return Collections.unmodifiableList(list);
    }

    private static Set<String> generateSet() {
        Set<String> set = new HashSet<>();
        set.add("One");
        set.add("Two");
        set.add("Three");
        set.add("Four");
        set.add("Five");
        return set;
    }
}
