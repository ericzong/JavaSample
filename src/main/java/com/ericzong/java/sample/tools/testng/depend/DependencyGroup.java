package com.bwoil.test.smartester.demo.testng.depend;

import org.testng.annotations.Test;

/**
 * Dependency group settings by xml
 * dependency-group.xml
 * @author zonglu
 */
public class DependencyGroup
{
	@Test(groups = "C")
	public void groupC() 
	{
		System.out.println("C");
	}
	
	@Test(groups = "B")
	public void groupB()
	{
		System.out.println("B");
	}

	@Test(groups = "A")
	public void groupA()
	{
		System.out.println("A");
	}
}
