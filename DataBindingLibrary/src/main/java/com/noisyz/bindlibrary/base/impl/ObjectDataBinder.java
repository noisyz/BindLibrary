package com.noisyz.databindinglibrary.bind.base.impl;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;

import com.noisyz.databindinglibrary.bind.base.AbsUIBinder;
import com.noisyz.databindinglibrary.bind.base.property.Property;
import com.noisyz.databindinglibrary.bind.base.property.PropertyFactory;
import com.noisyz.databindinglibrary.callback.DataUpdatedCallback;
import com.noisyz.databindinglibrary.conversion.Converter;
import com.noisyz.bindlibrary.wrappers.PropertyViewWrapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Oleg on 17.03.2016.
 */
public class ObjectDataBinder<O extends Object> extends AbsUIBinder implements View.OnAttachStateChangeListener {

    private List<Property> propertyList;

    private O o;

    private View parentView;

    public ObjectDataBinder(O o) {
        this();
        this.o = o;
    }

    public ObjectDataBinder() {
        propertyList = new ArrayList<>();
    }

    public ObjectDataBinder registerView(Fragment fragment) {
        return registerView(fragment.getView());
    }

    public ObjectDataBinder registerView(Activity activity) {
        final View viewGroup = ((ViewGroup) activity
                .findViewById(android.R.id.content)).getChildAt(0);
        return registerView(viewGroup);
    }

    public ObjectDataBinder registerView(View parentView) {
        this.parentView = parentView;
        propertyList = PropertyFactory.getPropertyList(this, o, parentView);
        propertyList.removeAll(Collections.singleton(null));
        return this;
    }

    public View getViewParent() {
        return parentView;
    }

    public ObjectDataBinder setUpdateUIConverter(String fieldName, Converter converter) {
        if (getPropertyViewWrapper(fieldName) != null)
            getPropertyViewWrapper(fieldName).setUpdateUIConverter(converter);
        return this;
    }

    public ObjectDataBinder setUpdateObjectConverter(String fieldName, Converter converter) {
        if (getPropertyViewWrapper(fieldName) != null)
            getPropertyViewWrapper(fieldName).setUpdateObjectConverter(converter);
        return this;
    }

    public ObjectDataBinder setOnElementClick(int elementId, View.OnClickListener onClickListener) {
        if (parentView != null) {
            View child = parentView.findViewById(elementId);
            if (child != null) {
                child.setOnClickListener(onClickListener);
            }
        }
        return this;
    }

    public ObjectDataBinder setOnElementsClick(int[] elementsId, View.OnClickListener onClickListener) {
        for (int elementId : elementsId)
            setOnElementClick(elementId, onClickListener);
        return this;
    }

    private PropertyViewWrapper getPropertyViewWrapper(String key) {
        for (Property property : propertyList) {
            if (property.getPropertyName().equals(key))
                return (PropertyViewWrapper) property.getPropertyBinder();
        }
        return null;
    }

    @Override
    public ObjectDataBinder setDataUpdatedCallback(DataUpdatedCallback callback) {
        super.setDataUpdatedCallback(callback);
        for (Property property : propertyList)
            property.getPropertyBinder().setDataUpdatedCallback(callback);
        return this;
    }

    @Override
    public void bindUI() {
        for (Property property : propertyList)
            property.getPropertyBinder().bindUI();
    }

    @Override
    public void setObject(Object object) {
        this.o = (O) object;
        for (Property property : propertyList) {
            property.getPropertyBinder().setObject(object);
        }
    }

    @Override
    public void release() {
        super.release();
        for (Property property : propertyList)
            property.release();
        propertyList.clear();
        propertyList = null;
        o = null;
        parentView = null;
    }

    @Override
    public void onViewAttachedToWindow(View view) {

    }

    @Override
    public void onViewDetachedFromWindow(View view) {
        release();
    }
}
