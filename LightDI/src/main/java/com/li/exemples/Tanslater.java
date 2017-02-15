package com.li.exemples;

import java.lang.reflect.InvocationTargetException;

import com.li.di.annotations.AutoInjected;
import com.li.di.context.ApplicationContext;

public class Tanslater {
	private TranslationService service;

	public TranslationService getService() {
		return service;
	}

	@AutoInjected
	public void setService(TranslationService service) {
		this.service = service;
	}
	
	public void translate(String message){
		this.service.translate(message);
	}
	
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		ApplicationContext.loadBeansConfiguration(MyBeansConfiguration.class);
		Tanslater translater = (Tanslater)ApplicationContext.getBean(Tanslater.class);
		translater.translate("test");
	}
	
	
}
