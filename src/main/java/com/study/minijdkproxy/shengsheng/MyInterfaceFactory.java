package com.study.minijdkproxy.shengsheng;

import org.springframework.boot.context.properties.bind.DefaultValue;

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
		String methodName2 = h.setMethodBody("fn2");
		String methodName3 = h.setMethodBody("fn3");
		return  "package com.study.minijdkproxy.shengsheng;\n" +
				"public class " +
				className +
				" implements MyInterface{\n" +
				"\n" +
				"\tMyInterface target;\n" +
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
	public static MyInterface createProxyObject(MyHandler h,MyInterface target) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
		// 获取代理类的内容，并且写进file中，然后用 Complier 编译成字节码
		String className = setClassName();
		String originStr = getClassFile(h,className);
		File javaFile = new File(className+".java");
		Files.writeString(javaFile.toPath(),originStr);
		Compiler.compile(javaFile);
		// loadClass 的类路径是从classes文件夹下开始算
		Class<?> clazz = MyInterfaceFactory.class.getClassLoader().loadClass("com.study.minijdkproxy.shengsheng."+className);
		Constructor<?> constructor = clazz.getConstructor();
		MyInterface proxyObject = (MyInterface) constructor.newInstance();
		// 这是一个在creatProxyObject 的封装上进行的再一层封装，代理链，并且成员变量中增加了上一层类的对象，便于调用该方法。
		if (target != null)  {
			Field targetObject = proxyObject.getClass().getDeclaredField("target");
			targetObject.setAccessible(true);
			targetObject.set(proxyObject,target);
		}
		return proxyObject;
	}
}
