package com.noisyz.bindlibrary.base.impl;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import com.noisyz.bindlibrary.base.AbsUIBinder;
import com.noisyz.bindlibrary.base.UIBinder;
import com.noisyz.bindlibrary.property.abs.Property;
import com.noisyz.bindlibrary.wrappers.PropertyViewWrapper;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;


public abstract class ObjectViewBinder<T> implements UIBinder<T>, View.OnAttachStateChangeListener {

    private WeakReference<View> parentViewRef;
    private T t;
    private List<PropertyViewWrapper> propertyViewWrappers;

    public ObjectViewBinder(T t) {
        setBindObject(t);
        initBinders();
    }


    public ObjectViewBinder registerView(Activity activity) {
        final View viewGroup = ((ViewGroup) activity
                .findViewById(android.R.id.content)).getChildAt(0);
        return registerView(viewGroup);
    }

    protected abstract List<Property> getProperties();

    protected void initBinders() {
        propertyViewWrappers = new ArrayList<>();
        for (Property property : getProperties()) {
            PropertyViewWrapper propertyViewWrapper = property.buildPropertyViewWrapper(t);
            if (propertyViewWrapper != null)
                propertyViewWrappers.add(propertyViewWrapper);
        }
    }

    public ObjectViewBinder<T> registerView(View parentView) {
        parentViewRef = new WeakReference<>(parentView);
        for (PropertyViewWrapper propertyViewWrapper : propertyViewWrappers)
            propertyViewWrapper.registerView((ViewGroup) parentView);
        return this;
    }

    public View getViewParent() {
        return parentViewRef.get();
    }

    public ObjectViewBinder<T> setOnElementClick(int elementId, View.OnClickListener onClickListener) {
        View parentView = getViewParent();
        if (parentView != null) {
            View child = parentView.findViewById(elementId);
            if (child != null) {
                child.setOnClickListener(onClickListener);
            }
        }
        return this;
    }

    public ObjectViewBinder setOnElementsClick(View.OnClickListener onClickListener, int... elementsId) {
        for (int elementId : elementsId)
            setOnElementClick(elementId, onClickListener);
        return this;
    }


    @Override
    public void bindUI() {
        for (PropertyViewWrapper propertyViewWrapper : propertyViewWrappers)
            propertyViewWrapper.bindUI();
    }

    @Override
    public void setBindObject(T t) {
        this.t = t;
        for (PropertyViewWrapper propertyViewWrapper : propertyViewWrappers) {
            propertyViewWrapper.setBindObject(t);
        }
    }

    @Override
    public T getBindObject() {
        return t;
    }

    @Override
    public void release() {
        parentViewRef.clear();
        parentViewRef = null;
    }

    @Override
    public void onViewAttachedToWindow(View view) {
    }

    @Override
    public void onViewDetachedFromWindow(View view) {
        release();
    }

}




