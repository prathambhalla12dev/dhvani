package com.gooseberry.annotation;

import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidString {
    String message() default "Field cannot be null or blank"; 
    // annotation element - all elements of an annotation are public abstract
}
