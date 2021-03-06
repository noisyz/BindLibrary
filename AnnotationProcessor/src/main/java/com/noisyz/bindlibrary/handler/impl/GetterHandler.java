package com.noisyz.bindlibrary.handler.impl;

import com.noisyz.bindlibrary.annotation.Type;
import com.noisyz.bindlibrary.annotation.methods.simple.Getter;
import com.noisyz.bindlibrary.handler.AnnotationHandler;
import com.noisyz.bindlibrary.handler.PrimitivesHandler;
import com.noisyz.bindlibrary.models.DefaultMethodWrapper;
import com.noisyz.bindlibrary.models.base.MethodItem;
import com.noisyz.bindlibrary.models.key.Key;
import com.noisyz.bindlibrary.models.key.KeyManager;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;

/**
 * Created by nero232 on 23.03.17.
 */

public class GetterHandler extends AnnotationHandler<DefaultMethodWrapper, Getter> {

    @Override
    protected DefaultMethodWrapper createMethodWrapper(ExecutableElement executableElement, Getter getter) {
        return new DefaultMethodWrapper(getter.value());
    }

    @Override
    protected MethodItem processAnnotation(ExecutableElement element, Getter getter) {
        if (getter.value() == Type.DEFAULT) {
            return PrimitivesHandler.buildGetterMethodItem(element);
        } else {
            String returnType = element.getReturnType().toString();
            return new MethodItem(element.getSimpleName().toString(), returnType);
        }
    }

    @Override
    protected Class<Getter> getAnnotation() {
        return Getter.class;
    }

    @Override
    protected Mode getMode() {
        return Mode.GETTER;
    }

    @Override
    protected Key getKey(Getter getter, Element element) {
        return KeyManager.getKey(getter, element);
    }
}
