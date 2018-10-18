package com.ericzong.java.sample.api;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;

public class ClassNameTest implements Serializable {
    private Map<String, Class<?>> classes;

    @BeforeClass
    public void setup() {
        class LocalClass {
        }

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

    @Test
    public void cloneAnonymousObject() throws CloneNotSupportedException {
        Data d1 = new Data() {};
        Data d2 = (Data) d1.clone();
        Assert.assertNotEquals(d1, d2);
        Assert.assertEquals(d1.getClass(), d2.getClass());
    }

    @Test
    public void serializeCloneAnonymousObject() throws IOException, ClassNotFoundException {
        Data d1 = new Data() {};
        Data d2 = Data.read(Data.write(d1));
        Assert.assertNotEquals(d1, d2);
        Assert.assertEquals(d1.getClass(), d2.getClass());
        System.out.println(d1.getClass().getName());
    }

    @Test
    public void createAnonymousArray() {
        Data d = new Data() {};
        Data[] array = (Data[]) Array.newInstance(d.getClass(), 0);

        System.out.println(array.getClass().getName());
        System.out.println(array.getClass().getCanonicalName());
        System.out.println(array.getClass().getSimpleName());
    }

    @Test(expectedExceptions = InstantiationException.class)
    public void anonymousNoConstructor() throws IllegalAccessException, InstantiationException {
        Data data = new Data() {};
        System.out.println(data.getClass().getConstructors().length);
        data.getClass().newInstance();
    }

    private static class NestedClass {
    }

    private class InnerClass {
    }
}

class Data implements Cloneable, Serializable {
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static File write(Data data) throws IOException {
        Path file = Files.createTempFile("java_", "serial");
        try (FileOutputStream fo = new FileOutputStream(file.toFile());
             ObjectOutputStream oo = new ObjectOutputStream(fo)
        ) {
            oo.writeObject(data);
        }

        return file.toFile();
    }

    public static Data read(File file) throws IOException, ClassNotFoundException {
        try (FileInputStream fi = new FileInputStream(file);
             ObjectInputStream oi = new ObjectInputStream(fi)
        ) {
            return (Data) oi.readObject();
        }
    }
}
