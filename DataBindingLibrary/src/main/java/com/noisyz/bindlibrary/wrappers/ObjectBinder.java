package com.noisyz.bindlibrary.wrappers;

import android.view.View;

/**
 * Created by Oleg on 05.04.2016.
 */

public interface ObjectBinder<T, V extends View> extends IViewBinder<T, V> {
    void addListeners(V v);

    void removeListeners(V v);

    void bindObject(T t);

    T getViewValue(V v);
}
