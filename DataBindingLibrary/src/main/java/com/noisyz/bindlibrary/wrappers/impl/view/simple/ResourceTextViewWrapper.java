package com.noisyz.bindlibrary.wrappers.impl.view.simple;

import android.widget.TextView;

import com.noisyz.bindlibrary.wrappers.impl.view.IViewBinder;

/**
 * Created by Oleg on 17.03.2016.
 */
public class ResourceTextViewWrapper implements IViewBinder<Integer,TextView> {

    @Override
    public void bindUI(Integer integer, TextView textView) {
        textView.setText(integer);
    }
}
