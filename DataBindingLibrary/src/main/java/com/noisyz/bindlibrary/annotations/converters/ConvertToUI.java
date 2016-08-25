package com.noisyz.databindinglibrary.annotations.converters;

import com.noisyz.databindinglibrary.conversion.Converter;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Oleg on 17.03.2016.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ConvertToUI {
    Class<? extends Converter> value();
}
