package com.ericzong.java.sample.api.collection;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

public class SetTest {

    @Test
    public void testNull() {
        HashSet<Object> set = new HashSet<>();
        Assert.assertFalse(set.contains(null));
        set.add(null);
        Assert.assertTrue(set.contains(null));
    }

    @Test
    public void testWrongElement() {
        HashSet<WrongElement> set = new HashSet<>();
        set.add(new WrongElement());
        set.add(new WrongElement());
        Assert.assertEquals(set.size(), 1);
    }

    @Test
    public void testAddDuplicate() {
        VolatileElement e1 = new VolatileElement("Eric", 30);
        HashSet<VolatileElement> set = new HashSet<>();
        set.add(e1);
        e1.setAge(31);
        set.add(e1);
        Assert.assertEquals(set.size(), 2);
    }

    @Test
    public void testRemoveWhileForEach() {
        HashSet<String> set = new HashSet<>();
        set.add("1");
        set.add("2");
        set.add("3");

        for (String s: set) {
            if(s.equals("3")) {
                set.remove(s);
            }
        }
        Assert.assertEquals(set.size(), 2);
    }

    @Test
    public void testIteratorRemove() {
        HashSet<String> set = new HashSet<>();
        set.add("1");
        set.add("2");
        set.add("3");

        Iterator<String> iterator = set.iterator();
        while(iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
        Assert.assertTrue(set.isEmpty());
    }

    @Test(expectedExceptions = ClassCastException.class)
    public void testTreeSetNoComparable() {
        TreeSet<VolatileElement> set = new TreeSet<>();
        set.add(new VolatileElement("Eric", 30));
    }

    @Test
    public void testTreeSetComparator() {
        TreeSet<VolatileElement> set = new TreeSet<>((o1, o2) -> {
            if(o1.name.compareTo(o2.name) > 0) {
                return 1;
            } else if(o1.name.compareTo(o2.name) < 0) {
                return -1;
            } else {
                return Integer.valueOf(o1.age).compareTo(o2.age);
            }
        });
        set.add(new VolatileElement("Eric", 31));
        set.add(new VolatileElement("Eric", 30));
        Assert.assertEquals(set.first().age, 30);
        Assert.assertEquals(set.last().age, 31);
    }

    static class WrongElement {
        @Override
        public int hashCode() {
            return 0;
        }

        @Override
        public boolean equals(Object obj) {
            return true;
        }
    }

    static class VolatileElement {
        private String name;
        private int age;

        public VolatileElement(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }

        @Override
        public boolean equals(Object obj) {
            if(this == obj) {
                return true;
            }

            if(!(obj instanceof VolatileElement)) {
                return false;
            }

            if(Objects.equals(this.name, ((VolatileElement) obj).name) && Objects.equals(this.age, ((VolatileElement) obj).age)) {
                return true;
            }

            return false;
        }
    }
}
