package com.bwoil.test.smartester.demo.testng.depend;

import org.testng.annotations.Test;

/**
 * Dependency settings by xml
 * dependency.xml
 * @author zonglu
 */
public class DependencyTest
{
	@Test(dependsOnMethods = "dependedMethod")
	public void dependMethod()
	{
		System.out.println("depend method 2");
	}

	@Test
	public void dependedMethod()
	{
		System.out.println("depended method 1");
	}
	
	@Test(dependsOnGroups = "group.*")
	public void dependGroup2()
	{
		System.out.println("depend group 3");
	}

	@Test(dependsOnGroups = "group")
	public void dependGroup()
	{
		System.out.println("depend group 2");
	}
	
	@Test(groups = "group")
	public void dependedGroup()
	{
		System.out.println("depended group 1");
	}
}
