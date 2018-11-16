package com.ericzong.java.sample.syntax;

import org.testng.annotations.Test;

public class FinalTest {

    private static final int i = 42;    // 静态常量
    private final int j = 43;           // 实例常量

    @Test
    public void test() {
        final int k = 44 * 2;               // 局部常量
        final int p;                    // 空白 final，不是常量
        p = 45;
        final Integer q = 99;           // 非基本数据类型和字符串类型变量，不是常量

        System.out.println(i);
        System.out.println(j);
        System.out.println(k);
        System.out.println(p);
        System.out.println(q);
    }

}
