package com.noisyz.bindlibrary.models;

import com.noisyz.bindlibrary.models.base.MethodWrapper;
import com.noisyz.bindlibrary.models.key.Key;

/**
 * Created by nero232 on 13.04.17.
 */

public class ImageMethodWrapper extends MethodWrapper {
    private Class<?> imageProvider;

    public ImageMethodWrapper(Class<?> imageProvider) {
        this.imageProvider = imageProvider;
    }

    @Override
    public String buildProperty(String className, Key key) {
        return null;
    }
}
