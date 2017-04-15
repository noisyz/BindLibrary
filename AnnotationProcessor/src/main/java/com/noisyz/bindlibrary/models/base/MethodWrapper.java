package com.noisyz.bindlibrary.models.base;

import com.noisyz.bindlibrary.models.key.Key;

/**
 * Created by nero232 on 21.03.17.
 */

public abstract class MethodWrapper {

    private MethodItem getter, setter;

    public void setGetter(MethodItem getter) {
        this.getter = getter;
    }

    public void setSetter(MethodItem setter) {
        this.setter = setter;
    }

    public MethodItem getGetter() {
        return getter;
    }

    public MethodItem getSetter() {
        return setter;
    }

    public String getType() {
        return getter.getType();
    }

    public abstract String buildProperty(String className, Key key);

}
