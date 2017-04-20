package com.noisyz.bindlibrary.wrappers;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.noisyz.bindlibrary.base.AbsUIBinder;
import com.noisyz.bindlibrary.models.key.Key;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nero232 on 14.04.17.
 */

//BO - bindObject, V - view, T - property value
public class PropertyViewWrapper<BO, V extends View, T> extends AbsUIBinder<BO> implements ViewBinder.OnViewValueChangedListener<T> {

    private IViewBinder<T, V> iViewBinder;
    private ValueProvider<BO, T> valueProvider;
    private List<V> vList;

    public  PropertyViewWrapper(IViewBinder<T, V> iViewBinder, ValueProvider<BO, T> valueProvider,
                               BO bo, Key key) {
        super(bo, key);
        this.iViewBinder = iViewBinder;
        this.valueProvider = valueProvider;
        if (iViewBinder instanceof ViewBinder) {
            ViewBinder<T, V> viewBinder = (ViewBinder<T, V>) iViewBinder;
            viewBinder.setOnViewValueChangedListener(this);
        }
    }

    public void registerView(ViewGroup parentView) {
        if (vList != null) {
            vList.clear();
            vList = null;
        }
        if (getBinderKey().getKeyResourceId() == 0 &&
                TextUtils.isEmpty(getBinderKey().getKeyInString()))
            return;

        String key = getBinderKey().getKeyInString();
        if (TextUtils.isEmpty(key)) {
            key = parentView.getResources().getString(
                    getBinderKey().getKeyResourceId()
            );
        }
        vList = getViewsByTag(parentView, key);
    }

    @Override
    public void onViewValueChanged(T t) {
        valueProvider.invokeSetter(getBindObject(), t);
    }

    @Override
    public void bindUI() {
        T t = valueProvider.invokeGetter(getBindObject());
        for (V v : vList) {
            iViewBinder.bindUI(t, v);
            if(iViewBinder instanceof ViewBinder){
                ViewBinder<T, V> viewBinder = (ViewBinder<T, V>) iViewBinder;
                viewBinder.addListeners(v);
            }
        }
    }

    private List<V> getViewsByTag(ViewGroup root, String tag) {
        List<V> views = new ArrayList<>();
        if (root != null) {
            final int childCount = root.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View view = root.getChildAt(i);
                if (view instanceof ViewGroup) {
                    views.addAll(getViewsByTag((ViewGroup) view, tag));
                } else {
                    try {
                        V child = (V) view;
                        final Object object = child.getTag();
                        if (object != null) {
                            String objectTagInString = object.toString();
                            final String[] tagsObj = objectTagInString.split("\\|");
                            for (String tagObj : tagsObj) {
                                boolean isField = tagObj.contains(tag);
                                if (!TextUtils.isEmpty(tagObj) && isField) {
                                    views.add(child);
                                    String newTag = "";
                                    for (int j = 0; j < tagsObj.length; j++) {
                                        newTag += tagObj;
                                        if (j < tagsObj.length - 1)
                                            newTag += "|";
                                    }
                                    if (TextUtils.isEmpty(newTag)) {
                                        child.setTag(null);
                                    } else {
                                        child.setTag(newTag);
                                    }
                                }
                            }
                        }
                    } catch (ClassCastException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return views;
    }

    @Override
    public void release() {
        super.release();
        if (vList != null) {
            vList.clear();
            vList = null;
        }
        if (iViewBinder != null && iViewBinder instanceof ViewBinder) {
            ((ViewBinder) iViewBinder).release();
            iViewBinder = null;
        }

        if (valueProvider != null) {
            valueProvider = null;
        }
    }
}
