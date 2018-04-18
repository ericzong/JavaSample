package com.bwoil.test.smartester.demo.cglib;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class ProxyTest
{
	@Test
	public void test()
	{
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(TargetObject.class);
		enhancer.setCallback(new TargetInterceptor(new TargetObject("zonglu")));
		TargetObject targetObject = (TargetObject) enhancer.create();

		targetObject.sayName();
	}

	static class TargetObject
	{

		private String name;

		public TargetObject()
		{

		}

		public TargetObject(String name)
		{
			this.name = name;
		}

		public void sayName()
		{
			System.out.println(this.name);
		}

		public String method1(String param)
		{
			return param;
		}

		public int method2(int count)
		{
			return count;
		}

	}

	class TargetInterceptor implements MethodInterceptor
	{
		private TargetObject target;

		public TargetInterceptor()
		{
		}

		public TargetInterceptor(TargetObject target)
		{
			this.target = target;
		}

		@Override
		public Object intercept(Object object, Method method, Object[] args, MethodProxy proxy) throws Throwable
		{

			if ("sayName".equals(method.getName()))
			{
				System.out.println("*****");
			}
			Object result = method.invoke(target, args);  // 真实对象方法被调用：zonglu
			// Object result = proxy.invoke(target, args); // 真实对象方法被调用：zonglu
			// Object result = method.invoke(object, args); // 代理对象方法被调用，递归
			// Object result = proxy.invoke(object, args); // 代理对象方法被调用，递归
			// Object result = proxy.invokeSuper(object, args); // 代理对象父类方法被调用：null

			return result;
		}

	}

}
