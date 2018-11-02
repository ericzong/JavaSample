package com.ericzong.java.sample.syntax;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class VoidTest {

    @Test
    public void test() {
        for( Method m : VoidTest.class.getDeclaredMethods()) {
            if( m.getReturnType().equals(Void.TYPE)){
                System.out.println( m.getName()  + " returns void ");
            }
        }

        Assert.assertTrue(void.class.isPrimitive());

        Assert.assertEquals(void.class, Void.TYPE);
        Assert.assertEquals(Void.class.getName(), "java.lang.Void");
    }

    public Void returnNothing() {
        Void v = null;
        return v;
    }

}
