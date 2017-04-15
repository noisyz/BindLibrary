package com.noisyz.bindlibrary.handler.impl;

import com.noisyz.bindlibrary.annotation.methods.simple.SpinnerGetter;
import com.noisyz.bindlibrary.handler.AnnotationHandler;
import com.noisyz.bindlibrary.models.key.Key;
import com.noisyz.bindlibrary.models.key.KeyManager;
import com.noisyz.bindlibrary.models.AdapterViewMethodItem;
import com.noisyz.bindlibrary.models.base.MethodItem;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;

/**
 * Created by nero232 on 13.04.17.
 */

public class SpinnerGetterHandler extends AnnotationHandler<SpinnerGetter> {
    @Override
    protected MethodItem processAnnotation(ExecutableElement element, SpinnerGetter spinnerGetter) {
        return new AdapterViewMethodItem(spinnerGetter.items(), spinnerGetter.itemLayoutID(),
                element.getSimpleName().toString());
    }

    @Override
    protected Class<SpinnerGetter> getAnnotation() {
        return SpinnerGetter.class;
    }

    @Override
    protected Mode getMode() {
        return Mode.GETTER;
    }

    @Override
    protected Key getKey(SpinnerGetter spinnerGetter, Element element) {
        return KeyManager.getKey(spinnerGetter, element);
    }
}
