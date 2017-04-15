package com.noisyz.bindlibrary.annotation.methods.simple;

/**
 * Created by nero232 on 22.03.17.
 */

public @interface SpinnerGetter {

    String key() default "";

    int keyId() default 0;

    int items();

    int itemLayoutID();
}
