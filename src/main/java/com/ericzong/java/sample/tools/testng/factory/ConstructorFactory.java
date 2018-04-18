package com.bwoil.test.smartester.demo.testng.factory;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

/**
 * Constructor factory settings by xml
 * factory-constructor.xml
 * @author zonglu
 */
public class ConstructorFactory
{
	private String name;
	
	@Factory(dataProvider = "data")
	public ConstructorFactory(String name)
	{
		this.name = name;
	}
	
	@Test
	public void print()
	{
		System.out.println(this.name);
	}
	
	@DataProvider
	public static Object[][] data() 
	{
		return new Object[][] {
			{"Zong"}, 
			{"Lu"}
		};
	}
}
