package com.noisyz.bindlibrary.exception;

/**
 * Created by oleg on 15.08.16.
 */
public class ViewBinderNotValidException extends IllegalArgumentException {

    public ViewBinderNotValidException(String className, String propertyKey) {
        super("IViewBinder not found for property " + propertyKey + " in class " + className);
    }

}
