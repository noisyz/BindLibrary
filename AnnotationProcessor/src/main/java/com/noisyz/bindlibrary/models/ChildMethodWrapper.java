package com.noisyz.bindlibrary.models;

import com.noisyz.bindlibrary.models.base.MethodItem;
import com.noisyz.bindlibrary.models.base.MethodWrapper;
import com.noisyz.bindlibrary.models.key.Key;

/**
 * Created by nero232 on 13.04.17.
 */

public class ChildMethodWrapper extends MethodWrapper {

    private String childClass;

    public ChildMethodWrapper(String childClass) {
        this.childClass = childClass;
    }

    @Override
    public String buildProperty(String className, Key key) {
        return null;
    }
}
