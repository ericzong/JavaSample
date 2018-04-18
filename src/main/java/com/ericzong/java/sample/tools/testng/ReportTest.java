package com.ericzong.java.sample.tools.testng;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class ReportTest
{
	@Test
	public void report()
	{
		Reporter.log("My custom report.");
	}
}
