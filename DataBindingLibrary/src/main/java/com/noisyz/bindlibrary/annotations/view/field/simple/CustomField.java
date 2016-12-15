package com.noisyz.bindlibrary.annotations.view.field.simple;

import com.noisyz.bindlibrary.annotations.view.converters.Conversion;
import com.noisyz.bindlibrary.annotations.view.converters.ConvertToObject;
import com.noisyz.bindlibrary.annotations.view.converters.ConvertToUI;
import com.noisyz.bindlibrary.conversion.EmptyConverter;
import com.noisyz.bindlibrary.wrappers.impl.obj.IViewBinder;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Oleg on 17.03.2016.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomField{

    Class<? extends IViewBinder> value();

    Conversion twoWayConverter() default @Conversion(EmptyConverter.class);

    ConvertToUI convertToUI() default @ConvertToUI(EmptyConverter.class);

    ConvertToObject convertToObject() default @ConvertToObject(EmptyConverter.class);
}
