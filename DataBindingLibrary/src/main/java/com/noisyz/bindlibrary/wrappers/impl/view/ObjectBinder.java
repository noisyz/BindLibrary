package com.noisyz.bindlibrary.wrappers.impl.view;

import android.view.View;

/**
 * Created by Oleg on 05.04.2016.
 */
public interface ObjectBinder<T, V extends View> extends IViewBinder<T, V> {
    void addListeners(V v);

    void removeListeners(V v);

    void bindObject();

    T getViewValue(V v);
}
