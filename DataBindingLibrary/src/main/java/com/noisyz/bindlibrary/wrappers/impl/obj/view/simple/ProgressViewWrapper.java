package com.noisyz.bindlibrary.wrappers.impl.obj.view.simple;

import android.widget.ProgressBar;

import com.noisyz.bindlibrary.wrappers.impl.obj.IViewBinder;

/**
 * Created by Oleg on 18.03.2016.
 */
public class ProgressViewWrapper implements IViewBinder<Integer, ProgressBar>{

    @Override
    public void bindUI(Integer integer, ProgressBar progressBar) {
        progressBar.setProgress(integer);
    }
}
