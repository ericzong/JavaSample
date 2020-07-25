package com.ericzong.java.sample.api;

import org.testng.Assert;
import org.testng.annotations.Test;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class OptionalTest {

    /**
     * empty() 返回同一个“空”对象
     */
    @Test
    public void testEmpty() {
        var emptyOpt1 = Optional.empty();
        var emptyOpt2 = Optional.empty();
        Assert.assertSame(emptyOpt1, emptyOpt2);
    }

    @Test
    public void testOf() {
        var valueOpt = Optional.of("test");
        Assert.assertTrue(valueOpt.isPresent());
    }

    /**
     * of() 只能为非空值创建 Optional 对象
     */
    @Test(expectedExceptions = {NullPointerException.class})
    public void testOfNull() {
        Optional.of(null);
    }

    @Test
    public void testOfNullable() {
        var nullOpt = Optional.ofNullable(null);
        Assert.assertTrue(nullOpt.isEmpty());

        var opt = Optional.ofNullable("test");
        Assert.assertTrue(opt.isPresent());
    }

    @Test
    public void testGet() {
        Optional opt = Optional.of("test");
        Assert.assertEquals(opt.get(), "test");
    }

    /**
     * get() 无值是会抛异常
     */
    @Test(expectedExceptions = {NoSuchElementException.class})
    public void testEmptyGet() {
        Optional opt = Optional.empty();
        opt.get();
    }

    @Test
    public void testIsEmpty() {
        var empty = Optional.empty();
        Assert.assertTrue(empty.isEmpty());

        var opt = Optional.of("test");
        Assert.assertFalse(opt.isEmpty());
    }

    @Test
    public void testIsPresent() {
        var empty = Optional.empty();
        Assert.assertFalse(empty.isPresent());

        var opt = Optional.of("test");
        Assert.assertTrue(opt.isPresent());
    }

    @Test
    public void testOrElse() {
        String empty = null;
        var opt1 = Optional.ofNullable(empty).orElse("other");
        Assert.assertEquals(opt1, "other");

        String opt2 = Optional.ofNullable("test").orElse("other");
        Assert.assertEquals(opt2, "test");
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void testOrElseThrow() {
        var empty = Optional.empty();
        empty.orElseThrow();
    }

    @Test
    public void testIfPresent() {
        var empty = Optional.empty();
        
    }

    @Test
    public void testOrElseGet() {
        final List<String> ls = new ArrayList<>();
        Optional.of("test").orElseGet(() -> {
            ls.add("test");
            return ls.get(0);
        });
        Assert.assertTrue(ls.isEmpty());
    }

    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testOrElseThrowFunctional() {
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
