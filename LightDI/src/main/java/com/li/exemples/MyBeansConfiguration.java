package com.li.exemples;

import com.li.di.annotations.Bean;
import com.li.di.annotations.Configuration;

@Configuration
public class MyBeansConfiguration {
	@Bean
	public TranslationService getTransService(){
		return new TranslationService();
	}
	@Bean
	public PaiementService getPaiementService(){
		return new PaiementService();
	}
}
