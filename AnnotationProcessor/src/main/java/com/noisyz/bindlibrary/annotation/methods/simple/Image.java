package com.noisyz.bindlibrary.annotation.methods.simple;



import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Oleg on 18.03.2016.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface Image {
    Class<?> value(); //must extend ImageProvider

    String key() default "";

    int keyId() default 0;
}
