package com.noisyz.bindlibrary.base.impl;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import com.noisyz.bindlibrary.base.AbsUIBinder;
import com.noisyz.bindlibrary.base.TreeUIBinder;
import com.noisyz.bindlibrary.wrappers.PropertyFactory;

import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;

/**
 * Created by oleg on 05.09.16.
 */
public class ObjectViewBinder extends TreeUIBinder implements View.OnAttachStateChangeListener {

    private WeakReference<View> parentViewRef;

    public ObjectViewBinder(Object object) {
        super(object);
    }

    public ObjectViewBinder registerView(Activity activity) {
        final View viewGroup = ((ViewGroup) activity
                .findViewById(android.R.id.content)).getChildAt(0);
        return registerView(viewGroup);
    }

    public ObjectViewBinder registerView(View parentView) {
        this.parentViewRef = new WeakReference<>(parentView);
        Object object = getObject();
        if (object != null) {
            List<AbsUIBinder> binders = PropertyFactory.getPropertyList(this, object, parentView);
            binders.removeAll(Collections.singleton(null));
            addChildren(binders);
        }
        return this;
    }

    public View getViewParent() {
        return parentViewRef.get();
    }

    public ObjectViewBinder setOnElementClick(int elementId, View.OnClickListener onClickListener) {
        View parentView = getViewParent();
        if (parentView != null) {
            View child = parentView.findViewById(elementId);
            if (child != null) {
                child.setOnClickListener(onClickListener);
            }
        }
        return this;
    }

    public ObjectViewBinder setOnElementsClick(int[] elementsId, View.OnClickListener onClickListener) {
        for (int elementId : elementsId)
            setOnElementClick(elementId, onClickListener);
        return this;
    }


    @Override
    public void release() {
        super.release();
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




