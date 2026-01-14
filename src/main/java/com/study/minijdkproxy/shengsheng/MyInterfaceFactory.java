package com.study.minijdkproxy.shengsheng;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.concurrent.atomic.AtomicInteger;

public class MyInterfaceFactory {
	private static final AtomicInteger count = new AtomicInteger(0);
	private static String setClassName() {
		return "MyInterfaceImpl"+ count.incrementAndGet();
	}
	private static String getClassFile(MyHandler h,String className) {
		// 获取MyInterfaceImpl 文件，增加h，然后创建实例，进行返回
		String methodName1 = h.setMethodBody("fn1");
		String methodName2 = h.setMethodBody("fn1");
		String methodName3 = h.setMethodBody("fn1");
		return  "package com.study.minijdkproxy.shengsheng;\n" +
				"public class " +
				className +
				" implements MyInterface{\n" +
				"\n" +
				"\tMyInterface myInterface;\n" +
				"\t@Override\n" +
				"\tpublic void fn1() {\n" +
				methodName1 +
				"\t}\n" +
				"\n" +
				"\t@Override\n" +
				"\tpublic void fn2() {\n" +
				methodName2 +
				"\t}\n" +
				"\n" +
				"\t@Override\n" +
				"\tpublic void fn3() {\n" +
				methodName3 +
				"\t}\n" +
				"}";
	}
	public static MyInterface createProxyObject(MyHandler h) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
		// 1. 写入文件
		String className = setClassName();
		String originFileString = getClassFile(h,className);
		File javaFile = new File(className +".java");
		Files.writeString(javaFile.toPath(),originFileString, StandardCharsets.UTF_8);
		// 2. 编译文件
		Compiler.compile(javaFile);
		// 3. 加载文件
		Class<?> clazz = MyInterfaceFactory.class.getClassLoader().loadClass("com.study.minijdkproxy.shengsheng."+className);
		Constructor<?> constructor = clazz.getConstructor();
		return  (MyInterface)constructor.newInstance();

	}
	public static MyInterface createProxyObject2(MyHandler h,MyInterface object) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
		MyInterface newInstance = createProxyObject(h);
		// 4.向已经创建好的实例，附上 object 值，这个object就是被代理的实例
		Field target = newInstance.getClass().getDeclaredField("myInterface");
		target.setAccessible(true);
		target.set(newInstance,object);
		return newInstance;
	}
}
