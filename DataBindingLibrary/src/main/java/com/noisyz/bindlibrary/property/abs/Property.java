package com.noisyz.bindlibrary.property.abs;

import com.noisyz.bindlibrary.models.key.Key;
import com.noisyz.bindlibrary.wrappers.PropertyViewWrapper;
import com.noisyz.bindlibrary.wrappers.ValueProvider;

/**
 * Created by nero232 on 15.04.17.
 */

public abstract class Property<BO, T> {

    private ValueProvider<BO, T> valueProvider;
    private Key key;

    public Property(ValueProvider<BO, T> valueProvider, Key key) {
        this.key = key;
        this.valueProvider = valueProvider;
    }

    public ValueProvider<BO, T> getValueProvider() {
        return valueProvider;
    }

    public Key getKey() {
        return key;
    }

    public abstract PropertyViewWrapper buildPropertyViewWrapper(BO bo);
}
