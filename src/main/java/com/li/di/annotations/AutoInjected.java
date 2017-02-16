package com.li.di.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/**
 * Injecter automatiquement la dépendence via les méthodes (Setters) annotées. 
 * @author hli
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoInjected {

}
