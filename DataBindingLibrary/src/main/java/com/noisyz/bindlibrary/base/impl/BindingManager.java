package com.noisyz.bindlibrary.base.impl;


import com.noisyz.bindlibrary.base.ParentBinder;
import com.noisyz.bindlibrary.base.UIBinder;
import com.noisyz.bindlibrary.callback.DataUpdatedCallback;

import java.util.HashMap;

/**
 * Created by Oleg on 18.03.2016.
 */
public class BindingManager implements UIBinder, ParentBinder {

    private HashMap<String, UIBinder> binders;
    private DataUpdatedCallback dataUpdatedCallback;

    public static BindingManager newInstance() {
        return new BindingManager();
    }

    private BindingManager() {
        binders = new HashMap<>();
    }

    public UIBinder getBinder(String key) {
        return binders.get(key);
    }

    public BindingManager newBinder(String key, UIBinder uiBinder) {
        binders.put(key, uiBinder);
        if (hasDataUpdatedCallback() && !uiBinder.hasDataUpdatedCallback()) {
            uiBinder.setDataUpdatedCallback(getDataUpdatedCallback());
        }
        return this;
    }

    @Override
    public void bindUI() {
        for (String key : binders.keySet())
            binders.get(key).bindUI();
    }

    @Override
    public BindingManager setDataUpdatedCallback(DataUpdatedCallback callback) {
        this.dataUpdatedCallback = callback;
        for (String key : binders.keySet())
            if (!binders.get(key).hasDataUpdatedCallback()) {
                binders.get(key).setDataUpdatedCallback(callback);
            }
        return this;
    }

    @Override
    public DataUpdatedCallback getDataUpdatedCallback() {
        return dataUpdatedCallback;
    }

    @Override
    public boolean hasDataUpdatedCallback() {
        return dataUpdatedCallback != null;
    }

    @Override
    public void setObject(Object o) {

    }

    @Override
    public Object getObject() {
        return null;
    }

    @Override
    public void release() {
        dataUpdatedCallback = null;
        for (String key : binders.keySet())
            binders.get(key).release();
        binders.clear();
        binders = null;
    }

    @Override
    public BindingManager setDataUpdatedCallback(String propertyKey, DataUpdatedCallback callback) {
        if (binders.containsKey(propertyKey))
            binders.get(propertyKey).setDataUpdatedCallback(callback);
        return this;
    }

    @Override
    public UIBinder getParentBinder() {
        return null;
    }
}
