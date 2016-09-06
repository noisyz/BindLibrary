package com.noisyz.bindlibrary.annotations.methods.simple;

import com.noisyz.bindlibrary.annotations.converters.ConvertToUI;
import com.noisyz.bindlibrary.conversion.EmptyConverter;
import com.noisyz.bindlibrary.wrappers.impl.view.IViewBinder;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Oleg on 17.03.2016.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomGetterMethod {

    Class<? extends IViewBinder> value();

    String propertyKey() default "";

    int propertyKeyResId() default 0;

    ConvertToUI convertToUI() default @ConvertToUI(EmptyConverter.class);

}
