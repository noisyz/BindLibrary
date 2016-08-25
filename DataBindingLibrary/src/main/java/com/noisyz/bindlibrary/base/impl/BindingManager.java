package com.noisyz.databindinglibrary.bind.base.impl;


import com.noisyz.databindinglibrary.bind.base.AbsUIBinder;
import com.noisyz.databindinglibrary.bind.base.UIBinder;
import com.noisyz.databindinglibrary.callback.DataUpdatedCallback;

import java.util.HashMap;

/**
 * Created by Oleg on 18.03.2016.
 */
public class BindingManager extends AbsUIBinder {

    private HashMap<String, UIBinder> binders;

    public static BindingManager newInstance() {
        return new BindingManager();
    }

    private BindingManager() {
        binders = new HashMap<>();
    }

    public UIBinder getBinder(String key){
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
        super.setDataUpdatedCallback(callback);
        for (String key : binders.keySet())
            if (!binders.get(key).hasDataUpdatedCallback()) {
                binders.get(key).setDataUpdatedCallback(callback);
            }
        return this;
    }

    @Override
    public void release() {
        super.release();
        for (String key : binders.keySet())
            binders.get(key).release();
        binders.clear();
        binders = null;
    }
}
