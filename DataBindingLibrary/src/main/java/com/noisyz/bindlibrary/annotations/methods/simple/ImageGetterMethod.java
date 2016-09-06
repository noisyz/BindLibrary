package com.noisyz.bindlibrary.annotations.methods.simple;



import com.noisyz.bindlibrary.callback.imageproperty.ImageProvider;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Oleg on 18.03.2016.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ImageGetterMethod {
    Class<? extends ImageProvider> value();

    String propertyKey() default "";

    int propertyKeyResId() default 0;
}
