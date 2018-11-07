package com.ericzong.java.sample.syntax;

import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;

public class TryResourceTest {

    @Test(expectedExceptions = IOException.class, expectedExceptionsMessageRegExp = "close error")
    public void testOrdinaryTry () throws IOException {
        InputStream is = null;
        try {
            is = new MyInputStream();
            is.read();
        } finally {
            is.close();
        }
    }

    @Test(expectedExceptions = IOException.class, expectedExceptionsMessageRegExp = "read error")
    public void testTwr() throws IOException {
        try (InputStream is = new MyInputStream()) {
            is.read();
        }
    }

    class MyInputStream extends InputStream {

        @Override
        public int read() throws IOException {
            throw new IOException("read error");
        }

        @Override
        public void close() throws IOException {
            throw new IOException("close error");
        }
    }
}
