package com.ericzong.java.sample.syntax;
// 静态导入，可导入静态成员，这里导入的是静态方法
import static com.ericzong.java.sample.syntax.StaticTest.StaticClass.test;

public class StaticTest {

    public static void main(String[] args) {
        test();
    }

    // 静态成员类，嵌套类的一种
    public static class StaticClass {
        public static final int test; // 静态变量

        static { // 静态初始化块
            test = 42;
        }

        public static void test() { // 静态方法
            System.out.println(test);
        }
    }
}