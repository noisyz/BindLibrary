package com.noisyz.bindlibrary.annotations.methods;

import com.noisyz.bindlibrary.annotations.converters.ConvertToObject;
import com.noisyz.bindlibrary.annotations.propertyType;
import com.noisyz.bindlibrary.conversion.EmptyConverter;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Oleg on 17.03.2016.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface SetterMethod {
    propertyType value();

    String propertyKey() default "";

    int propertyKeyResId() default 0;

    ConvertToObject convertToObject() default @ConvertToObject(EmptyConverter.class);
}
