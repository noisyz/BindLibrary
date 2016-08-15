package com.noisyz.databindinglibrary.annotations.field;

import com.noisyz.databindinglibrary.annotations.converters.Conversion;
import com.noisyz.databindinglibrary.annotations.converters.ConvertToObject;
import com.noisyz.databindinglibrary.annotations.converters.ConvertToUI;
import com.noisyz.databindinglibrary.annotations.propertyType;
import com.noisyz.databindinglibrary.conversion.EmptyConverter;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Oleg on 17.03.2016.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface SimpleFieldType {
    propertyType value();

    Conversion twoWayConverter() default @Conversion(EmptyConverter.class);

    ConvertToUI convertToUI() default @ConvertToUI(EmptyConverter.class);

    ConvertToObject convertToObject() default @ConvertToObject(EmptyConverter.class);
}
