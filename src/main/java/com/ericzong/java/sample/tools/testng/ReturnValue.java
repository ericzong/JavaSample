package com.ericzong.java.sample.tools.testng;

import org.testng.annotations.Test;

/**
 * Return value settings by xml
 * return-value.xml
 * @author zonglu
 */
public class ReturnValue
{
	/**
	 * Default, it will be ignored, because return a value, unless set allow-return-values="true".
	 */
	@Test
	public int returnInt() {
		System.out.println("************ test return value **************");
		return 2;
	}
}
