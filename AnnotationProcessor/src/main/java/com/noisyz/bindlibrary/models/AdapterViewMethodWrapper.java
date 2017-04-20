package com.noisyz.bindlibrary.models;

import com.noisyz.bindlibrary.StringUtils;
import com.noisyz.bindlibrary.models.base.MethodWrapper;

/**
 * Created by nero232 on 13.04.17.
 */

public class AdapterViewMethodWrapper extends MethodWrapper {

    private int itemLayoutResId, items;

    public AdapterViewMethodWrapper(int items, int itemLayoutResId) {
        this.itemLayoutResId = itemLayoutResId;
        this.items = items;
    }

    @Override
    protected String getProperty(String valueProvider, String keyInString) {
        return "new SelectableIntegerProperty(" + itemLayoutResId + ", " + items + ", new "
                + StringUtils.getShortType(valueProvider) + "(), " + keyInString + ")";
    }

    @Override
    public String getPropertyClassName() {
        return "com.noisyz.bindlibrary.property.SelectableIntegerProperty";
    }
}
