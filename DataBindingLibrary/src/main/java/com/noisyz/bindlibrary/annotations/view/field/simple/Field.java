package com.noisyz.bindlibrary.annotations.view.field.simple;

import com.noisyz.bindlibrary.annotations.view.converters.Conversion;
import com.noisyz.bindlibrary.annotations.view.converters.ConvertToObject;
import com.noisyz.bindlibrary.annotations.view.converters.ConvertToUI;
import com.noisyz.bindlibrary.annotations.view.propertyType;
import com.noisyz.bindlibrary.conversion.EmptyConverter;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Oleg on 17.03.2016.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Field {
    propertyType value();

    Conversion twoWayConverter() default @Conversion(EmptyConverter.class);

    ConvertToUI convertToUI() default @ConvertToUI(EmptyConverter.class);

    ConvertToObject convertToObject() default @ConvertToObject(EmptyConverter.class);
}
