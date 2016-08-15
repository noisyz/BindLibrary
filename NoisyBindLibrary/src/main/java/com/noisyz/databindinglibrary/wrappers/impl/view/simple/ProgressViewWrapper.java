package com.noisyz.databindinglibrary.wrappers.impl.view.simple;

import android.widget.ProgressBar;

import com.noisyz.databindinglibrary.wrappers.ObjectBinder;
import com.noisyz.databindinglibrary.wrappers.impl.view.AbsViewWrapper;

/**
 * Created by Oleg on 18.03.2016.
 */
public class ProgressViewWrapper<T extends ProgressBar> extends AbsViewWrapper<T> {
    public ProgressViewWrapper(T t) {
        super(t);
    }

    @Override
    public void bindUI(Object object) {
        if (object != null) {
            getView().setProgress(Integer.valueOf(object.toString()));
        }
    }
}
