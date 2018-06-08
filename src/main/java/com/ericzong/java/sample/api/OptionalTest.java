package com.ericzong.java.sample.api;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class OptionalTest {
    @Test(expectedExceptions = {NoSuchElementException.class})
    public void testEmptyGet() {
        Optional<Object> opt = Optional.empty();
        opt.get();
    }

    @Test(expectedExceptions = {NullPointerException.class})
    public void testOf() {
        Optional.of(null);
    }

    @Test
    public void testOfNullabel() {
        Optional.ofNullable(null);
    }

    @Test
    public void testIsPresentNull() {
        Assert.assertFalse(Optional.empty().isPresent());
    }

    @Test
    public void testIsPresent() {
        Optional<String> opt = Optional.ofNullable("test");
        opt.ifPresent(e -> Assert.assertEquals(e, "test"));
    }

    @Test
    public void testOrElse() {
        String result;
        Optional.of("test").orElse(result = "test"); // 必然执行赋值
        Assert.assertEquals(result, "test");
    }

    @Test
    public void testOrElseGet() {
        final List<String> ls = new ArrayList<>();
        Optional.of("test").orElseGet(() -> {ls.add("test");  return ls.get(0);});
        Assert.assertTrue(ls.isEmpty());
    }

    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testOrElseThrow() {
        Optional<Object> opt = Optional.empty();
        opt.orElseThrow(() -> new IllegalArgumentException());
    }

    @Test
    public void testMap() {
        Optional<String> opt = Optional.of("test");
        String result = opt.map(e -> e + " map").get();
        Assert.assertEquals(result, "test map");
    }

    @Test
    public void testFlatMap() {
        Optional<String> originOpt = Optional.ofNullable("test");
        String result = Optional.ofNullable(originOpt).flatMap(o -> o).orElse("default");
        Assert.assertEquals(result, "test");
    }

    @Test
    public void testFilter() {
        Optional<String> opt = Optional.of("test");
        Optional<String> newOpt = opt.filter(e -> !e.equals("test"));
        Assert.assertFalse(newOpt.isPresent());
    }
}
