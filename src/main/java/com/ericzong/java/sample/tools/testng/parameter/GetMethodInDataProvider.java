package com.ericzong.java.sample.tools.testng.parameter;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GetMethodInDataProvider
{
	@Test(dataProvider = "data")
	public void firstname(String name)
	{
		System.out.println(name);
	}

	@Test(dataProvider = "data")
	public void lastname(String name)
	{
		System.out.println(name);
	}

	@DataProvider(name = "data")
	public Object[][] createData(Method m)
	{
		if ("firstname".equals(m.getName()))
		{
			return new Object[][] {
			        { "Zong" }
			};
		}

		if ("lastname".equals(m.getName()))
		{
			return new Object[][] {
			        { "Lu" }
			};
		}

		return null;
	}
}
