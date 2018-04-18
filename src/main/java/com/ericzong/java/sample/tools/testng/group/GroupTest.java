package com.ericzong.java.sample.tools.testng.group;

import org.testng.annotations.Test;

/**
 * Group settings by xml
 * group.xml
 * @author zonglu
 */
public class GroupTest
{
	@Test(groups="group1")
	public void group1_1()
	{
		System.out.println("group1-1");
	}

	@Test(groups="group1")
	public void group1_2()
	{
		System.out.println("group1-2");
	}

	@Test(groups="group2")
	public void group2_1()
	{
		System.out.println("group2-1");
	}

	@Test(groups="group2")
	public void group2_2()
	{
		System.out.println("group2-2");
	}
}
