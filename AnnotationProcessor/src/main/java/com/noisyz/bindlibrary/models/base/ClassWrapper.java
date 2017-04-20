package com.noisyz.bindlibrary.models.base;

import com.noisyz.bindlibrary.models.key.Key;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by nero232 on 21.03.17.
 */

public class ClassWrapper {

    private final Map<Key, MethodWrapper> methods;
    private final boolean generateAdapters;

    public ClassWrapper(boolean generateAdapters) {
        this.generateAdapters = generateAdapters;
        methods = new HashMap<>();
    }

    public MethodWrapper getMethodWrapper(Key key) {
        if (methods.keySet().contains(key))
            return methods.get(key);
        else return null;
    }

    public boolean isGenerateAdapters() {
        return generateAdapters;
    }

    public Set<Key> methodsKeySet() {
        return methods.keySet();
    }

    public void putMethodWrapper(Key key, MethodWrapper methodWrapper) {
        methods.put(key, methodWrapper);
    }
}
