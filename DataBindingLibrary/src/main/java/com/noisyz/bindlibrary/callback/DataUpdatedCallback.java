package com.noisyz.bindlibrary.callback;

import com.noisyz.bindlibrary.base.UIBinder;

/**
 * Created by Oleg on 24.03.2016.
 */
public interface DataUpdatedCallback<T, V> {
    void onDataUpdated(UIBinder uiBinder, T object, String propertyName, V value);
}
