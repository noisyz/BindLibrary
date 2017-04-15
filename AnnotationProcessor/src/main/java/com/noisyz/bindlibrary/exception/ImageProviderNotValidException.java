package com.noisyz.bindlibrary.exception;

/**
 * Created by nero232 on 14.04.17.
 */

public class ImageProviderNotValidException extends IllegalArgumentException {

    public ImageProviderNotValidException(String className, String propertyName) {
        super("Specified class is not ImageProvider on " + propertyName + " in class " + className);
    }

}
