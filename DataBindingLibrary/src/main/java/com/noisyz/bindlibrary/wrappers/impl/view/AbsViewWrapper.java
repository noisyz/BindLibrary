package com.noisyz.databindinglibrary.wrappers.impl.view;

import android.view.View;

/**
 * Created by Oleg on 05.04.2016.
 */
public abstract class AbsViewWrapper<V extends View> {

    private V v;
    private OnViewValueChangedListener onViewValueChangedListener;

    public AbsViewWrapper(V v) {
        this.v = v;
    }

    public void setOnViewValueChangedListener(OnViewValueChangedListener onViewValueChangedListener){
        this.onViewValueChangedListener = onViewValueChangedListener;
    }

    protected void bindObject(Object value) {
        if (onViewValueChangedListener != null) {
            onViewValueChangedListener.onViewValueChanged(value);
        }
    }

    public abstract void bindUI(Object value);

    protected V getView() {
        return v;
    }

    public void release() {
        this.v = null;
        this.onViewValueChangedListener = null;
    }

    public interface OnViewValueChangedListener {
        void onViewValueChanged(Object value);
    }
}
