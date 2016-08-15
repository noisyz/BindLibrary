package com.noisyz.databindinglibrary.wrappers.impl.view.simple;

import android.view.View;

import com.noisyz.databindinglibrary.wrappers.ObjectBinder;
import com.noisyz.databindinglibrary.wrappers.impl.view.AbsViewWrapper;

/**
 * Created by Oleg on 18.03.2016.
 */
public class EnabledWrapper extends AbsViewWrapper {

    public EnabledWrapper(View view) {
        super(view);
    }

    @Override
    public void bindUI(Object object) {
        if (object != null) {
            boolean value = Boolean.valueOf(object.toString());
            getView().setEnabled(value);
        }
    }

}
