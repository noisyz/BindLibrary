package com.noisyz.databindinglibrary.annotations.field;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Oleg on 18.03.2016.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface SimpleAdapterViewField{
    int indent() default 0;

    int resourceArray();

    int layoutResID();
}
