package com.li.beansManager;

import java.lang.reflect.Field;
import java.util.Vector;

public class MyClassLoader extends ClassLoader {
	
	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		System.out.println("load " + name);
		return super.loadClass(name);
	}
	
	static public void showLoadedClass(ClassLoader cl) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		Field field = ClassLoader.class.getDeclaredField("classes");
		field.setAccessible(true);
		Vector<Class<?>>  classes = (Vector<Class<?>> )field.get(cl);
		for(Class<?> clazz : classes){
			System.out.println("--loaded class " + clazz);
		}
	}

	public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		MyClassLoader mcl = new MyClassLoader();
		ClassLoader start = mcl;
		while(start != null){
			System.out.println(start);
			showLoadedClass(start);
			start = start.getParent();
		}
		
	}

}
