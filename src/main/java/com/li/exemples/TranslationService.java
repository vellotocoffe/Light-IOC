package com.li.exemples;

public class TranslationService {
	public void translate(String message){
		System.out.println("translated message");
	}
	public TranslationService getTranslateService(){
		return new TranslationService();
	}
}
