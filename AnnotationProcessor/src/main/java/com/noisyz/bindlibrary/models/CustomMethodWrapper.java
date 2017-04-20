package com.noisyz.bindlibrary.models;

import com.noisyz.bindlibrary.StringUtils;
import com.noisyz.bindlibrary.models.base.MethodWrapper;

/**
 * Created by nero232 on 13.04.17.
 */

public class CustomMethodWrapper extends MethodWrapper {
    private String viewBinder;

    public CustomMethodWrapper(String viewBinder) {
        this.viewBinder = viewBinder;
    }

    @Override
    protected String getProperty(String valueProvider, String keyInString) {
        return "new CustomBinderProperty(\"" + viewBinder + "\", new "
                + StringUtils.getShortType(valueProvider) + "(), " + keyInString + ")";
    }

    @Override
    public String getPropertyClassName() {
        return "com.noisyz.bindlibrary.property.CustomBinderProperty";
    }
}
