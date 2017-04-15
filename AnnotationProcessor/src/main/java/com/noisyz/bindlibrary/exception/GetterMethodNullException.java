package com.noisyz.bindlibrary.exception;

/**
 * Created by oleg on 15.08.16.
 */
public class GetterMethodNullException extends IllegalArgumentException {

    public GetterMethodNullException(String className, String propertyKey) {
        super("Getter method not found for property " + propertyKey + " in class " + className);
    }

}
