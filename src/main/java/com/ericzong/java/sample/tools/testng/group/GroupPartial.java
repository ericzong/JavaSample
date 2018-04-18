package com.ericzong.java.sample.tools.testng.group;

import org.testng.annotations.Test;

/**
 * Group partial settings by xml
 * group-partial.xml
 * @author zonglu
 */
@Test(groups= {"check"})
public class GroupPartial
{
	@Test(groups="function")
	public void funcTest()
	{
		System.out.println("function test");
	}
	
	public void normalTest()
	{
		System.out.println("normal test");
	}
}
