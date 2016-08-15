package com.noisyz.databindinglibrary.annotations.methods;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Oleg on 18.03.2016.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface SimpleAdapterViewSetter {
    int indent() default 0;

    String propertyKey() default "";

    int propertyKeyResId() default 0;

    int resourceArray() default 0;

    int layoutResID() default 0;
}
