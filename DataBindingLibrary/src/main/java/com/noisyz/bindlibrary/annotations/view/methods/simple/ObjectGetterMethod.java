package com.noisyz.bindlibrary.annotations.view.methods.simple;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by oleg on 06.09.16.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ObjectGetterMethod {
    String propertyKey() default "";

    int propertyKeyResId() default 0;

}