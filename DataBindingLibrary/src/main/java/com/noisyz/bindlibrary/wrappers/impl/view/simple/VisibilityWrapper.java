package com.noisyz.bindlibrary.wrappers.impl.view.simple;

import android.view.View;

import com.noisyz.bindlibrary.wrappers.impl.view.AbsViewWrapper;

/**
 * Created by Oleg on 18.03.2016.
 */
public class VisibilityWrapper<V extends View> extends AbsViewWrapper {


    public VisibilityWrapper(V v) {
        super(v);
    }

    @Override
    public void bindUI(Object object) {
        if (object != null) {
            boolean value = Boolean.valueOf(object.toString());
            if (value) {
                getView().setVisibility(View.VISIBLE);
            } else {
                getView().setVisibility(View.GONE);
            }
        }
    }
}
