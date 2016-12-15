package com.noisyz.bindlibrary.annotations.view.converters;



import com.noisyz.bindlibrary.conversion.Converter;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Oleg on 17.03.2016.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ConvertToObject {
    Class<? extends Converter> value();
}
