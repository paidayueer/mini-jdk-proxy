package com.study.minijdkproxy;

import com.study.minijdkproxy.shengsheng.MyHandler;
import com.study.minijdkproxy.shengsheng.MyInterface;
import com.study.minijdkproxy.shengsheng.MyInterfaceFactory;
// import sun.misc.ProxyGenerator

public class MiniJdkProxyApplication {

	public static void main(String[] args) throws Exception {
		// MyProxy userProxy = new MyProxy(UserServiceImpl.class);
		// userProxy.invokeFn("login");

		// 静态代理：
		// UserService proxy = new UserServiceProxy();
		// proxy.login();

		// 动态代理
		// LogMyInvocationHandler 本质是拦截器，里面塞进你想要填充的日志信息;
		// $proxy0这个是jvm生成的代理类（此处我自己写），如果有多个实现类都是一样的MyInvocation，只会生成一个代理类，根据构造函数传入的实现类不同，会有不同的代理实例
		// UserService target = new UserServiceImpl();
		// MyInvocationHandler h = new LogMyInvocationHandler(target);
		// UserService proxy0 = new $proxy0(h);
		// proxy0.login();

		MyInterface proxyObject = MyInterfaceFactory.createProxyObject(new MethodNameHandler());
		proxyObject.fn1();
		proxyObject.fn2();
		proxyObject.fn3();

		System.out.println("-----");

		proxyObject = MyInterfaceFactory.createProxyObject2(new MethodHandler2(),proxyObject);
		System.out.println(proxyObject.getClass().getName());
		proxyObject.fn1();
		proxyObject.fn2();
		proxyObject.fn3();


	}
	static class MethodNameHandler implements MyHandler {
		@Override
		public String setMethodBody(String methodName) {
			return "System.out.println(\""+methodName+"\");";
		}
	}
	static class MethodHandler2 implements MyHandler {

		@Override
		public String setMethodBody(String methodName) {
			return "\tSystem.out.println(\"before\");\n" +
					"\t\t\tmyInterface." +
					methodName +
					"();\n" +
					"\t\t\tSystem.out.println(\"after\");";
		}


	}

}
