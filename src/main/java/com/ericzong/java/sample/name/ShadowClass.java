package com.ericzong.java.sample.name;

public class ShadowClass {
    static class String {
        public String(java.lang.String string) {
        }

        @Override
        public java.lang.String toString() {
            return "Just custom String";
        }
    }
    
    public static void main(java.lang.String[] args) {
        String str = new String("Test");
        System.out.println(str);
    }
}