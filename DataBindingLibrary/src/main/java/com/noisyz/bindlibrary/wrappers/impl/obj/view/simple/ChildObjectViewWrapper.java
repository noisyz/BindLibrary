package com.noisyz.bindlibrary.wrappers.impl.obj.view.simple;

import android.view.View;

import com.noisyz.bindlibrary.base.impl.ObjectViewBinder;
import com.noisyz.bindlibrary.wrappers.impl.obj.IViewBinder;

/**
 * Created by oleg on 31.08.16.
 */
public class ChildObjectViewWrapper implements IViewBinder<Object, View> {

    @Override
    public void bindUI(Object o, View view) {
        new ObjectViewBinder(o).registerView(view).bindUI();
    }
}
