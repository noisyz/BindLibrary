package com.noisyz.bindlibrary;

import com.noisyz.bindlibrary.classbuilder.ObjectBinderPattern;
import com.noisyz.bindlibrary.classbuilder.ValueProviderPattern;
import com.noisyz.bindlibrary.exception.GetterMethodNullException;
import com.noisyz.bindlibrary.models.base.ClassWrapper;
import com.noisyz.bindlibrary.models.base.MethodWrapper;
import com.noisyz.bindlibrary.models.key.Key;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by nero232 on 14.04.17.
 */

public class CodeWriter {

    public void writeCodeForData(Map<String, ClassWrapper> map) {
        for (String className : map.keySet()) {
            ClassWrapper classWrapper = map.get(className);

            new ObjectBinderPattern().setType(className).writeSource();
            for (Key key : classWrapper.methodsKeySet()) {
                MethodWrapper methodWrapper = classWrapper.getMethodWrapper(key);
                List<String> valueProviders = new ArrayList<>();

                if (methodWrapper.getGetter() != null) {
                    valueProviders.add(writeValueProvider(key, className, methodWrapper));
                } else
                    throw new GetterMethodNullException(className, key.toString());
            }
        }
    }


    private String writeValueProvider(Key key, String className, MethodWrapper methodWrapper) {
        String keyInString = StringUtils.validateStringKey(key.toString());
        return new ValueProviderPattern(className, keyInString, methodWrapper).writeSource();
    }


}
