package com.ericzong.java.sample.name;

public class ShadowLocalByInternalField {
    
    private int i = 0;
    
    void test() {
        class InternalClass {
            void print() {
                int i = 1;
                System.out.println(ShadowLocalByInternalField.this.i); // 0, access field
                System.out.println(i); // 1, access local variable
            }
        }
        
        InternalClass in = new InternalClass();
        in.print();
    }
    
    public static void main(String[] args) {
        new ShadowLocalByInternalField().test();
    }
}
