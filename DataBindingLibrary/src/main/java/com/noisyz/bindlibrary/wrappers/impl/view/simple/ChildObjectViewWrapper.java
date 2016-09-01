package com.noisyz.bindlibrary.wrappers.impl.view.simple;

import android.view.View;

import com.noisyz.bindlibrary.base.impl.ObjectDataBinder;
import com.noisyz.bindlibrary.wrappers.impl.view.IViewBinder;

/**
 * Created by oleg on 31.08.16.
 */
public class ChildObjectViewWrapper implements IViewBinder<Object, View> {

    @Override
    public void bindUI(Object o, View view) {
        new ObjectDataBinder(o).registerView(view).bindUI();
    }
}
