package com.ericzong.java.sample.syntax;

import org.testng.annotations.Test;

public class SwitchTest {

    @Test
    public void testFallThrough() {
        int i = 0;
        final int j = 100;

        switch (i) {
            default:
                System.out.println("None");
            case 2 + j:
                System.out.println("Two");
            case 3:
                System.out.println("Three");
            case 4:
                System.out.println("Four");
        }
    }

    @Test
    public void testVarInBlock() {
        int i = 3;

        switch (i) {
            case 2:
                int j = 2;
                System.out.println(i + j);
                break;
            case 3:
                j = 3;
                System.out.println(i + j);
                break;
            case 4:
                j = 4;
                System.out.println(i + j);
                break;
        }
    }
}
