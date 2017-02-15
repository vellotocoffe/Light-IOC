package com.li.di.Exceptions;

public class NoSuchBeanException extends Exception {

	private static final long serialVersionUID = 1L;
	private String beanName;
	public NoSuchBeanException(String name){
		super(name + " not found ");
		this.beanName = name;
	}

}
