package com.noisyz.bindlibrary.annotations.methods;

import com.noisyz.bindlibrary.annotations.converters.ConvertToUI;
import com.noisyz.bindlibrary.annotations.propertyType;
import com.noisyz.bindlibrary.conversion.EmptyConverter;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Oleg on 17.03.2016.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface GetterMethod {

    propertyType value();

    String propertyKey() default "";

    int propertyKeyResId() default 0;

    ConvertToUI convertToUI() default @ConvertToUI(EmptyConverter.class);

}
