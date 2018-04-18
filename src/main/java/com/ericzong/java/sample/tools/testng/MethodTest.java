package com.ericzong.java.sample.tools.testng;

import org.testng.annotations.Test;

/**
 * Method settings by xml
 * method.xml
 * @author zonglu
 */
public class MethodTest
{
	@Test
	public void includeMethod()
	{
		System.out.println("include method");
	}
	
	@Test
	public void excludeMethod()
	{
		System.out.println("exclude method");
	}
	
	@Test
	public void normalMethod()
	{
		System.out.println("normalMethod");
	}
}
