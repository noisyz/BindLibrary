package com.noisyz.bindlibrary.wrappers.view.simple;

import android.view.View;

import com.noisyz.bindlibrary.wrappers.IViewBinder;

/**
 * Created by Oleg on 18.03.2016.
 */
public class EnabledWrapper implements IViewBinder<Boolean, View> {

    @Override
    public void bindUI(Boolean aBoolean, View view) {
        view.setEnabled(aBoolean);
    }
}
