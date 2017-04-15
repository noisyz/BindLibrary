package com.noisyz.bindlibrary.models;

import com.noisyz.bindlibrary.models.base.MethodItem;
import com.noisyz.bindlibrary.models.base.MethodWrapper;
import com.noisyz.bindlibrary.models.key.Key;

/**
 * Created by nero232 on 13.04.17.
 */

public class AdapterViewMethodWrapper extends MethodWrapper {

    private int itemLayoutResId, items;

    public AdapterViewMethodWrapper(int items, int itemLayoutResId, String name) {
        this.itemLayoutResId = itemLayoutResId;
        this.items = items;
    }

    @Override
    public String buildProperty(String className, Key key) {
        return null;
    }
}
