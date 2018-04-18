package com.ericzong.java.sample.tools.testng.parameter;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Parameter settings by xml
 * parameter.xml
 * @author zonglu
 */
public class ParameterTest
{
	@Parameters({ "first-name", "last-name" })
	@Test
	public void testName(String firstname, @Optional("Lu") String lastname)
	{
		System.out.println(firstname);
		System.out.println(lastname);
	}
}
