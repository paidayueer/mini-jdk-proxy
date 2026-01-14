package com.study.minijdkproxy.shengsheng;

public class MyInterfaceImpl implements MyInterface {

	MyInterface myInterface;

	@Override
	public void fn1() {
		System.out.println("fn1");
	}

	@Override
	public void fn2() {
		System.out.println("fn2");
	}

	@Override
	public void fn3() {
		System.out.println("fn3");
	}
}
