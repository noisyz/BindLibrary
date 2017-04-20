package com.noisyz.bindlibrary.handler.impl;

import com.noisyz.bindlibrary.annotation.methods.simple.SpinnerSetter;
import com.noisyz.bindlibrary.handler.AnnotationHandler;
import com.noisyz.bindlibrary.handler.PrimitivesHandler;
import com.noisyz.bindlibrary.models.AdapterViewMethodWrapper;
import com.noisyz.bindlibrary.models.base.MethodItem;
import com.noisyz.bindlibrary.models.key.Key;
import com.noisyz.bindlibrary.models.key.KeyManager;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;

/**
 * Created by nero232 on 13.04.17.
 */

public class SpinnerSetterHandler extends AnnotationHandler<AdapterViewMethodWrapper, SpinnerSetter> {
    @Override
    protected AdapterViewMethodWrapper createMethodWrapper(ExecutableElement executableElement, SpinnerSetter spinnerSetter) {
        return new AdapterViewMethodWrapper(spinnerSetter.items(), spinnerSetter.itemLayoutID());
    }

    @Override
    protected MethodItem processAnnotation(ExecutableElement element, SpinnerSetter spinnerSetter) {
        return PrimitivesHandler.buildSetterMethodItem(element);
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
