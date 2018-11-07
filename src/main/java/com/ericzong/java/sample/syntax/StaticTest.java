package com.ericzong.java.sample.syntax;

import static com.ericzong.java.sample.syntax.StaticTest.StaticClass.test;
// 不能导入无名包中的类，也不能静态导入其静态成员
// import DefaultPkgClass;
// import static DefaultPkgClass.staticMethod

public class StaticTest {

    public static void main(String[] args) {
        test();
    }

    public static class StaticClass {
        public static final int number;

        static {
            number = 42;
        }

        public static void test() {
            System.out.println(number);
        }
    }
}
