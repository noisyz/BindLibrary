package com.noisyz.databindinglibrary.annotations.methods;


import com.noisyz.databindinglibrary.callback.imageproperty.ImageProvider;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Oleg on 18.03.2016.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ImageGetterMethod {
    Class<? extends ImageProvider> imageProvider();

    String propertyKey() default "";

    int propertyKeyResId() default 0;
}
