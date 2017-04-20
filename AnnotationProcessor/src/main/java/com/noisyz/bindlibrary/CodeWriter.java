package com.noisyz.bindlibrary;

import com.noisyz.bindlibrary.classbuilder.ObjectBinderPattern;
import com.noisyz.bindlibrary.classbuilder.RecyclerBindAdapterPattern;
import com.noisyz.bindlibrary.exception.GetterMethodNullException;
import com.noisyz.bindlibrary.models.base.ClassWrapper;
import com.noisyz.bindlibrary.models.base.MethodWrapper;
import com.noisyz.bindlibrary.models.key.Key;

import java.util.Map;

/**
 * Created by nero232 on 14.04.17.
 */

public class CodeWriter {

    public void writeCodeForData(Map<String, ClassWrapper> map) {
        for (String className : map.keySet()) {
            ClassWrapper classWrapper = map.get(className);
            ObjectBinderPattern objectBinderPattern = new ObjectBinderPattern(className);
            for (Key key : classWrapper.methodsKeySet()) {
                MethodWrapper methodWrapper = classWrapper.getMethodWrapper(key);

                if (methodWrapper.getGetter() != null) {
                    String valueProvider = methodWrapper.writeValueProvider(key, className);
                    objectBinderPattern.addImport(valueProvider);
                    objectBinderPattern.addImport(methodWrapper.getPropertyClassName());
                    objectBinderPattern.addProperty(methodWrapper.buildProperty(valueProvider, key));
                } else
                    throw new GetterMethodNullException(className, key.toString());
            }
            objectBinderPattern.writeSource();
            if (classWrapper.isGenerateAdapters())
                new RecyclerBindAdapterPattern(className).writeSource();
        }
    }

}
