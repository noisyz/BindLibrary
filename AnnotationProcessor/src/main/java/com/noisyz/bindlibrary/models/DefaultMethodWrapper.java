package com.noisyz.bindlibrary.models;

import com.noisyz.bindlibrary.StringUtils;
import com.noisyz.bindlibrary.annotation.Type;
import com.noisyz.bindlibrary.models.base.MethodWrapper;

/**
 * Created by nero232 on 13.04.17.
 */

public class DefaultMethodWrapper extends MethodWrapper {
    private final Type type;

    public DefaultMethodWrapper(Type type) {
        this.type = type;
    }

    @Override
    protected String getProperty(String valueProvider, String keyInString) {
        return "new DefaultProperty(Type." + type + ", new " +
                StringUtils.getShortType(valueProvider) + "(), " + keyInString + ")";
    }

    @Override
    public String getPropertyClassName() {
        return "com.noisyz.bindlibrary.property.DefaultProperty";
    }
}
