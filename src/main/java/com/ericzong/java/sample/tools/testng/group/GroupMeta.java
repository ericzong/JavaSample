package com.ericzong.java.sample.tools.testng.group;

import org.testng.annotations.Test;

/**
 * Group meta settings by xml
 * group-meta.xml
 * @author zonglu
 */
public class GroupMeta
{
	@Test(groups="ui")
	public void test1()
	{
		System.out.println("test1");
	}
	
	@Test(groups="function")
	public void test2()
	{
		System.out.println("test2");
	}
	
	@Test(groups="check")
	public void test3()
	{
		System.out.println("test3");
	}
}
