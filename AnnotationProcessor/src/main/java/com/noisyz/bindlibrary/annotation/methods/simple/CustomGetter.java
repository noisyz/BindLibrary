package com.noisyz.bindlibrary.annotation.methods.simple;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Oleg on 17.03.2016.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface CustomGetter{

    Class<?> value(); //must extend IViewBinder

    int keyId() default 0;

    String key() default "";

}
