package com.ericzong.java.sample.tools.testng;

import org.testng.annotations.Test;

/**
 * @author zonglu
 */
public class Demo {
	@Test
	public void test()
	{
		System.out.println(getClass().getResource("/"));
	}
}
