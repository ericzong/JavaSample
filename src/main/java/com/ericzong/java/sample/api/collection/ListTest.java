package com.ericzong.java.sample.api.collection;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class ListTest {

    @Test
    public void testNew() {
        ArrayList<String> list = new ArrayList<>();
        list.add("a");
    }

    @Test
    public void testNewEmpty() {
        ArrayList<WatchData> list = new ArrayList<>(0);
        list.add(new WatchData());
    }

    @Test
    public void testNewEmptyCollection() {
        ArrayList<WatchData> list = new ArrayList<>(List.of(new WatchData()));
        list.add(new WatchData());
    }

    public static class WatchData {

    }
}
