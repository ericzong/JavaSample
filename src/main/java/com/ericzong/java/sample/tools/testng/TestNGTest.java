package com.ericzong.java.sample.tools.testng;

import org.testng.ITestNGListener;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.annotations.Test;

/**
 * Running TestNG programmatically
 * 
 * @author zonglu
 */
public class TestNGTest
{
	public static void main(String[] args)
	{
		TestListenerAdapter listener = new TestListenerAdapter();
		TestNG testng = new TestNG();
		testng.setTestClasses(new Class[] {Tester.class});
		testng.addListener((ITestNGListener)listener);
		testng.run();
	}
}

class Tester
{
	@Test
	public void test()
	{
		System.out.println("test");
	}
}