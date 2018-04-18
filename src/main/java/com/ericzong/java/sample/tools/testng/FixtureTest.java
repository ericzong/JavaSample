package com.ericzong.java.sample.tools.testng;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Fixture settings by xml
 * fixture.xml
 * 
 * @author zonglu
 */
@Test(groups= {"all"})
public class FixtureTest
{
	@BeforeSuite
	public void beforeSuite()
	{
		System.out.println("----------BeforeSuite");
	}
	
	@AfterSuite
	public void afterSuite()
	{
		System.out.println("----------AfterSuite");
	}
	
	@BeforeTest
	public void beforeTest()
	{
		System.out.println("----------BeforeTest");
	}
	
	@AfterTest
	public void afterTest()
	{
		System.out.println("----------AfterTest");
	}
	
	@BeforeGroups("group2")
	public void beforeGroups()
	{
		System.out.println("----------BeforeGroups");
	}
	
	@AfterGroups("group2")
	public void afterGroups()
	{
		System.out.println("----------AfterGroups");
	}

	@BeforeClass
	public void beforeClass()
	{
		System.out.println("----------BeforeClass");
	}

	@AfterClass
	public void afterClass()
	{
		System.out.println("----------AfterClass");
	}

	@BeforeMethod
	public void beforeMethod()
	{
		System.out.println("----------BeforeMethod");
	}

	@AfterMethod
	public void afterMethod()
	{
		System.out.println("----------AfterMethod");
	}

	@Test
	public void testCase1()
	{
		System.out.println("testcase 1");
	}

	@Test
	public void testCase2()
	{
		System.out.println("testcase 2");
	}
	
	@Test(groups= {"group1"})
	public void testCase3()
	{
		System.out.println("testcase 3");
	}
	
	@Test(groups= {"group2"})
	public void testCase4()
	{
		System.out.println("testcase 4");
	}
}
