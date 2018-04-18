package com.bwoil.test.smartester.demo.testng.factory;

import org.testng.annotations.Factory;
import org.testng.annotations.Test;

/**
 * Factory settings by xml
 * factory.xml
 * @author zonglu
 */
public class FactoryTest
{
	@Factory
	public Object[] createInstances()
	{
		Object[] result = new Tester[5];
		for (int i = 0; i < result.length; i++)
		{
			result[i] = new Tester(i);
		}
		
		return result;
	}
}

class Tester
{
	private int times;

	public Tester(int times)
	{
		this.times = times;
	}
	
	@Test
	public void test() 
	{
		System.out.println(times);
		for(int i = 0; i < this.times; i++)
		{
			
		}
	}
}
