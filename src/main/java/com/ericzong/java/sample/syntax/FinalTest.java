package com.ericzong.java.sample.syntax;

import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

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

    interface InterfaceField {
        int implicitlyFinal = 42;       // 隐式 final，接口中的域
    }

    @SuppressWarnings("unused")
    class MyClass implements InterfaceField {

        public void test() {
            try(InputStream implicitlyFinalResource = new FileInputStream("")) { // 隐式 final，TWR 资源
                System.out.println(implicitlyFinal);
                System.out.println(implicitlyFinalResource);
            } catch (FileNotFoundException | RuntimeException implicitlyFinalException) { // 隐式 final，多重 catch 异常参数
                implicitlyFinalException.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
