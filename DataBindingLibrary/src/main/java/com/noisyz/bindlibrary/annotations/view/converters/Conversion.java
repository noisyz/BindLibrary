package com.noisyz.bindlibrary.annotations.view.converters;

import com.noisyz.bindlibrary.conversion.TwoWayConverter;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Oleg on 17.03.2016.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Conversion {
    Class<? extends TwoWayConverter> value();
}
