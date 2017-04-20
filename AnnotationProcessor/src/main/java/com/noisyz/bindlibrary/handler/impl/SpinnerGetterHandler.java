package com.noisyz.bindlibrary.handler.impl;

import com.noisyz.bindlibrary.annotation.methods.simple.SpinnerGetter;
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

public class SpinnerGetterHandler extends AnnotationHandler<AdapterViewMethodWrapper, SpinnerGetter> {
    @Override
    protected AdapterViewMethodWrapper createMethodWrapper(ExecutableElement executableElement, SpinnerGetter spinnerGetter) {
        return new AdapterViewMethodWrapper(spinnerGetter.items(), spinnerGetter.itemLayoutID());
    }

    @Override
    protected MethodItem processAnnotation(ExecutableElement element, SpinnerGetter spinnerGetter) {
        return PrimitivesHandler.buildGetterMethodItem(element);
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
