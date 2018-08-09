package com.ericzong.java.sample.api;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class ClassNameTest {
    private Map<String, Class<?>> classes;

    @BeforeClass
    public void setup() {
        class LocalClass {}

        classes = new LinkedHashMap<>();
        classes.put("Normal class", ClassNameTest.class);
        classes.put("Nested class", NestedClass.class);
        classes.put("Inner class", InnerClass.class);
        classes.put("Local class", LocalClass.class);
        classes.put("Anonymous class", new Object() {}.getClass());
        classes.put("Normal array class", ClassNameTest[].class);
        classes.put("Nested array class", NestedClass[].class);
        classes.put("Inner array class", InnerClass[].class);
        classes.put("Local array class", LocalClass[].class);
    }

    @Test
    public void test() {
        classes.entrySet().stream().forEach(c -> {
            System.out.println("----------------------");
            System.out.println(c.getKey());
            System.out.println("getName(): " + c.getValue().getName());
            System.out.println("getCanonicalName(): " + c.getValue().getCanonicalName());
            System.out.println("getSimpleName(): " + c.getValue().getSimpleName());
        });
    }

    private static class NestedClass {}

    private class InnerClass {}
}
