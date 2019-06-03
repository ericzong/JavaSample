package com.ericzong.java.sample.api.string;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.StringJoiner;

public class StringJoinerTest {

    @Test
    public void testCreate()
    {
        StringJoiner stringJoiner = new StringJoiner(",");
        stringJoiner.add("a");
        stringJoiner.add("b");
        stringJoiner.add("c");
        Assert.assertEquals(stringJoiner.toString(), "a,b,c");
    }

    @Test
    public void testCreateAll()
    {
        StringJoiner stringJoiner1 = new StringJoiner(",", "[", "]");
        stringJoiner1.add("1");
        stringJoiner1.add("2");
        stringJoiner1.add("3");
        Assert.assertEquals(stringJoiner1.toString(), "[1,2,3]");
    }
}
