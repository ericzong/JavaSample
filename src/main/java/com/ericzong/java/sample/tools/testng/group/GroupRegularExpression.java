package com.ericzong.java.sample.tools.testng.group;

import org.testng.annotations.Test;

/**
 * Group regular expression settings by xml
 * group-regular.xml
 * @author zonglu
 */
public class GroupRegularExpression
{
	@Test(groups="group1.one")
	public void group1One()
	{
		System.out.println("group1 one");
	}
	
	@Test(groups="group1.two")
	public void group1Two()
	{
		System.out.println("group1 two");
	}
	
	@Test(groups="group2.one")
	public void group2One()
	{
		System.out.println("group2 one");
	}
	
	@Test(groups="group2.two")
	public void group2Two()
	{
		System.out.println("group2 two");
	}
}
