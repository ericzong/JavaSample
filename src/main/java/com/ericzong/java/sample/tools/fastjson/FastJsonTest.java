package com.ericzong.java.sample.tools.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class FastJsonTest {
    public static final String DEMO_JSON = "{\n" +
            "  \"name\": \"Eric Zong\",\n" +
            "  \"address\": {\n" +
            "    \"name\": \"China\"\n" +
            "  }\n" +
            "}";

    @Test
    public void test() {
        User user = JSON.parseObject(DEMO_JSON, User.class);
        Assert.assertEquals(user.getName(), "Eric Zong");
        Assert.assertEquals(user.getAddress().getName(), "China");
    }

    public static class User {
        private String name;

        private Address address;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }
    }

    public static class Address {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
