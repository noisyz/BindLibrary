package com.noisyz.bindlibrary.wrappers.impl.obj;

import android.view.View;

/**
 * Created by oleg on 29.08.16.
 */
public interface IViewBinder<T, V extends View> {
    void bindUI(T t, V v);
}
