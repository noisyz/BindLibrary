package com.noisyz.bindlibrary.annotations.view.field.collection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Oleg on 18.03.2016.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AdapterViewField{
    int indent() default 0;

    int resourceArray();

    int layoutResID();
}
