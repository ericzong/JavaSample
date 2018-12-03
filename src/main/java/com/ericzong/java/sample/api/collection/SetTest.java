package com.ericzong.java.sample.api.collection;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Objects;

public class SetTest {

    @Test
    public void testNull() {
        HashSet<Object> set = new HashSet<>();
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
