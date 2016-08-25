package com.noisyz.databindinglibrary.annotations.methods;

import com.noisyz.databindinglibrary.annotations.converters.ConvertToObject;
import com.noisyz.databindinglibrary.annotations.propertyType;
import com.noisyz.databindinglibrary.conversion.EmptyConverter;
import com.noisyz.databindinglibrary.wrappers.impl.view.AbsViewWrapper;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Oleg on 17.03.2016.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomSetterMethod {
    Class<? extends AbsViewWrapper> customViewWrapper();

    String propertyKey() default "";

    int propertyKeyResId() default 0;

    ConvertToObject convertToObject() default @ConvertToObject(EmptyConverter.class);
}
