package com.example;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Attempt to raise a NoClassDefFoundError.
 * <p>
 * We have 2 version of the same jackson lib. Try to compile code with higher version and touch code from higher
 * version. For this move higher version lib during runtime.
 *
 * Libs are here: version-conflict/libs/jackson
 */
public class VersionConflictDemo {

    public static void main(String[] args) throws IOException {
        System.out.println("Debug pause");

        // java.lang.NoClassDefFoundError will be thrown if we moved 2.15 and 2.14 libs during runtime
        JsonPointer empty = JsonPointer.empty();  // JsonPointer is absent in the lowest version 2.0.6
        System.out.println(empty);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonParser arrayParser = objectMapper.createNonBlockingByteArrayParser();
        // java.lang.NoSuchMethodError will be thrown if we moved 2.15 lib during runtime
        Object object = arrayParser.getNumberValueDeferred(); // method only in new version 2.15
    }
}
