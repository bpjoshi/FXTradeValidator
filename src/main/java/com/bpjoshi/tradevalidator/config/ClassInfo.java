package com.bpjoshi.tradevalidator.config;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface ClassInfo{
	String author() default "bpjoshi(Bhagwati Prasad)";
	int version() default 1;
	String summary();
}