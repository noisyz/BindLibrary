package com.noisyz.bindlibrary.wrappers.impl.obj;

import android.view.View;

/**
 * Created by oleg on 29.08.16.
 */
public abstract class ViewBinder<T, V extends View> implements ObjectBinder<T, V> {

    private OnViewValueChangedListener onViewValueChangedListener;

    public void setOnViewValueChangedListener(OnViewValueChangedListener onViewValueChangedListener) {
        this.onViewValueChangedListener = onViewValueChangedListener;
    }


    @Override
    public void bindObject() {
        if (onViewValueChangedListener != null)
            onViewValueChangedListener.onViewValueChanged();
    }

    public interface OnViewValueChangedListener {
        void onViewValueChanged();
    }

    public void release(){
        onViewValueChangedListener = null;
    }

}
