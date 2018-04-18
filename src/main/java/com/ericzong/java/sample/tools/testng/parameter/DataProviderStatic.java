package com.ericzong.java.sample.tools.testng.parameter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderStatic
{
	@Test(dataProvider = "create", dataProviderClass = StaticDataProvider.class)
	public void test(int i)
	{
		System.out.println(i);
	}
}

class StaticDataProvider
{
	@DataProvider(name = "create")
	public static Object[][] createData()
	{
		return new Object[][] {
			{100},
			{98}
		};
	}
}
