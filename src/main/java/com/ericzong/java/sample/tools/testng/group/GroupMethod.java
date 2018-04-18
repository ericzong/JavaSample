package com.bwoil.test.smartester.demo.testng.group;

import org.testng.annotations.Test;

/**
 * Group method settings by xml
 * group-method.xml
 * @author zonglu
 */
public class GroupMethod
{
	@Test
	public void method1()
	{
		System.out.println("method1");
	}
	
	@Test
	public void method2()
	{
		System.out.println("method2");
	}
	
	@Test
	public void group1()
	{
		System.out.println("group1");
	}
	
	@Test
	public void group2()
	{
		System.out.println("group2");
	}
}
