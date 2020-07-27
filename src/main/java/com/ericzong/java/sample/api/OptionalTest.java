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
        empty.ifPresent(value -> {Assert.assertTrue(value != null);});

        var opt = Optional.of("test");
        opt.ifPresent(value -> {Assert.assertTrue(value != null);});
    }

    @Test
    public void testIfPresentOrNot() {
        var empty = Optional.empty();
        final boolean isEmpty = true;
        empty.ifPresentOrElse(value -> {Assert.assertTrue(value != null);},
                () -> {Assert.assertTrue(isEmpty);});

        var opt = Optional.of("test");
        final boolean isPresent = true;
        opt.ifPresentOrElse(value -> {Assert.assertTrue(value != null);},
                () -> {Assert.assertFalse(isPresent);});
    }

    @Test
    public void testOrElseGetElse() {
        var empty = Optional.empty();
        var value = empty.orElseGet(() -> "test");
        Assert.assertEquals(value, "test");
    }

    /**
     * 抛出空指针的条件是指定的 supplier 为 null，而非其返回为 null
     */
    @Test(expectedExceptions = NullPointerException.class)
    public void testOrElseGetNull() {
        var empty = Optional.empty();

        Assert.assertNull(empty.orElseGet(() -> null));

        empty.orElseGet(null);
    }

    @Test
    public void testOrElseGetValue() {
        var opt = Optional.of("non-null");
        var value = opt.orElseGet(() -> "test");
        Assert.assertEquals(value, "non-null");
    }

    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testOrElseThrowFunctional() {
        Optional<Object> opt = Optional.empty();
        opt.orElseThrow(() -> new IllegalArgumentException());
    }


    @Test
    public void testOrNonNullValue() {
        var opt = Optional.of("non-null");
        var returnOpt = opt.or(() -> Optional.empty());
        Assert.assertNotEquals(returnOpt, Optional.empty());
    }

    @Test
    public void testOrNullValue() {
        var empty = Optional.empty();
        var returnOpt = empty.or(() -> Optional.of("return"));
        Assert.assertEquals(returnOpt, Optional.of("return"));
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testOrNullSupplier() {
        Optional.empty().or(null);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testOrNullSupplierReturnNull() {
        Optional.empty().or(() -> null);
    }

    @Test
    public void testMapEmpty() {
        var empty = Optional.empty();
        Optional opt = empty.map(e -> null);
        Assert.assertEquals(opt, Optional.empty());
        opt = empty.map(e -> "test");
        Assert.assertEquals(opt, Optional.empty());
    }

    @Test
    public void testMapPresent() {
        Optional opt = Optional.of("test");
        Optional returnOpt = opt.map(e -> "non-null: " + e);
        Assert.assertEquals(returnOpt, Optional.of("non-null: test"));
        Optional nullOpt = opt.map(e -> null);
        Assert.assertEquals(nullOpt, Optional.empty());
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testMapNullSupplier() {
        Optional opt = Optional.empty();
        opt.map(null);
    }

    @Test
    public void testFlatMapEmpyt() {
        Optional empty = Optional.empty();
        Optional opt = empty.flatMap(e -> null);
        Assert.assertEquals(opt, Optional.empty());
        opt = empty.map(e -> "test");
        Assert.assertEquals(opt, Optional.empty());
    }

    @Test
    public void testFlatMapPresent() {
        Optional originOpt = Optional.ofNullable("test");
        Optional result = originOpt.flatMap(value -> Optional.of("non-null: " + value));
        Assert.assertEquals(result, Optional.of("non-null: test"));
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testFlatMapNullSupplier() {
        Optional opt = Optional.empty();
        opt.flatMap(null);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testFlatMapNullSupplierReturnNull() {
        Optional empty = Optional.ofNullable("test");
        empty.flatMap(e -> null);
    }

    @Test
    public void testFilter() {
        Optional<String> opt = Optional.of("test");
        Optional<String> newOpt = opt.filter(e -> !e.equals("test"));
        Assert.assertFalse(newOpt.isPresent());
    }
}
