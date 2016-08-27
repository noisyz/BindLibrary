package com.noisyz.bindlibrary.wrappers.impl.view.simple;

import android.widget.ProgressBar;

import com.noisyz.bindlibrary.wrappers.impl.view.AbsViewWrapper;

/**
 * Created by Oleg on 18.03.2016.
 */
public class ProgressViewWrapper<V extends ProgressBar> extends AbsViewWrapper<V> {
    public ProgressViewWrapper(V v) {
        super(v);
    }

    @Override
    public void bindUI(Object object) {
        if (object != null) {
            getView().setProgress(Integer.valueOf(object.toString()));
        }
    }
}
