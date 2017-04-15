package com.noisyz.bindlibrary.handler.impl;

import com.noisyz.bindlibrary.annotation.methods.simple.CustomGetter;
import com.noisyz.bindlibrary.handler.AnnotationHandler;
import com.noisyz.bindlibrary.models.key.Key;
import com.noisyz.bindlibrary.models.key.KeyManager;
import com.noisyz.bindlibrary.models.CustomMethodItem;
import com.noisyz.bindlibrary.models.base.MethodItem;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;

/**
 * Created by nero232 on 13.04.17.
 */

public class CustomGetterHandler extends AnnotationHandler<CustomGetter> {
    @Override
    protected MethodItem processAnnotation(ExecutableElement element, CustomGetter customGetter) {
        String returnType = element.getReturnType().toString();
        return new CustomMethodItem(customGetter.value(), element.getSimpleName().toString(), returnType);
    }

    @Override
    protected Class<CustomGetter> getAnnotation() {
        return CustomGetter.class;
    }

    @Override
    protected Mode getMode() {
        return Mode.GETTER;
    }

    @Override
    protected Key getKey(CustomGetter customGetter, Element element) {
        return KeyManager.getKey(customGetter, element);
    }
}
