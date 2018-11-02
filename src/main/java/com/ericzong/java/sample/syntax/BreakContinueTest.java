package com.ericzong.java.sample.syntax;

import org.testng.annotations.Test;

public class BreakContinueTest {

    @Test
    public void testBreakFor() {
        System.out.println("**********");
        inner:
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(i == 1 && j == 1) break inner;
                System.out.println(i + ", " + j);
            }
        }
    }

    @Test
    public void testBreakWhile() {
        System.out.println("**********");
        int i = 0;
        inner:
        while(i < 3) {
            int j = 0;
            while(j < 3) {
                System.out.println(i + ", " + j++);
                if(i == 1 && j == 1) break inner;
            }
            i++;
        }
    }

    @Test
    public void testContinueFor() {
        System.out.println("**********");
        inner:
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(i == 1 && j == 1) continue inner;
                System.out.println(i + ", " + j);
            }
        }
    }

    @Test
    public void testContinueWhile() {
        System.out.println("**********");
        int i = 0;
        inner:
        while(i < 3) {
            try {
                int j = 0;
                while(j < 3) {
                    try {
                        if(i == 1 && j == 1) continue  inner;
                        System.out.println(i + ", " + j);
                    } finally {
                        j++;
                    }
                }
            } finally {
                i++;
            }
        }
    }
}
