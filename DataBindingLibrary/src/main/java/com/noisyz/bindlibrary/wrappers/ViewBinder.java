package com.noisyz.bindlibrary.wrappers;

import android.view.View;

/**
 * Created by oleg on 29.08.16.
 */
public abstract class ViewBinder<T, V extends View> implements ObjectBinder<T, V> {

    private OnViewValueChangedListener<T> onViewValueChangedListener;

    public void setOnViewValueChangedListener(OnViewValueChangedListener<T> onViewValueChangedListener) {
        this.onViewValueChangedListener = onViewValueChangedListener;
    }

    @Override
    public void bindObject(T t) {
        if (onViewValueChangedListener != null)
            onViewValueChangedListener.onViewValueChanged(t);
    }

    public void release() {
        onViewValueChangedListener = null;
    }

    public interface OnViewValueChangedListener<T> {
        void onViewValueChanged(T t);
    }
}
