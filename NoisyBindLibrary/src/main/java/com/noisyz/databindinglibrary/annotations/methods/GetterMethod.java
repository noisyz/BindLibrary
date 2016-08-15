package com.noisyz.databindinglibrary.annotations.methods;

import com.noisyz.databindinglibrary.annotations.converters.ConvertToUI;
import com.noisyz.databindinglibrary.annotations.propertyType;
import com.noisyz.databindinglibrary.conversion.EmptyConverter;

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