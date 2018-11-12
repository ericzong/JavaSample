package com.ericzong.java.sample.syntax;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Field;

public class IntegerTest {
    // 测试要通过需要修改JVM启动参数调整自动装箱的上限值
    // -Djava.lang.Integer.IntegerCache.high=200
    // -XX:AutoBoxCacheMax=200
    @Test
    public void testCache() {
        Integer i1 = 200;
        Integer i2 = 200;

        Assert.assertTrue(i1 == i2);
    }

    @Test
    public void testModifyCache() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        Class<?> clazz = Class.forName("java.lang.Integer$IntegerCache");
        Field field = clazz.getDeclaredField("cache");
        field.setAccessible(true);
        Integer[] cache = (Integer[]) field.get(clazz);
        for(int i = 0; i < cache.length; i++) {
            cache[i] = 1000;
        }
        Integer i5 = 5;
        Assert.assertTrue(i5 == 1000);
    }
}
