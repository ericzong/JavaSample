package com.ericzong.java.sample.name;

public class ShadowFieldByLocal {
    static int i = 1;
    
    public static void main(String[] args) {
        int i = 0;
        System.out.println(i); // 0, access local variable;
        System.out.println(ShadowFieldByLocal.i); // 1, access field
    }
}
