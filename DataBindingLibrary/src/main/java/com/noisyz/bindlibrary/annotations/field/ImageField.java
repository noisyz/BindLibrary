package com.noisyz.bindlibrary.annotations.field;


import com.noisyz.bindlibrary.callback.imageproperty.ImageProvider;
import com.noisyz.bindlibrary.callback.imageproperty.impl.PicassoImageProvider;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Oleg on 18.03.2016.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ImageField {
    Class<? extends ImageProvider> value();
}
