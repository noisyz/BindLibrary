package com.noisyz.bindlibrary.property;

import android.view.View;

import com.noisyz.bindlibrary.models.key.Key;
import com.noisyz.bindlibrary.property.abs.Property;
import com.noisyz.bindlibrary.wrappers.IViewBinder;
import com.noisyz.bindlibrary.wrappers.PropertyViewWrapper;
import com.noisyz.bindlibrary.wrappers.ValueProvider;

/**
 * Created by nero232 on 15.04.17.
 */

public class CustomBinderProperty<BO, V extends View, T> extends Property<BO, T> {

    private IViewBinder<T, V> iViewBinder;

    public CustomBinderProperty(Class<? extends IViewBinder<T, V>> iViewBinder, ValueProvider<BO, T> valueProvider, Key key) {
        super(valueProvider, key);
        try {
            this.iViewBinder = iViewBinder.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PropertyViewWrapper buildPropertyViewWrapper(BO bo) {
        return new PropertyViewWrapper<>(iViewBinder, getValueProvider(), bo, getKey());
    }
}
