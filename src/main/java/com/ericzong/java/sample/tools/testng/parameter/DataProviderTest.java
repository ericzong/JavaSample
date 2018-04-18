package com.ericzong.java.sample.tools.testng.parameter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderTest
{
	@DataProvider(name = "data")
	public Object[][] createData()
	{
		return new Object[][] {
		        { "Zong", 98 },
		        { "Lu", 100 }
		};
	}

	@Test(dataProvider = "data")
	public void testData(String name, int i)
	{
		System.out.println(name + " " + i);
	}
}
