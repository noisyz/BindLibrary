package com.noisyz.bindlibrary.wrappers.view.child;

import android.view.View;

import com.noisyz.bindlibrary.base.impl.ObjectViewBinder;
import com.noisyz.bindlibrary.wrappers.IViewBinder;

/**
 * Created by nero232 on 16.04.17.
 */

public abstract class ChildViewBinder<T> implements IViewBinder<T, View> {
    @Override
    public void bindUI(T t, View view) {
        getViewBinder(t).registerView(view).bindUI();
    }

    protected abstract ObjectViewBinder<T> getViewBinder(T t);
}
