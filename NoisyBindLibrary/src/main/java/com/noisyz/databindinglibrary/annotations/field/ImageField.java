package com.noisyz.databindinglibrary.annotations.field;


import com.noisyz.databindinglibrary.callback.imageproperty.ImageProvider;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Oleg on 18.03.2016.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ImageField {
    Class<? extends ImageProvider> imageProvider();
}
