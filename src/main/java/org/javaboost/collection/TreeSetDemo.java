package org.javaboost.collection;

import java.util.TreeSet;

/**
 * Create a treeSet with your comparator which will sort Strings from the second letter in descending order.
 */
public class TreeSetDemo {

    public static void main(String[] args) {
        TreeSet<String> treeSet = new TreeSet<>(TreeSetDemo::compare);
        treeSet.add("bbb");
        treeSet.add("aaa");
        treeSet.add("ccc");
        treeSet.add("dcd");
        System.out.println(treeSet);
    }

    private static int compare(String prev, String next) {
        int result;
        if (prev.length() < 2 && next.length() < 2) {
            result = next.compareTo(prev);
        } else if (prev.length() < 2) {
            result = 1;     // a & bb -> bb a
        } else if (next.length() < 2) {
            result = -1;    // aa & b -> aa b
        } else {
            result = next.charAt(1) - prev.charAt(1);
            result = result == 0 ? next.substring(2).compareTo(prev.substring(2)) : result;
        }
        return result;
    }
}
