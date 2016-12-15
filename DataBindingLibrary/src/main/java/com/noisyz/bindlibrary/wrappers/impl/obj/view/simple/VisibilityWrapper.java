package com.noisyz.bindlibrary.wrappers.impl.obj.view.simple;

import android.view.View;

import com.noisyz.bindlibrary.wrappers.impl.obj.IViewBinder;

/**
 * Created by Oleg on 18.03.2016.
 */
public class VisibilityWrapper implements IViewBinder<Boolean, View> {

    @Override
    public void bindUI(Boolean aBoolean, View view) {
        if (aBoolean) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }
}
