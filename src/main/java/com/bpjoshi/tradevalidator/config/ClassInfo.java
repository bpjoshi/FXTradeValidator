package com.bpjoshi.tradevalidator.config;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/**
 * @author bpjoshi(Bhagwati Prasad)
 * Annotation for documenting classes and interfaces in the application
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface ClassInfo{
	String author() default "bpjoshi(Bhagwati Prasad)";
	int version() default 1;
	String summary();
}