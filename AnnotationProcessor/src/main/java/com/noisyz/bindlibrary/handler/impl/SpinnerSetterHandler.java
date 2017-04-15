package com.noisyz.bindlibrary.handler.impl;

import com.noisyz.bindlibrary.annotation.methods.simple.SpinnerSetter;
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

public class SpinnerSetterHandler extends AnnotationHandler<SpinnerSetter> {
    @Override
    protected MethodItem processAnnotation(ExecutableElement element, SpinnerSetter spinnerSetter) {
        return new AdapterViewMethodItem(spinnerSetter.items(), spinnerSetter.itemLayoutID(),
                element.getSimpleName().toString());
    }

    @Override
    protected Class<SpinnerSetter> getAnnotation() {
        return SpinnerSetter.class;
    }

    @Override
    protected Mode getMode() {
        return Mode.SETTER;
    }

    @Override
    protected Key getKey(SpinnerSetter spinnerSetter, Element element) {
        return KeyManager.getKey(spinnerSetter, element);
    }
}
