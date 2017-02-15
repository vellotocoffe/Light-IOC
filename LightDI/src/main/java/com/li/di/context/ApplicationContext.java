package com.li.di.context;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

import com.li.di.annotations.AutoInjected;
import com.li.di.annotations.Bean;

public class ApplicationContext {
	private static ConcurrentHashMap<Class<?>, Object> internMap = new ConcurrentHashMap<>();
	private static Class<?> beansDefinitions;

	public static void loadBeansConfiguration(Class<?> configurationClass) {
		beansDefinitions = configurationClass;
	}
	
	public static Object getBean(Class<?> clazz) {
		return getBeanInstance(clazz);
	}
	
	private static Object getBeanInstance(Class<?> clazz) {
		if (internMap.containsKey(clazz)) {
			return internMap.get(clazz);
		} else {
			Constructor<?> defaultCons;
			Object obj = null;
			try {
				defaultCons = clazz.getConstructor();
				obj = defaultCons.newInstance();
				injectDependenciesViaSetters(obj);
			} catch (NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				e.printStackTrace();
			}
			internMap.putIfAbsent(clazz, obj);
		}
		return internMap.get(clazz);
	}
	
	private static void injectDependenciesViaSetters(Object beforeInjection)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException,
			NoSuchMethodException, SecurityException {
		Class<?> clazz = beforeInjection.getClass();
		Method[] methods = clazz.getDeclaredMethods();
		Class<?>[] paramsTypes;
		Object beanToInjected = null;
		for (Method method : methods) {
			if (method.isAnnotationPresent(AutoInjected.class)) {
				paramsTypes = method.getParameterTypes();
				for (Class<?> paramType : paramsTypes) {
					beanToInjected = scanBeansAndReturn(paramType);
					// beanToInjected = getBeanInstance(paramType);
					method.invoke(beforeInjection, beanToInjected);
				}
			}
		}
	}

	private static Object scanBeansAndReturn(Class<?> clazz) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, InstantiationException, NoSuchMethodException, SecurityException {
		Method[] methods = beansDefinitions.getDeclaredMethods();
		Class<?> returnType;
		for (Method method : methods) {
			if (method.isAnnotationPresent(Bean.class)) {
				returnType = method.getReturnType();
				if (returnType.equals(clazz)) {
					return method.invoke(beansDefinitions.getConstructor().newInstance());
				}
			}
		}
		return null;
	}
	
}
