package com.noisyz.bindlibrary.models;

import com.noisyz.bindlibrary.annotation.Type;
import com.noisyz.bindlibrary.models.base.MethodItem;
import com.noisyz.bindlibrary.models.base.MethodWrapper;
import com.noisyz.bindlibrary.models.key.Key;

/**
 * Created by nero232 on 13.04.17.
 */

public class DefaultMethodWrapper extends MethodWrapper {
    private final Type type;

    public DefaultMethodWrapper(Type type) {
        this.type = type;
    }

    @Override
    public String buildProperty(String className, Key key) {
        return null;
    }
}
