package com.noisyz.bindlibrary.models.base;

import com.noisyz.bindlibrary.StringUtils;
import com.noisyz.bindlibrary.classbuilder.ValueProviderPattern;
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

    public String buildProperty(String valueProvider, Key key) {
        String keyInString = "new Key(" + (key.getKeyInString() != null ? ("\"" + key.getKeyInString() + "\"") : key.getKeyResourceId()) + ")";
        return getProperty(valueProvider, keyInString);
    }

    protected abstract String getProperty(String valueProvider, String keyInString);

    public abstract String getPropertyClassName();

    public String writeValueProvider(Key key, String className) {
        String keyInString = StringUtils.validateStringKey(key.toString());
        return new ValueProviderPattern(className, keyInString, this).writeSource();
    }
}
